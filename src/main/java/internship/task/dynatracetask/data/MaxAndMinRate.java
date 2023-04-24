package internship.task.dynatracetask.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class MaxAndMinRate {

    private double max;
    private double min;

    public MaxAndMinRate(double max, double min) {
        this.max = max;
        this.min = min;
    }
}
