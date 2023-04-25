package internship.task.dynatracetask;

import internship.task.dynatracetask.config.NbpConfig;
import internship.task.dynatracetask.data.MaxAndMinRate;
import internship.task.dynatracetask.exceptionhandler.HttpClientErrorExceptionHandler;
import internship.task.dynatracetask.response.AverageExchangeRateResponse;
import internship.task.dynatracetask.response.SpreadResponse;
import internship.task.dynatracetask.service.NbpExchangeRateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class NbpExchangeRateServiceTest {

    private static final String AVERAGE_RATE_API_URL = "https://api.nbp.pl/api/exchangerates/rates/";
    private static NbpExchangeRateService nbpExchangeRateService;
    private static NbpConfig nbpConfig;
    private static RestTemplate restTemplate;



    @BeforeAll
    static void initializeService() {
        nbpConfig = Mockito.mock(NbpConfig.class);
        restTemplate = Mockito.mock(RestTemplate.class);
        Mockito.when(nbpConfig.getApiUrl()).thenReturn(AVERAGE_RATE_API_URL);
        nbpExchangeRateService = new NbpExchangeRateService(nbpConfig, restTemplate, new HttpClientErrorExceptionHandler());
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

        Optional<Double> exchangeRateOptional = nbpExchangeRateService.getAverageExchangeRate(currencyCode, date);

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

        Optional<MaxAndMinRate> maxAndMinRateOptional = nbpExchangeRateService.getLastMaxAndMinAverageExchangeRate(currencyCode,numberOfLastQuotations);

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

        Optional<Double> majorDifferenceSpreadOptional = nbpExchangeRateService.getMajorDifferenceSpreadRate(currencyCode, numberOfLastQuotations);

        Assertions.assertTrue(majorDifferenceSpreadOptional.isPresent());
        Assertions.assertEquals(expectedResult, majorDifferenceSpreadOptional.get());
    }

    @Test
    public void testEmptyResponseForAverageExchangeRate() {
        String currencyCode = "GBP";
        String date = "2023-01-01";

        final String URL = String.format(
                "%s%s/%s/%s/",
                nbpConfig.getApiUrl(),
                "a",
                currencyCode.toLowerCase(),
                date
        );

        Mockito.when(restTemplate.getForObject(URL, AverageExchangeRateResponse.class)).thenReturn(null);
        Optional<Double> response = nbpExchangeRateService.getAverageExchangeRate(currencyCode, date);

        Assertions.assertFalse(response.isPresent());
    }

    @Test
    public void testNotValidFormatOfDateForAverageExchangeRate() {
        String currencyCode = "GBP";
        String date = "02.01.2023";

        final String URL = String.format(
                "%s%s/%s/%s/",
                nbpConfig.getApiUrl(),
                "a",
                currencyCode.toLowerCase(),
                date
        );

        Mockito.when(restTemplate.getForObject(URL, AverageExchangeRateResponse.class)).thenReturn(null);
        Optional<Double> response = nbpExchangeRateService.getAverageExchangeRate(currencyCode, date);

        Assertions.assertFalse(response.isPresent());
    }

    @Test
    public void testEmptyResponseForMaxAndMinAverageExchangeRate() {
        String currencyCode = "GBP";
        Integer numberOfLastQuotations = 0;

        final String URL = String.format(
                "%s%s/%s/last/%d/?format=json",
                nbpConfig.getApiUrl(),
                "a",
                currencyCode.toLowerCase(),
                numberOfLastQuotations
        );

        Mockito.when(restTemplate.getForObject(URL, AverageExchangeRateResponse.class)).thenReturn(null);
        Optional<MaxAndMinRate> response = nbpExchangeRateService.getLastMaxAndMinAverageExchangeRate(currencyCode, numberOfLastQuotations);

        Assertions.assertFalse(response.isPresent());
    }

    @Test
    public void testEmptyResponseForSpreadRate() {
        String currencyCode = "GBP";
        Integer numberOfLastQuotations = 0;

        final String URL = String.format(
                "%s%s/%s/last/%d/?format=json",
                nbpConfig.getApiUrl(),
                "a",
                currencyCode.toLowerCase(),
                numberOfLastQuotations
        );

        Mockito.when(restTemplate.getForObject(URL, AverageExchangeRateResponse.class)).thenReturn(null);
        Optional<MaxAndMinRate> response = nbpExchangeRateService.getLastMaxAndMinAverageExchangeRate(currencyCode, numberOfLastQuotations);

        Assertions.assertFalse(response.isPresent());
    }

    @Test
    public void testNotValidCurrencyCodeForMaxAndMinAverageExchangeRate() {
        String currencyCode = "InvalidCurrencyCode";
        Integer numberOfLastQuotations = 10;


        final String URL = String.format(
                "%s%s/%s/%s/",
                nbpConfig.getApiUrl(),
                "a",
                currencyCode.toLowerCase(),
                numberOfLastQuotations
        );

        Mockito.when(restTemplate.getForObject(URL, AverageExchangeRateResponse.class)).thenReturn(null);
        Optional<MaxAndMinRate> response = nbpExchangeRateService.getLastMaxAndMinAverageExchangeRate(currencyCode, numberOfLastQuotations);

        Assertions.assertFalse(response.isPresent());
    }

    @Test
    public void testNotValidCurrencyCodeForSpreadRate() {
        String currencyCode = "InvalidCurrencyCode";
        Integer numberOfLastQuotations = 0;


        final String URL = String.format(
                "%s%s/%s/%s/",
                nbpConfig.getApiUrl(),
                "a",
                currencyCode.toLowerCase(),
                numberOfLastQuotations
        );

        Mockito.when(restTemplate.getForObject(URL, AverageExchangeRateResponse.class)).thenReturn(null);
        Optional<Double> response = nbpExchangeRateService.getMajorDifferenceSpreadRate(currencyCode, numberOfLastQuotations);

        Assertions.assertFalse(response.isPresent());
    }




}
