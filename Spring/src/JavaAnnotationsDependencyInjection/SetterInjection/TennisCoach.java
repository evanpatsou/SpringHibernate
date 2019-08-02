package JavaAnnotationsDependencyInjection.SetterInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {

    private FortuneService fortuneService;

    // Define default constructor
    public TennisCoach() {
        System.out.println("TennisCoach: inside the default constructor");
    }

    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley.";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }

    @Autowired
    public void setFortuneService(FortuneService fortuneService) {
        System.out.println("TennisCoach: inside the setFortuneService method");
        this.fortuneService = fortuneService;
    }
}
