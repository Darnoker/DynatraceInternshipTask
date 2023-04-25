package internship.task.dynatracetask;

import internship.task.dynatracetask.config.NbpConfig;
import internship.task.dynatracetask.data.MaxAndMinRate;
import internship.task.dynatracetask.response.AverageExchangeRateResponse;
import internship.task.dynatracetask.response.SpreadResponse;
import internship.task.dynatracetask.service.NbpService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class NbpServiceTest {

    private static final String AVERAGE_RATE_API_URL = "https://api.nbp.pl/api/exchangerates/rates/";
    private static NbpService nbpService;
    private static NbpConfig nbpConfig;
    private static RestTemplate restTemplate;

    @BeforeAll
    static void initializeService() {
        nbpConfig = Mockito.mock(NbpConfig.class);
        restTemplate = Mockito.mock(RestTemplate.class);
        Mockito.when(nbpConfig.getApiUrl()).thenReturn(AVERAGE_RATE_API_URL);
        nbpService = new NbpService(nbpConfig, restTemplate);
    }

    @ParameterizedTest
    @MethodSource("internship.task.dynatracetask.args.ServiceTestArgs#testAverageRateArgumentsService")
    public void testAverageRateService(String currencyCode, String date, AverageExchangeRateResponse response, Double expectedResult) {
        final String URL = String.format(
                "%s%s/%s/%s/",
                nbpConfig.getApiUrl(),
                "a",
                currencyCode.toLowerCase(),
                date
        );

        Mockito.when(restTemplate.getForObject(URL, AverageExchangeRateResponse.class)).thenReturn(response);

        Optional<Double> exchangeRateOptional = nbpService.getAverageExchangeRate(currencyCode, date);

        Assertions.assertTrue(exchangeRateOptional.isPresent());
        Assertions.assertEquals(expectedResult, exchangeRateOptional.get());
    }

    @ParameterizedTest
    @MethodSource("internship.task.dynatracetask.args.ServiceTestArgs#testLastMaxMinArgumentsService")
    public void testLastMaxAndMinRateService(String currencyCode, Integer numberOfLastQuotations, AverageExchangeRateResponse response, MaxAndMinRate expectedResult) {

        final String URL = String.format(
                "%s%s/%s/last/%d/?format=json",
                nbpConfig.getApiUrl(),
                "a",
                currencyCode.toLowerCase(),
                numberOfLastQuotations
        );

        Mockito.when(restTemplate.getForObject(URL, AverageExchangeRateResponse.class)).thenReturn(response);

        Optional<MaxAndMinRate> maxAndMinRateOptional = nbpService.getMaxAndMinRate(currencyCode,numberOfLastQuotations);

        Assertions.assertTrue(maxAndMinRateOptional.isPresent());
        Assertions.assertEquals(expectedResult, maxAndMinRateOptional.get());
    }

    @ParameterizedTest
    @MethodSource("internship.task.dynatracetask.args.ServiceTestArgs#testSpreadArgumentsService")
    public void testMajorDifferenceSpreadRateService(String currencyCode, Integer numberOfLastQuotations, SpreadResponse response, Double expectedResult) {
        final String URL = String.format(
                "%s%s/%s/last/%d/?format=json",
                nbpConfig.getApiUrl(),
                "c",
                currencyCode.toLowerCase(),
                numberOfLastQuotations
        );

        Mockito.when(restTemplate.getForObject(URL, SpreadResponse.class)).thenReturn(response);

        Optional<Double> majorDifferenceSpreadOptional = nbpService.getMajorDifferenceSpread(currencyCode, numberOfLastQuotations);

        Assertions.assertTrue(majorDifferenceSpreadOptional.isPresent());
        Assertions.assertEquals(expectedResult, majorDifferenceSpreadOptional.get());
    }
}
