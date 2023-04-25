package internship.task.dynatracetask.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class MaxAndMinRate {

    private Double max;
    private Double min;

    public MaxAndMinRate(double max, double min) {
        this.max = max;
        this.min = min;
    }
}
