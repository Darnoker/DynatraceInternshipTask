package internship.task.dynatracetask.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class AverageExchangeRate {

    private Double mid;

    public AverageExchangeRate(Double mid) {
        this.mid = mid;
    }
}
