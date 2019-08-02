package JavaAnnotationsIoC;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JavaAnnotationsIoCDemo {
    public static void main(String[] args) {
        // read spring config file
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("./JavaAnnotationsIoC/applicationContext.xml");

        // get the bean from spring container
        Coach theCoach = context.getBean("thatSillyCoach", Coach.class);

        // call a method on the bean
        System.out.println(theCoach.getDailyWorkout());

        // close context
        context.close();
    }
}
