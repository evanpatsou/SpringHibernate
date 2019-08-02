#### What happens if there's multiple implementations of a given interface?

So, if we had multiple FortuneService implementations like the HappyFortuneService,
and then what if we had like maybe a RandomFortuneService?
We get real fancy with a DatabaseFortuneService. And then maybe a RESTFortuneService.

How will Spring know which one to pick? Well, if you actually had all those implementations
out there, you'd actually have a problem. And this is the error message that Spring will
give you. Spring will say, hey, there was an error creating a bean named tennisCoach.
The injection of the autowired dependencies failed. And this was caused by NoUniqueBeanDefinitionException.
So basically, it says that we expected a single match of the bean, but we found four implementations.

Therefore, in order to resolve this, you need to be able to give Spring a unique bean,
all right. So Spring can't figure it out on it's own because there's more than one.
So you have to kind of be very specific, and you have to qualify or tell Spring which
specific bean you want them to use. So what you'll do is actually make use of this 
annotation called Qualifier.

#### Using @Qualifier with Constructors
_@Qualifier is a nice feature, but it is tricky when used with Constructors._

The syntax is much different from other examples and not exactly intuitive.  Consider this the "deep end of the pool" when it comes to Spring configuration LOL :-)

 You have to place the @Qualifier annotation inside of the constructor arguments. 

Here's an example from our classroom example. I updated it to make use of constructor injection, with @Autowired and @Qualifier. Make note of the code in bold below:

```
@Component
public class TennisCoach implements Coach {

    private FortuneService fortuneService;

    // define a default constructor
    public TennisCoach() {
        System.out.println(">> TennisCoach: inside default constructor");
    }
    
    @Autowired
    public TennisCoach(@Qualifier("randomFortuneService") FortuneService theFortuneService) {

        System.out.println(">> TennisCoach: inside constructor using @autowired and @qualifier");
        
        fortuneService = theFortuneService;
    }
        
    
    /*
    @Autowired
    public void doSomeCrazyStuff(FortuneService theFortuneService) {
        System.out.println(">> TennisCoach: inside doSomeCrazyStuff() method");
        fortuneService = theFortuneService;
    }
    */
    
    /*
    @Autowired
    public TennisCoach(FortuneService theFortuneService) {
        fortuneService = theFortuneService;
    }
    */
    
    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }

}
```

For detailed documentation on using @Qualified with Constructors, see this link in the Spring Reference Manual

https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-autowired-annotation-qualifiers


