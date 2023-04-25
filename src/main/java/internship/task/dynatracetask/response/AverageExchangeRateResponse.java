package internship.task.dynatracetask.response;

import internship.task.dynatracetask.data.AverageExchangeRate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class AverageExchangeRateResponse implements NbpResponse<AverageExchangeRate> {

    private List<AverageExchangeRate> rates;

    public AverageExchangeRateResponse(List<AverageExchangeRate> rates) {
        this.rates = rates;
    }
}
