### Setter Injection

So with setter injection, this is where the spring framework will inject your dependencies by calling
setter methods on your class. So looking at the development process, The first thing we'll have to do,
or the first step we'll have is creating the setter methods in our class for injections. Then the second
step is configuring the dependency in the Spring configuration file

So CricketCoach, they'll have a private field, they'll have a no-argument constructor, and then we'll 
have a setter method for setFortuneService. This is the method that will be called by Spring when they
inject the dependency using setter injection. So in step two, we have to configure the dependency 
injection in our Spring config file.