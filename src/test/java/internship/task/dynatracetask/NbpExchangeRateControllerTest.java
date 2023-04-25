package internship.task.dynatracetask;

import internship.task.dynatracetask.controller.NbpExchangeRateController;
import internship.task.dynatracetask.data.MaxAndMinRate;
import internship.task.dynatracetask.service.NbpExchangeRateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class NbpExchangeRateControllerTest {

    private static NbpExchangeRateController nbpExchangeRateController;
    private static NbpExchangeRateService nbpExchangeRateServiceMock;

    @BeforeAll
    static void initializeController() {
        nbpExchangeRateServiceMock = Mockito.mock(NbpExchangeRateService.class);
        nbpExchangeRateController = new NbpExchangeRateController(nbpExchangeRateServiceMock);
    }

    @ParameterizedTest
    @MethodSource("internship.task.dynatracetask.args.ControllerTestArgs#testAverageRateArgumentsController")
    public void testAverageRateController(String currencyCode, String date, Double expectedValue) {
        Mockito.when(nbpExchangeRateServiceMock.getAverageExchangeRate(currencyCode, date)).thenReturn(Optional.of(expectedValue));
        ResponseEntity<Double> responseEntity = nbpExchangeRateController.getExchangeRate(currencyCode, date);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(expectedValue, responseEntity.getBody());
    }

    @Test
    public void testAverageRateControllerEmptyOptional() {
        String currencyCode = "USD";
        String date = "2023-01-02";
        Mockito.when(nbpExchangeRateServiceMock.getAverageExchangeRate(currencyCode, date)).thenReturn(Optional.empty());
        ResponseEntity<Double> responseEntity = nbpExchangeRateController.getExchangeRate(currencyCode, date);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        Assertions.assertNull(responseEntity.getBody());
    }

    @ParameterizedTest
    @MethodSource("internship.task.dynatracetask.args.ControllerTestArgs#testLastMaxMinArgumentsController")
    public void testLastMaxAndMinRatesController(String currencyCode, Integer numberOfLastQuotations, MaxAndMinRate expectedResult) {
        Mockito.when(nbpExchangeRateServiceMock.getMaxAndMinAverageExchangeRate(currencyCode, numberOfLastQuotations)).thenReturn(Optional.of(expectedResult));
        ResponseEntity<MaxAndMinRate> responseEntity = nbpExchangeRateController.getMaxAndMinAverageExchangeRate(currencyCode, numberOfLastQuotations);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(expectedResult, responseEntity.getBody());
    }

    @Test
    public void testLastMaxAndMinRatesControllerEmptyOptional() {
        String currencyCode = "USD";
        Integer numberOfLastQuotations = 10;
        Mockito.when(nbpExchangeRateServiceMock.getMaxAndMinAverageExchangeRate(currencyCode, numberOfLastQuotations)).thenReturn(Optional.empty());
        ResponseEntity<MaxAndMinRate> responseEntity = nbpExchangeRateController.getMaxAndMinAverageExchangeRate(currencyCode, numberOfLastQuotations);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        Assertions.assertNull(responseEntity.getBody());
    }

    @ParameterizedTest
    @MethodSource("internship.task.dynatracetask.args.ControllerTestArgs#testSpreadArgumentsController")
    public void testMajorDifferenceSpreadRateController(String currencyCode, Integer numberOfLastQuotations, Double expectedResult) {
        Mockito.when(nbpExchangeRateServiceMock.getMajorDifferenceSpreadRate(currencyCode, numberOfLastQuotations)).thenReturn(Optional.of(expectedResult));
        ResponseEntity<Double> responseEntity = nbpExchangeRateController.getMajorDifferenceSpreadRate(currencyCode, numberOfLastQuotations);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(expectedResult, responseEntity.getBody());
    }

    @Test
    public void testMajorDifferenceSpreadRateControllerEmptyOptional() {
        String currencyCode = "USD";
        Integer numberOfLastQuotations = 10;
        Mockito.when(nbpExchangeRateServiceMock.getMajorDifferenceSpreadRate(currencyCode, numberOfLastQuotations)).thenReturn(Optional.empty());
        ResponseEntity<Double> responseEntity = nbpExchangeRateController.getMajorDifferenceSpreadRate(currencyCode, numberOfLastQuotations);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        Assertions.assertNull(responseEntity.getBody());
    }
}
