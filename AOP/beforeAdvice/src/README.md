## @Before Advice

So with the Before advice, we're going to have our main application that's going to call a Target Object,
and what we'd like to do is, for our main app, it's going to call a method.
It'll say targetObj.doSomeStuff. We want to have our own custom code execute before the actual method.

So that's the basic idea of putting in a logging advice. This code will run before the actual method executes.
All right, so let's take a look at the Advice Interaction. We have our target object.
we have this method called doSomeStuff. What we'd like to do is basically add some
codes to run before this method. And in order to do that, we make use of this
@Before advice type. 
This allows us to inject our own custom code that will execute before the doSomeStuff method executes.
And then, likewise, we can also make use of the after returning. This is more like a bonus, didn't plan on doin' this,
but after returning, we'll actually show you or allow you to inject custom code after the method returns.
So you can imagine, like for logging, you could log some data before,
and you could also log data after the method process, maybe for de-bugging, or logging purposes, or whatever.
Therefore, you can handle @Before, and you add code for after returning.