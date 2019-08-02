#### So what exactly are Java annotations?

Well all they are, they're simply special or markers that are added to Java classes
and they actually give you meta-data about the class.

```
public class TrackCoach implements Coach {
   
   // Annotation --> Override
    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k";
    }
}
```

So what this annotation does is it basically tells the compiler, hey,
we're gonna implement a given interface, or extend a class, and we're gonna 
override the method. So when you give that annotation of at override,
you're basically telling the compiler, hey, we're compliant, we're gonna
override the method exactly as listed in the interface or the parent class. 
Now, what happens at compilation time is that the compiler will check your class 
and make sure that you really are overriding the method. So they will verify ,
or perform an audit check on your class. However, if there are any problems,
then the compiler will say, hey you're not really overriding the method as you stated
 that you were, and it'll actually give you a compilation error.
 
 #### Why Spring Configuration with Annotations?
 
 * XML configuration can be verbose
 * Configure your Spring beans with Annotations.
 * Annotations minimizes the XML configuration
 
 #### How it works?
 
Well basically once you add an annotation to a class, then Spring will actually scan
your Java classes for those annotations. When it finds a class that has a special
Spring annotation on it, it'll automatically register that bean with the Spring
container. So instead of doing everything long hand via XML config file,
Spring will just scan and say, oh that's a Spring bean, let me grab that bean
and let me register it with the Spring container. So Spring will kinda help you out
here and do a lot of work for you in the background by scanning your classes.