package internship.task.dynatracetask.response;

import internship.task.dynatracetask.data.SpreadRate;
import lombok.Getter;

import java.util.List;

@Getter
public class SpreadResponse {

    private List<SpreadRate> rates;
}
