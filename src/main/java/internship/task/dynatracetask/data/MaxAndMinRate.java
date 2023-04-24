package internship.task.dynatracetask.data;

import lombok.Getter;

@Getter
public class MaxAndMinRate {

    private double max;
    private double min;

    public MaxAndMinRate(double max, double min) {
        this.max = max;
        this.min = min;
    }
}
