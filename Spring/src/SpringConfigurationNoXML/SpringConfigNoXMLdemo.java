package SpringConfigurationNoXML;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringConfigNoXMLdemo {
    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SportConfig.class);

        // get the bean from spring container
        SwimCoach theCoach = context.getBean("swimCoach", SwimCoach.class);

        // call a method on the bean
        System.out.println(theCoach.getDailyWorkout());

        // call the method to get the daily fortune
        System.out.println(theCoach.getDailyFortune());

        // call the literal fields
        System.out.println("Email: " + theCoach.getEmail());
        System.out.println("Team: " + theCoach.getTeam());

        // close context
        context.close();
    }
}
