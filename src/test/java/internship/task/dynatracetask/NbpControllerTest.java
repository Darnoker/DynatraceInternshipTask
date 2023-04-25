package internship.task.dynatracetask;

import internship.task.dynatracetask.controller.NbpController;
import internship.task.dynatracetask.data.MaxAndMinRate;
import internship.task.dynatracetask.service.NbpService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class NbpControllerTest {

    private static NbpController nbpController;
    private static NbpService nbpServiceMock;

    @BeforeAll
    static void initializeController() {
        nbpServiceMock = Mockito.mock(NbpService.class);
        nbpController = new NbpController(nbpServiceMock);
    }

    @ParameterizedTest
    @MethodSource("internship.task.dynatracetask.args.ControllerTestArgs#testAverageRateArgumentsController")
    public void testAverageRateController(String currencyCode, String date, Double expectedValue) {
        Mockito.when(nbpServiceMock.getAverageExchangeRate(currencyCode, date)).thenReturn(Optional.of(expectedValue));
        ResponseEntity<Double> responseEntity = nbpController.getExchangeRate(currencyCode, date);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(expectedValue, responseEntity.getBody());
    }

    @Test
    public void testAverageRateControllerEmptyOptional() {
        String currencyCode = "USD";
        String date = "2023-01-02";
        Mockito.when(nbpServiceMock.getAverageExchangeRate(currencyCode, date)).thenReturn(Optional.empty());
        ResponseEntity<Double> responseEntity = nbpController.getExchangeRate(currencyCode, date);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        Assertions.assertNull(responseEntity.getBody());
    }

    @ParameterizedTest
    @MethodSource("internship.task.dynatracetask.args.ControllerTestArgs#testLastMaxMinArgumentsController")
    public void testLastMaxAndMinRatesController(String currencyCode, Integer numberOfLastQuotations, MaxAndMinRate expectedResult) {
        Mockito.when(nbpServiceMock.getMaxAndMinRate(currencyCode, numberOfLastQuotations)).thenReturn(Optional.of(expectedResult));
        ResponseEntity<MaxAndMinRate> responseEntity = nbpController.getMaxAndMinRate(currencyCode, numberOfLastQuotations);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(expectedResult, responseEntity.getBody());
    }

    @Test
    public void testLastMaxAndMinRatesControllerEmptyOptional() {
        String currencyCode = "USD";
        Integer numberOfLastQuotations = 10;
        Mockito.when(nbpServiceMock.getMaxAndMinRate(currencyCode, numberOfLastQuotations)).thenReturn(Optional.empty());
        ResponseEntity<MaxAndMinRate> responseEntity = nbpController.getMaxAndMinRate(currencyCode, numberOfLastQuotations);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        Assertions.assertNull(responseEntity.getBody());
    }

    @ParameterizedTest
    @MethodSource("internship.task.dynatracetask.args.ControllerTestArgs#testSpreadArgumentsController")
    public void testMajorDifferenceSpreadRateController(String currencyCode, Integer numberOfLastQuotations, Double expectedResult) {
        Mockito.when(nbpServiceMock.getMajorDifferenceSpread(currencyCode, numberOfLastQuotations)).thenReturn(Optional.of(expectedResult));
        ResponseEntity<Double> responseEntity = nbpController.getMajorDifferenceAskBid(currencyCode, numberOfLastQuotations);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(expectedResult, responseEntity.getBody());
    }

    @Test
    public void testMajorDifferenceSpreadRateControllerEmptyOptional() {
        String currencyCode = "USD";
        Integer numberOfLastQuotations = 10;
        Mockito.when(nbpServiceMock.getMajorDifferenceSpread(currencyCode, numberOfLastQuotations)).thenReturn(Optional.empty());
        ResponseEntity<Double> responseEntity = nbpController.getMajorDifferenceAskBid(currencyCode, numberOfLastQuotations);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        Assertions.assertNull(responseEntity.getBody());
    }
}
