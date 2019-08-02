package JavaAnnotationBeanLifecycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

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

    // define an init method
    @PostConstruct
    public void doMyStartupStuff() {
        System.out.println("TennisCoach: inside method doMyStartupStuff");
    }

    // define a destroy method
    @PreDestroy
    public void doMyCleanupStuff() {
        System.out.println("TennisCoach: inside method doMyCleanupStuff");
    }
}
