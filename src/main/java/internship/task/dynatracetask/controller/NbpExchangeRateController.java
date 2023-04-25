package internship.task.dynatracetask.controller;

import internship.task.dynatracetask.data.MaxAndMinRate;
import internship.task.dynatracetask.service.NbpExchangeRateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/exchange")
public class NbpExchangeRateController {

    private final NbpExchangeRateService nbpExchangeRateService;

    public NbpExchangeRateController(NbpExchangeRateService nbpExchangeRateService) {
        this.nbpExchangeRateService = nbpExchangeRateService;
    }

    @GetMapping(value = "/{currencyCode}/{date}")
    public ResponseEntity<Double> getExchangeRate(@PathVariable String currencyCode, @PathVariable String date) {
        return nbpExchangeRateService
                .getAverageExchangeRate(currencyCode, date)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/last/average-rate/{currencyCode}/{numberOfLastQuotations}")
    public ResponseEntity<MaxAndMinRate> getMaxAndMinAverageExchangeRate(@PathVariable String currencyCode, @PathVariable Integer numberOfLastQuotations) {
        return nbpExchangeRateService
                .getMaxAndMinAverageExchangeRate(currencyCode, numberOfLastQuotations)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/last/spread/{currencyCode}/{numberOfLastQuotations}")
    public ResponseEntity<Double> getMajorDifferenceSpreadRate(@PathVariable String currencyCode, @PathVariable Integer numberOfLastQuotations) {
        return nbpExchangeRateService
                .getMajorDifferenceSpreadRate(currencyCode, numberOfLastQuotations)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
