# DynatraceInternshipTask
Written in Java Spring Boot with Maven.

### Main classes
The classes where the main functionality is, are `NbpExchangeRateService` and `NbpExchangeRateController`

### Starting app
Use `./mvnw spring-boot:run`

### Example endpoints
htttp://localhost:8080/exchange/GBP/2023-01-02 - for getting average exchange rate for specified date.
htttp://localhost:8080/exchange/last/max-and-min-average-rate/GBP/10 - maximum and minimum average exchange rate value for last N days.
htttp://localhost:8080/exchange/last/spread/GBP/10 - major difference spread value for last N days.
