package internship.task.dynatracetask.service;

import internship.task.dynatracetask.config.NbpConfig;
import internship.task.dynatracetask.data.AverageExchangeRate;
import internship.task.dynatracetask.data.MaxAndMinRate;
import internship.task.dynatracetask.data.SpreadRate;
import internship.task.dynatracetask.response.AverageExchangeRateResponse;
import internship.task.dynatracetask.response.SpreadResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class NbpService {

    private final NbpConfig nbpConfig;
    private final RestTemplate restTemplate;

    public NbpService(NbpConfig nbpConfig, RestTemplate restTemplate) {
        this.nbpConfig = nbpConfig;
        this.restTemplate = restTemplate;
    }

    public Optional<Double> getAverageExchangeRate(String currencyCode, String date) {
        final String URL = String.format(
                "%sa/%s/%s/",
                nbpConfig.getApiUrl(),
                currencyCode.toLowerCase(),
                date
        );

        Optional<AverageExchangeRateResponse> responseOptional = Optional.ofNullable(restTemplate.getForObject(URL, AverageExchangeRateResponse.class));

        if(responseOptional.isPresent()) {
            AverageExchangeRateResponse response = responseOptional.get();
            if(!response.getRates().isEmpty()) {
                return Optional.of(response.getRates().get(0).getMid());
            }
        }

        return Optional.empty();
    }

    public Optional<MaxAndMinRate> getMaxAndMinRate(String currencyCode, Integer numberOfLastQuotations) {
        final String URL = String.format(
                "%sa/%s/last/%d/?format=json",
                nbpConfig.getApiUrl(),
                currencyCode.toLowerCase(),
                numberOfLastQuotations
        );

        Optional<AverageExchangeRateResponse> responseOptional = Optional.ofNullable(restTemplate.getForObject(URL, AverageExchangeRateResponse.class));

        if(responseOptional.isPresent()) {
            AverageExchangeRateResponse response = responseOptional.get();
            List<Double> values = response.getRates()
                    .stream()
                    .map(AverageExchangeRate::getMid)
                    .toList();

            double maxMidValue = Collections.max(values);
            double minMidValue = Collections.min(values);
            MaxAndMinRate maxAndMinRate = new MaxAndMinRate(maxMidValue, minMidValue);

            return Optional.of(maxAndMinRate);
        }

        return Optional.empty();
    }

    public Optional<Double> getMajorDifferenceSpread(String currencyCode, Integer numberOfLastQuotations) {
        final String URL = String.format(
                "%sc/%s/last/%d/?format=json",
                nbpConfig.getApiUrl(),
                currencyCode.toLowerCase(),
                numberOfLastQuotations
        );

        Optional<SpreadResponse> responseOptional = Optional.ofNullable(restTemplate.getForObject(URL, SpreadResponse.class));

        if(responseOptional.isPresent()) {
             ;
            List<Double> values = responseOptional
                    .get()
                    .getRates()
                    .stream()
                    .map(askBidRate -> Math.abs(askBidRate.getBid() - askBidRate.getAsk()))
                    .toList();

            return Optional.of(Collections.max(values));
        }
        return Optional.empty();
    }
}
