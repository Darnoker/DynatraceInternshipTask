package internship.task.dynatracetask.args;

import internship.task.dynatracetask.data.MaxAndMinRate;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class ControllerTestArgs {

    public static Stream<Arguments> testAverageRateArgumentsController() {
        return Stream.of(
                Arguments.of("GBP", "2023-01-03", 5.2911),
                Arguments.of("GBP", "2023-01-02", 5.2768),
                Arguments.of("USD", "2023-01-02", 4.3811),
                Arguments.of("USD", "2023-01-03", 4.4373)
        );
    }

    public static Stream<Arguments> testLastMaxMinArgumentsController() {
        return Stream.of(
                Arguments.of("GBP", 10, new MaxAndMinRate(5.3041, 5.1958)),
                Arguments.of("GBP", 5, new MaxAndMinRate(5.2529, 5.1958)),
                Arguments.of("USD", 10, new MaxAndMinRate(4.2713, 4.1649)),
                Arguments.of("USD", 5, new MaxAndMinRate(4.2244, 4.1649))
        );
    }

    public static Stream<Arguments> testSpreadArgumentsController() {
        return Stream.of(
                Arguments.of("GBP", 10, 0.1062000000000003),
                Arguments.of("GBP", 20, 0.1048),
                Arguments.of("USD", 10, 0.08539999999999992),
                Arguments.of("USD", 20, 0.08440000000000047)
        );
    }
}
