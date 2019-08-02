package MatchTheReturnType.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    // This is where we add all of our related advices for logging

    // let's start with an @Before advice

    //So that's what we have to match on.
    //
    //So any call sum method with add star
    //
    //will match on that method if it has a return type of void.

    @Before("execution(void add*())")
    public void beforeAddAccountAdvice() {
        System.out.println("\nExecuting @Before advice on addAccount()");
    }
}
