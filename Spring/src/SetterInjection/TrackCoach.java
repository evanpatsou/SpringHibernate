package SetterInjection;

public class TrackCoach implements Coach {

    // Define a private field for the dependency
    private FortuneService fortuneService;


    // Define a constructor for dependency injection
    public TrackCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k";
    }

    @Override
    public String getDailyFortune() {

        // Use my fortuneService to get a fortune
        return fortuneService.getFortune();
    }
}
