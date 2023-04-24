package internship.task.dynatracetask.service;

import internship.task.dynatracetask.config.NbpConfig;
import internship.task.dynatracetask.response.AverageExchangeRateResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
}
