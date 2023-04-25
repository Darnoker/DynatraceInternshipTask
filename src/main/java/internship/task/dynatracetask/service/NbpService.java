package internship.task.dynatracetask.service;

import internship.task.dynatracetask.config.NbpConfig;
import internship.task.dynatracetask.data.AverageExchangeRate;
import internship.task.dynatracetask.data.MaxAndMinRate;
import internship.task.dynatracetask.exceptionhandler.HttpClientErrorExceptionHandler;
import internship.task.dynatracetask.response.AverageExchangeRateResponse;
import internship.task.dynatracetask.response.SpreadResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class NbpService {

    private final NbpConfig nbpConfig;
    private final RestTemplate restTemplate;
    private final String tableTypeA = "a";
    private final String tableTypeC = "c";
    private static final Logger LOGGER = Logger.getLogger(NbpService.class.getName());

    public NbpService(NbpConfig nbpConfig, RestTemplate restTemplate) {
        this.nbpConfig = nbpConfig;
        this.restTemplate = restTemplate;
    }

    public Optional<Double> getAverageExchangeRate(String currencyCode, String date) {
        final String URL = String.format(
                "%s%s/%s/%s/",
                nbpConfig.getApiUrl(),
                tableTypeA,
                currencyCode.toLowerCase(),
                date
        );
        try {
            Optional<AverageExchangeRateResponse> responseOptional = Optional.ofNullable(restTemplate.getForObject(URL, AverageExchangeRateResponse.class));

            if(responseOptional.isPresent()) {
                AverageExchangeRateResponse response = responseOptional.get();
                if(!response.getRates().isEmpty()) {
                    return Optional.of(response.getRates().get(0).getMid());
                }
            }

        } catch (HttpClientErrorException e) {
            HttpClientErrorExceptionHandler.sendErrorInformation(e.getStatusCode(), LOGGER);
        }

        return Optional.empty();
    }

    public Optional<MaxAndMinRate> getMaxAndMinAverageExchangeRate(String currencyCode, Integer numberOfLastQuotations) {
        final String URL = String.format(
                "%s%s/%s/last/%d/?format=json",
                nbpConfig.getApiUrl(),
                tableTypeA,
                currencyCode.toLowerCase(),
                numberOfLastQuotations
        );

        try {
            Optional<AverageExchangeRateResponse> responseOptional = Optional.ofNullable(restTemplate.getForObject(URL, AverageExchangeRateResponse.class));

            if(responseOptional.isPresent()) {
                AverageExchangeRateResponse response = responseOptional.get();
                List<Double> values = response.getRates()
                        .stream()
                        .map(AverageExchangeRate::getMid)
                        .toList();

                MaxAndMinRate maxAndMinRate = new MaxAndMinRate(Collections.max(values), Collections.min(values));

                return Optional.of(maxAndMinRate);
            }

        } catch (HttpClientErrorException e) {
            HttpClientErrorExceptionHandler.sendErrorInformation(e.getStatusCode(), LOGGER);
        }

        return Optional.empty();
    }

    public Optional<Double> getMajorDifferenceSpread(String currencyCode, Integer numberOfLastQuotations) {
        final String URL = String.format(
                "%s%s/%s/last/%d/?format=json",
                nbpConfig.getApiUrl(),
                tableTypeC,
                currencyCode.toLowerCase(),
                numberOfLastQuotations
        );

        try {
            Optional<SpreadResponse> responseOptional = Optional.ofNullable(restTemplate.getForObject(URL, SpreadResponse.class));

            if(responseOptional.isPresent()) {
                List<Double> values = responseOptional
                        .get()
                        .getRates()
                        .stream()
                        .map(askBidRate -> Math.abs(askBidRate.getBid() - askBidRate.getAsk()))
                        .toList();

                return Optional.of(Collections.max(values));
            }

        } catch (HttpClientErrorException e) {
            HttpClientErrorExceptionHandler.sendErrorInformation(e.getStatusCode(), LOGGER);
        }

        return Optional.empty();
    }
}
