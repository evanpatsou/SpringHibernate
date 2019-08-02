#### Bean Lifecycle

In Spring, you can actually add some custom code that will execute during bean initialization,
but you can also add code to run during bean destruction.

And the very basic development process here that you simply define your methods
for initialization and destroy and then all you do is you add annotations to those method
and the annotation that you'll use is @PostConstruct and @PreDestroy.

So, for initialization, you can choose any method that you want, give it any method name,
and you simply annotate it with @PostConstruct. So, as the annotation says, this code will execute
after the bean has been constructed.

And likewise, you can actually write some custom code here for destroy.
So, before your bean is destroyed you can annotate a method with @PreDestroy
and then you can write your own custom clean-up code to execute.

*Special Note about @PostConstruct and @PreDestroy Method Signatures*
---

I want to provide additional details regarding the method signatures of @PostContruct and @PreDestroy methods.

*Access modifier*

The method can have any access modifier (public, protected, private)

*Return type*

The method can have any return type. However, "void' is most commonly used. If you give a return type
just note that you will not be able to capture the return value. As a result, "void" is commonly used.

*Method name*

The method can have any method name.

*Arguments*

The method can not accept any arguments. The method should be no-arg.

### Special Note about Destroy Lifecycle and Prototype Scope

Here is a subtle point you need to be aware of with "prototype" scoped beans.

*For "prototype" scoped beans, Spring does not call the @PreDestroy method.  Gasp!*

https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-scopes-prototype

---

In contrast to the other scopes, Spring does not manage the complete lifecycle of a
prototype bean: the container instantiates, configures, and otherwise assembles a
prototype object, and hands it to the client, with no further record of that prototype
instance. 

Thus, although initialization lifecycle callback methods are called on all objects regardless of scope, in the case of prototypes, configured destruction lifecycle callbacks are not called. The client code must clean up prototype-scoped objects and release expensive resources that the prototype bean(s) are holding. 

To get the Spring container to release resources held by prototype-scoped beans, try using a custom bean post-processor, which holds a reference to beans that need to be cleaned up.

---

This also applies to XML configuration.