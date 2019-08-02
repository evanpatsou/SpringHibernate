#### What is Spring AutoWiring?

Well, for dependency injection, Spring can automatically wire up your objects together,
so basically what'll happen is that Spring will look for a class that matches a given property.
And it'll actually match by type, so the type could be either the class or the interface.
Once Spring finds a match, then it'll automatically inject it.

#### Autowiring example

* Injecting FortuneService into a Coach implementation
* Spring will scan @Components
* Any one implements FortuneService interface
* If so, let's inject them. For example: _HappyFortuneService_

In our examples that we're gonna work through, we're gonna actually have a happy FortuneService
which is an implementation of the FortuneService interface and Spring will find that implementation
and automatically inject it into our class and that's called Autowiring.

#### Which one should I use?

* Constructor injection
* Setter injection
* Field injection
* Method injection

Choose a style and stay consistent within your project, alright.
And, that'll make things easy for you, but also make it easy for other team members on your project
if it's one consistent approach throughout. Now, you may wonder, is one injection type better
than the other? Well, you get the same functionality regardless of either injection type that you use.
So, constructor, setter, field you get the same functionality.

#### Annotations - Default Bean Names - The Special Case

In general, when using Annotations, for the default bean name, Spring uses the following rule.
If the annotation's value doesn't indicate a bean name, an appropriate name will be built based on the short name of the class (with the first letter lower-cased).

For example:

HappyFortuneService --> happyFortuneService

However, for the special case of when BOTH the first and second characters of the class name are upper case, then the name is NOT converted.
For the case of RESTFortuneService

RESTFortuneService --> RESTFortuneService

No conversion since the first two characters are upper case.
Behind the scenes, Spring uses the Java Beans Introspector to generate the default bean name. Here's a screenshot of the documentation for the key method.


Also, here's a link to the documentation.

- https://docs.oracle.com/javase/8/docs/api/java/beans/Introspector.html#decapitalize(java.lang.String)

---

As always, you can give explicity names to your beans.

@Component("foo")
public class RESTFortuneService .... {
    
}

Then you can access it using the name of "foo". Nothing tricky to worry about :-)
Hope this helps. Happy Coding! :-)