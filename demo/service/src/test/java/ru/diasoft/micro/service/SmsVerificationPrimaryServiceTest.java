package ru.diasoft.micro.service;

import liquibase.pro.packaged.V;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.diasoft.micro.controller.dto.SmsVerificationCheckRequest;
import ru.diasoft.micro.controller.dto.SmsVerificationCheckResponse;
import ru.diasoft.micro.controller.dto.SmsVerificationPostRequest;
import ru.diasoft.micro.controller.dto.SmsVerificationPostResponse;
import ru.diasoft.micro.domain.SmsVerification;
import ru.diasoft.micro.repository.SmsVerificationRepository;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class SmsVerificationPrimaryServiceTest {

    @Mock
    private SmsVerificationRepository repository;

    private SmsVerificationPrimaryService service;

    private final String PHONE_NUMBER = "89540112454";
    private final String VALID_SECRET_CODE = "0007";
    private final String INVALID_SECRET_CODE = "0008";
    private final String GUID = UUID.randomUUID().toString();
    private final String STATUS = "OK";

    @BeforeEach
    void init() {
        service = new SmsVerificationPrimaryService(repository);

        SmsVerification smsVerification = SmsVerification.builder()
                .processguid(GUID)
                .phoneNumber(PHONE_NUMBER)
                .secretCode(VALID_SECRET_CODE)
                .status(STATUS)
                .build();
        when(repository.findBySecretCodeAndProcessguidAndStatus(VALID_SECRET_CODE, GUID, STATUS)).thenReturn(Optional.of(smsVerification));
        when(repository.findBySecretCodeAndProcessguidAndStatus(INVALID_SECRET_CODE, GUID, STATUS)).thenReturn(Optional.empty());
    }

    @Test
    void dsSmsVerificationCheckValidCode() {
        SmsVerificationCheckResponse response = service.dsSmsVerificationCheck(new SmsVerificationCheckRequest(GUID, VALID_SECRET_CODE));

        assertThat(response.getCheckResult()).isEqualTo(true);
    }

    @Test
    void dsSmsVerificationCheckInvalidCode() {
        SmsVerificationCheckResponse response = service.dsSmsVerificationCheck(new SmsVerificationCheckRequest(GUID, INVALID_SECRET_CODE));

        assertThat(response.getCheckResult()).isEqualTo(false);
    }

    @Test
    void dsSmsVerificationCreate() {
        SmsVerificationPostResponse response = service.dsSmsVerificationCreate(new SmsVerificationPostRequest(PHONE_NUMBER));

        assertThat(response.getProcessGUID()).isNotNull();
    }
}