package MatchASingle.aopdemo;

import MatchASingle.aopdemo.dao.AccountDAO;
import MatchASingle.aopdemo.dao.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp {

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context =
                                            new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean from spring container
        AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);

        // get membership bean from spring container
        MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);

        // call the Account business method
        theAccountDAO.addAccount();

        // Call the Membership business method one more time
        theMembershipDAO.addAccount();

        // close the context
        context.close();
    }

}
