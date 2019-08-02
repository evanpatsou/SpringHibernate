### Inversion Of Control (IoC)

> The approach of outsourcing the construction and management of objects

Well, it's simply the design process of externalizing, the construction and management of
your objects. So, in a nutshell it basically says that your application's gonna outsource
the creation and management of the objects, and that outsourcing will be handled by a
object factory, and that's the big idea of inversion of control.

#### Coding Scenario 
We're gonna have our app that will make use of a coach like 
a baseball coach and so our app will say hey, baseball coach, give me a daily workout,
just so you find out what type of workout you need to perform at practice.

* App should be configurable
* Easily change the coach for another sport
    * Hockey, Tennis, Gymnastics etc.
