package JavaAnnotationBeanLifecycle;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JavaAnnotationBeanLifecycleDemo {
    public static void main(String[] args) {
        // load spring config file
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("./JavaAnnotationBeanLifecycle/applicationContext.xml");

        // get the bean from spring container
        Coach theCoach   = context.getBean("tennisCoach", Coach.class);

        // call some of the funciton
        System.out.println(theCoach.getDailyWorkout());
        System.out.println(theCoach.getDailyFortune());

        // close context
        context.close();
    }
}
