package InversionOfControl.SpringIoC;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringApp {
    public static void main(String[] args) {

        // Load the spring configuration file
        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext("InversionOfControl/SpringIoC/applicationContext.xml");

        // retrieve bean from spring container
        Coach theCoach = context.getBean("myCoach", Coach.class);

        // call methods on the bean
        System.out.println(theCoach.getDailyWorkout());

        // close the context
        context.close();
    }
}
