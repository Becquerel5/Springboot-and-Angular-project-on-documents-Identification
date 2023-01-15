package com.fabrication.exceptions;

import com.fabrication.utils.ErrorBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class GeneralExceptionTreatmentTest {

    private GeneralExceptionTreatement generalExceptionTreatement;

    @BeforeEach
    void setUp() {
        this.generalExceptionTreatement = new GeneralExceptionTreatement();
    }

    @Test
    void customExceptionController() {

        ErrorBody errorBody = new ErrorBody(
                "Exception",
                HttpStatus.NOT_FOUND.toString(),
                "/api/v1/data"
        );

        ResponseEntity<?> responseEntity = generalExceptionTreatement.customExceptionController(
                new RuntimeException("Exception"),
                new ServletWebRequest(
                        new MockHttpServletRequest("get","/api/v1/data")
                ));
        assertCustomException(errorBody, responseEntity);
    }

    private void assertCustomException(ErrorBody errorBody, ResponseEntity<?> responseEntity) {
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.NOT_FOUND);
        assertThat(responseEntity.getStatusCodeValue()).isEqualByComparingTo(404);
        ErrorBody errorReturn = (ErrorBody)  responseEntity.getBody();
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getClass()).isEqualTo(errorBody.getClass());
        assert errorReturn != null;
        assertThat(errorReturn.getCode()).isEqualTo(errorBody.getCode());
        assertThat(errorReturn.getResource()).isEqualTo(errorBody.getResource());
    }
}