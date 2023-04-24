package internship.task.dynatracetask.args;

import internship.task.dynatracetask.data.MaxAndMinRate;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class TestArgs {

    public static Stream<Arguments> testAverageRateArguments() {
        return Stream.of(
                Arguments.of("GBP", "2023-01-03", 5.2911),
                Arguments.of("GBP", "2023-01-02", 5.2768),
                Arguments.of("USD", "2023-01-02", 4.3811),
                Arguments.of("USD", "2023-01-03", 4.4373)
        );
    }

    public static Stream<Arguments> testLastMaxMinArguments() {
        return Stream.of(
                Arguments.of("GBP", 10, new MaxAndMinRate(5.3369, 5.2086)),
                Arguments.of("GBP", 5, new MaxAndMinRate(5.2529, 5.2086)),
                Arguments.of("USD", 10, new MaxAndMinRate(4.2917, 4.1905)),
                Arguments.of("USD", 5, new MaxAndMinRate(4.2244, 4.1905))
        );
    }

    public static Stream<Arguments> testSpreadArguments() {
        return Stream.of(
                Arguments.of("GBP", 10, 0.10679999999999978),
                Arguments.of("GBP", 20, 0.1070000000000002),
                Arguments.of("USD", 10, 0.08579999999999988),
                Arguments.of("USD", 20, 0.08720000000000017)
        );
    }
}
