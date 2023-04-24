package internship.task.dynatracetask;

import internship.task.dynatracetask.config.NbpConfig;
import internship.task.dynatracetask.data.MaxAndMinRate;
import internship.task.dynatracetask.service.NbpService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class NbpServiceTest {

    private static final String AVERAGE_RATE_API_URL = "https://api.nbp.pl/api/exchangerates/rates/";
    private static NbpService nbpService;
    private static NbpConfig nbpConfig;


    @BeforeAll
    static void initializeService() {
        nbpConfig = Mockito.mock(NbpConfig.class);
        Mockito.when(nbpConfig.getApiUrl()).thenReturn(AVERAGE_RATE_API_URL);
        nbpService = new NbpService(nbpConfig, new RestTemplate());
    }


    @ParameterizedTest
    @MethodSource("internship.task.dynatracetask.args.TestArgs#testAverageRateArguments")
    public void testAverageRateService(String currencyCode, String date, Double expectedResult) {
        Optional<Double> exchangeRateOptional = nbpService.getAverageExchangeRate(currencyCode, date);

        Assertions.assertTrue(exchangeRateOptional.isPresent());
        Assertions.assertEquals(expectedResult, exchangeRateOptional.get());
    }

    @ParameterizedTest
    @MethodSource("internship.task.dynatracetask.args.TestArgs#testLastMaxMinArguments")
    public void testLastMaxAndMinRateService(String currencyCode, Integer numberOfLastQuotations, MaxAndMinRate expectedResult) {
        Optional<MaxAndMinRate> maxAndMinRateOptional = nbpService.getMaxAndMinRate(currencyCode,numberOfLastQuotations);

        Assertions.assertTrue(maxAndMinRateOptional.isPresent());
        Assertions.assertEquals(expectedResult, maxAndMinRateOptional.get());
    }

    @ParameterizedTest
    @MethodSource("internship.task.dynatracetask.args.TestArgs#testSpreadArguments")
    public void testMajorDifferenceSpreadRateService(String currencyCode, Integer numberOfLastQuotations, Double expectedResult) {
        Optional<Double> majorDifferenceSpreadOptional = nbpService.getMajorDifferenceSpread(currencyCode, numberOfLastQuotations);

        Assertions.assertTrue(majorDifferenceSpreadOptional.isPresent());
        Assertions.assertEquals(expectedResult, majorDifferenceSpreadOptional.get());
    }

    @Test
    public void testAverageExchangeRateNotValidPathVariable() {
        Assertions.assertThrows(
                HttpClientErrorException.class,
                () -> nbpService.getAverageExchangeRate("currencyCode", "date")
        );
        Assertions.assertThrows(
                HttpClientErrorException.class,
                () -> nbpService.getMaxAndMinRate("currencyCode", null)
        );
        Assertions.assertThrows(
                HttpClientErrorException.class,
                () -> nbpService.getMajorDifferenceSpread("currencyCode", null)
        );
    }
}
