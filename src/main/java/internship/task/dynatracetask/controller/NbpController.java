package internship.task.dynatracetask.controller;

import internship.task.dynatracetask.data.MaxAndMinRate;
import internship.task.dynatracetask.service.NbpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/exchange")
public class NbpController {

    private final NbpService nbpService;

    public NbpController(NbpService nbpService) {
        this.nbpService = nbpService;
    }

    @GetMapping(value = "/{currencyCode}/{date}")
    public ResponseEntity<Double> getExchangeRate(@PathVariable String currencyCode, @PathVariable String date) {
        return nbpService
                .getAverageExchangeRate(currencyCode, date)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/last/average-rate/{currencyCode}/{numberOfLastQuotations}")
    public ResponseEntity<MaxAndMinRate> getMaxAndMinRate(@PathVariable String currencyCode, @PathVariable Integer numberOfLastQuotations) {
        return nbpService
                .getMaxAndMinRate(currencyCode, numberOfLastQuotations)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/last/spread/{currencyCode}/{numberOfLastQuotations}")
    public ResponseEntity<Double> getMajorDifferenceAskBid(@PathVariable String currencyCode, @PathVariable Integer numberOfLastQuotations) {
        return nbpService
                .getMajorDifferenceSpread(currencyCode, numberOfLastQuotations)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
