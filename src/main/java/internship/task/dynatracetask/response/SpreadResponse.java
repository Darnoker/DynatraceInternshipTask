package internship.task.dynatracetask.response;

import internship.task.dynatracetask.data.SpreadRate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class SpreadResponse implements NbpResponse<SpreadRate> {

    private List<SpreadRate> rates;

    public SpreadResponse(List<SpreadRate> rates) {
        this.rates = rates;
    }
}
