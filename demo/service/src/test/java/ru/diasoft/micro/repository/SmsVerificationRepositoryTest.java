package ru.diasoft.micro.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.diasoft.micro.domain.SmsVerification;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class SmsVerificationRepositoryTest {

    /*@Autowired
    private SmsVerificationRepository smsVerificationRepository;*/

   /* @Test
    public void smsVerificationCreate() {
        SmsVerification smsVerification = SmsVerification.builder()
                .processguid(UUID.randomUUID().toString())
                .phoneNumber("54642")
                .secretCode("0070")
                .status("WAITING")
                .build();

        SmsVerification createdEntity = smsVerificationRepository.save(smsVerification);

        Assertions.assertThat(smsVerification).isEqualToComparingOnlyGivenFields(createdEntity, "verificationId");
        Assertions.assertThat(createdEntity.getVerificationId()).isNotNull();
    }

    @Test
    public void findBySecretCodeAndProcessguidAndStatusTest() {
        String guid = UUID.randomUUID().toString();
        String secretCode = "0070";
        String status = "WAITING";


        SmsVerification smsVerification = SmsVerification.builder()
                .processguid(guid)
                .phoneNumber("54642")
                .secretCode(secretCode)
                .status(status)
                .build();

        SmsVerification createdEntity = smsVerificationRepository.save(smsVerification);

       Assertions.assertThat(smsVerificationRepository.findBySecretCodeAndProcessguidAndStatus(secretCode, guid, status)
                       .orElse(null)).isNotNull();
    }*/
}