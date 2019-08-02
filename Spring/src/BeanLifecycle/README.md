### Bean Lifecycle

First off, the beans are instantiated, and then the actual dependencies are injected,
next you have some internal Spring processing that occurs with the bean factory,
and then you have the option of adding your own custom initialization code,
and then at that point the bean is ready for use. So you can call methods on it,
do work with the bean, so on and so forth. At a certain point the containers
actually shutdown meaning your application is shutdown like what context.close,
then you also have a chance to call your custom destroy method, and that code'll 
execute before the actual application is stopped or before the actual beans lifecycle
is over.

| Step # |                   Title                      |
| :----: |:---------------------------------------------|
| 1      | Container Started                            |
| 2      | Bean Instantiated                            |
| 3      | Dependencies Injected                        |
| 4      | Internal Spring Processing                   |
| 5      | Your custom Init Methdod                     |
| 6      | Bean is Ready to use. Container is Shutdown  |

### Bean Lifecycle Methods/Hooks

* You can add custom code during *bean initialization*
    * Calling custom business logic methods
    * Setting up handles to resources (db, sockets, file etc)
    
```
<beans ...>
    <bean id="myCoach"
            class="com.tatata.TrackCoach"
            init-method="doMyStartupStuff" 
            destroy-method="doMyCleanupMethod" >
    ...
    </bean>
</beans>
```

---

#### Special Note: Defining init and destroy methods - Method Signatures

##### Special Note about init and destroy Method Signatures

When using XML configuration, I want to provide additional details regarding the method signatures of the init-method  and destroy-method .

##### Access modifier
The method can have any access modifier (public, protected, private)

##### Return type
The method can have any return type. However, "void' is most commonly used. If you give a return type just note that you will not be able to capture the return value. As a result, "void" is commonly used.

##### Method name
The method can have any method name.

##### Arguments
The method can not accept any arguments. The method should be no-arg.

---

##### Special Note about Destroy Lifecycle and Prototype Scope
There is a subtle point you need to be aware of with "prototype" scoped beans.

*For "prototype" scoped beans, Spring does not call the destroy method.  Gasp!* 

In contrast to the other scopes, Spring does not manage the complete lifecycle of a prototype bean: the container instantiates, configures, and otherwise assembles a prototype object, and hands it to the client, with no further record of that prototype instance.

Thus, although initialization lifecycle callback methods are called on all objects regardless of scope, in the case of prototypes, configured destruction lifecycle callbacks are not called. The client code must clean up prototype-scoped objects and release expensive resources that the prototype bean(s) are holding. 

This also applies to both XML configuration and Annotation-based configuration.