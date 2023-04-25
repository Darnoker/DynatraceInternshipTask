package internship.task.dynatracetask.data;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SpreadRate {

    private Double bid;
    private Double ask;

    public SpreadRate(Double bid, Double ask) {
        this.bid = bid;
        this.ask = ask;
    }
}
