package internship.task.dynatracetask.response;

import internship.task.dynatracetask.data.AverageExchangeRate;
import lombok.Getter;

import java.util.List;

@Getter
public class AverageExchangeRateResponse {

    private List<AverageExchangeRate> rates;
}
