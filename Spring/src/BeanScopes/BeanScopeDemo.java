package BeanScopes;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemo {

    public static void main(String[] args) {

        // load the spring configuration file
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("./BeanScopes/beanScope-applicationContext.xml");

        // retrieve bean from spring container
        Coach theCoach = context.getBean("myCoach", Coach.class);
        Coach alphaCoach = context.getBean("myCoach", Coach.class);

        // Check if they are the same beans by comparing their references
        boolean result = ( theCoach == alphaCoach);

        // Print out the results
        System.out.println("The references are the same? " + result);
        System.out.println("The memory location for theCoach: " + theCoach);
        System.out.println("The memory location for alphaCoach: " + alphaCoach);
    }
}
