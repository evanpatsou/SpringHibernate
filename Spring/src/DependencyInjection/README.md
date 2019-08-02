### Dependency Injection

> The dependency inversion principle.
>
> The client delegates to calls to another object the responsibility of providing its dependencies.

Let's assume that I'm going to buy a car and this car is built at the factory on demand. 
So, there's nothing in the car lot. You have to actually talk to the factory and put in a request and
they'll build a car for you; Therefore, at the factory, you have all the different parts for the car. 
You have the car chassis, you have the engine, the tires, the seats, the electronics, the exhaust, 
and so on. And the mechanics or the assemblers there or the technicians, they'll actually assemble 
the car for you and then deliver to you the final car. So, you don't have to actually build the car.
The car's already built for you at the factory. So, they actually inject all of the dependencies
for the car.

_"Dependency" same thing as "helper objects"._

##### So how does it work in the Spring World?

Spring has an object factory,  which retrieves an object  like a coach object, and this coach object
may have some addition dependencies. Due to the fact that these dependencies are really just helper objects,
other objects that it need to perform it's operation. Which implies that instead of manually build 
the coach object and all of it's dependencies, the Spring framework of the Spring factory will
actually do this work for you.

---
FAQ: What is the purpose for the no arg constructor?

Question:
I was wondering why you created a no arg constructor? I thought that they are implied by Java and only required when you also have an overloaded constructor. Or is this a Spring specific thing?

Answered by: Oleksandr Palamarchuk

When you don’t define any constructor in your class, compiler defines default one for you, however when you declare any constructor (in your example you have already defined a parameterized constructor), compiler doesn’t do it for you.

Since you have defined a constructor in class code, compiler didn’t create default one. While creating object you are invoking default one, which doesn’t exist in class code. Then the code gives an compilation error.