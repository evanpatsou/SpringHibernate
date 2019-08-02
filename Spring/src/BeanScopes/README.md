### Bean Scopes

A scope refers to the lifecycle of a bean, for example,
it tells you how long the bean will live, how many instances will be created and
also how is the bean shared in the Spring environment?
  
So the default scope for a bean is singleton. For example:

```
<beans ...>
    <bean id="myCoach"
            class="com.tatata.TrackCoach">
    ...
    </bean>
</beans>
```

Well, for a singleton, the Spring Container creates only one instance of the bean, 
it's cached in memory and then all requests for that bean will return a 
shared reference to the same bean, so the end result is that there is only one bean
and everyone will share it.

### Explicitly Specify Bean Scope

```
<beans ...>
    <bean id="myCoach"
            class="com.tatata.TrackCoach"
            scope="singleton" >
    ...
    </bean>
</beans>
```

| Scope          | Description                                                 | 
| :------------- |:------------------------------------------------------------| 
| singleton      | Create a single shared instance of the bean. Default Scope. |
| prototype      | Creates a new bean instance for each container request.     | 
| request        | Scope to an HTTP web request. Only used for web apps.       |
| session        | Scoped to an HTTP web session. Only used for web apps.      |
| global-session | Scoped to global HTTP web session. Only used for web apps.  |