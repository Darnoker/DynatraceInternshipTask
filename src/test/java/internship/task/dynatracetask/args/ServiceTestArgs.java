package internship.task.dynatracetask.args;

import internship.task.dynatracetask.data.AverageExchangeRate;
import internship.task.dynatracetask.data.MaxAndMinRate;
import internship.task.dynatracetask.data.SpreadRate;
import internship.task.dynatracetask.response.AverageExchangeRateResponse;
import internship.task.dynatracetask.response.SpreadResponse;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

public class ServiceTestArgs {

    public static Stream<Arguments> testAverageRateArgumentsService() {
        return Stream.of(
                Arguments.of(
                        "GBP",
                        "2023-01-03",
                        new AverageExchangeRateResponse(
                                List.of(new AverageExchangeRate(5.2911))
                        ),
                        5.2911
                ),
                Arguments.of(
                        "GBP",
                        "2023-01-02",
                        new AverageExchangeRateResponse(
                                List.of(new AverageExchangeRate(5.2768))
                        ),
                        5.2768
                ),
                Arguments.of(
                        "USD",
                        "2023-01-02",
                        new AverageExchangeRateResponse(
                                List.of(new AverageExchangeRate(4.3811))
                        ),
                        4.3811
                ),
                Arguments.of(
                        "USD",
                        "2023-01-03",
                        new AverageExchangeRateResponse(
                                List.of(new AverageExchangeRate(4.4373))
                        ),
                        4.4373
                )
        );
    }

    public static Stream<Arguments> testLastMaxMinArgumentsService() {
        return Stream.of(
                Arguments.of(
                        "GBP",
                        10,
                        new AverageExchangeRateResponse(
                                List.of(
                                        new AverageExchangeRate(5.3041),
                                        new AverageExchangeRate(5.2874),
                                        new AverageExchangeRate(5.2565),
                                        new AverageExchangeRate(5.231),
                                        new AverageExchangeRate(5.2428),
                                        new AverageExchangeRate(5.2529),
                                        new AverageExchangeRate(5.2296),
                                        new AverageExchangeRate(5.1958)
                                )
                        ),
                        new MaxAndMinRate(5.3041, 5.1958)
                ),

                Arguments.of(
                        "GBP",
                        5,
                        new AverageExchangeRateResponse(
                                List.of(
                                        new AverageExchangeRate(5.2529),
                                        new AverageExchangeRate(5.2296),
                                        new AverageExchangeRate(5.2086),
                                        new AverageExchangeRate(5.2176),
                                        new AverageExchangeRate(5.1958)
                                )
                        ),
                        new MaxAndMinRate(5.2529, 5.1958)
                ),

                Arguments.of(
                        "USD",
                        10,
                        new AverageExchangeRateResponse(
                                List.of(
                                        new AverageExchangeRate(4.2713),
                                        new AverageExchangeRate(4.2225),
                                        new AverageExchangeRate(4.2042),
                                        new AverageExchangeRate(4.2261),
                                        new AverageExchangeRate(4.2151),
                                        new AverageExchangeRate(4.2151),
                                        new AverageExchangeRate(4.2244),
                                        new AverageExchangeRate(4.2024),
                                        new AverageExchangeRate(4.2006),
                                        new AverageExchangeRate(4.1905),
                                        new AverageExchangeRate(4.1649)
                                )
                        ),
                        new MaxAndMinRate(4.2713, 4.1649)
                ),
                Arguments.of(
                        "USD",
                        5,
                        new AverageExchangeRateResponse(
                                List.of(
                                        new AverageExchangeRate(4.2244),
                                        new AverageExchangeRate(4.2024),
                                        new AverageExchangeRate(4.2006),
                                        new AverageExchangeRate(4.1905),
                                        new AverageExchangeRate(4.1649)
                                )
                        ),
                        new MaxAndMinRate(4.2244, 4.1649)
                )
        );
    }

    public static Stream<Arguments> testSpreadArgumentsService() {
        return Stream.of(
                Arguments.of(
                        "GBP",
                        10,
                        new SpreadResponse(
                                List.of(
                                        new SpreadRate(5.2581, 5.3643),
                                        new SpreadRate(5.2405, 5.3463),
                                        new SpreadRate(5.1984, 5.3034),
                                        new SpreadRate(5.1903, 5.2951),
                                        new SpreadRate(5.1908, 5.2956),
                                        new SpreadRate(5.1908, 5.2956),
                                        new SpreadRate(5.1877, 5.2925),
                                        new SpreadRate(5.1883, 5.2931),
                                        new SpreadRate(5.1706, 5.275),
                                        new SpreadRate(5.1706, 5.275),
                                        new SpreadRate(5.154, 5.2582),
                                        new SpreadRate(5.1448, 5.2488)
                                )
                        ),
                        0.1062000000000003
                ),
                Arguments.of(
                        "GBP",
                        5,
                        new SpreadResponse(
                                List.of(
                                        new SpreadRate(5.1877, 5.2925),
                                        new SpreadRate(5.1883, 5.2931),
                                        new SpreadRate(5.1706, 5.275),
                                        new SpreadRate(5.154, 5.2582),
                                        new SpreadRate(5.1448, 5.2488)
                                )
                        ),
                        0.1048
                ),
                Arguments.of(
                        "USD",
                        10,
                        new SpreadResponse(
                                List.of(
                                        new SpreadRate(4.2298, 4.3152),
                                        new SpreadRate(4.2066, 4.2916),
                                        new SpreadRate(4.1493, 4.2331),
                                        new SpreadRate(4.1626, 4.2466),
                                        new SpreadRate(4.1919, 4.2765),
                                        new SpreadRate(4.1769, 4.2613),
                                        new SpreadRate(4.1677, 4.2519),
                                        new SpreadRate(4.1532, 4.2372),
                                        new SpreadRate(4.1629, 4.2471),
                                        new SpreadRate(4.1329, 4.2163)
                                )
                        ),
                        0.08539999999999992
                ),
                Arguments.of(
                        "USD",
                        5,
                        new SpreadResponse(
                                List.of(
                                        new SpreadRate(4.1769, 4.2613),
                                        new SpreadRate(4.1677, 4.2519),
                                        new SpreadRate(4.1532, 4.2372),
                                        new SpreadRate(4.1629, 4.2471),
                                        new SpreadRate(4.1329, 4.2163)
                                )
                        ),
                        0.08440000000000047
                )
        );
    }
}
