package JavaAnnotationsDependencyInjection.ConstructorInjection;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConstructorInjectionDemo {
    public static void main(String[] args) {
        // read spring config file
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("./JavaAnnotationsDependencyInjection/ConstructorInjection/applicationContext.xml");

        // get the bean from spring container
        Coach theCoach = context.getBean("tennisCoach", Coach.class);

        // call a method on the bean
        System.out.println(theCoach.getDailyWorkout());

        // call the method to get the daily fortune
        System.out.println(theCoach.getDailyFortune());

        // close context
        context.close();
    }
}
