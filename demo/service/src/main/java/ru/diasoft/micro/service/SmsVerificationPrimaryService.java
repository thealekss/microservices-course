package ru.diasoft.micro.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.diasoft.micro.controller.dto.SmsVerificationCheckRequest;
import ru.diasoft.micro.controller.dto.SmsVerificationCheckResponse;
import ru.diasoft.micro.controller.dto.SmsVerificationPostRequest;
import ru.diasoft.micro.controller.dto.SmsVerificationPostResponse;
import ru.diasoft.micro.domain.SmsVerification;
import ru.diasoft.micro.repository.SmsVerificationRepository;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Primary
public class SmsVerificationPrimaryService implements SmsVerificationService {

    private final SmsVerificationRepository repository;

    @Override
    public SmsVerificationCheckResponse dsSmsVerificationCheck(SmsVerificationCheckRequest smsVerificationCheckRequest) {
        Optional<SmsVerification> response = repository.findBySecretCodeAndProcessguidAndStatus(smsVerificationCheckRequest.getCode(),
                smsVerificationCheckRequest.getProcessGUID(), "OK");
        return new SmsVerificationCheckResponse(response.isPresent());
    }

    @Override
    public SmsVerificationPostResponse dsSmsVerificationCreate(SmsVerificationPostRequest smsVerificationPostRequest) {
        String processguid = UUID.randomUUID().toString();
        String secretCode = String.format("%04d", new Random().nextInt(10000));
        SmsVerification smsVerification = SmsVerification.builder()
                .phoneNumber(smsVerificationPostRequest.getPhoneNumber())
                .processguid(processguid)
                .secretCode(secretCode)
                .status("WAITING")
                .build();
        repository.save(smsVerification);
        return new SmsVerificationPostResponse(processguid);
    }
}
