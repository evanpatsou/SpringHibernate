### What is Spring MVC?

* Framework for building web applications in Java
* Based on Model-View-Controller design pattern
* Leverages features of the Core Spring Framework (IoC, DI)

So basically you have an incoming request coming from the browser, it'll encounter the Spring MVC
front controller. This person will actually delegate the request off to a
controller code. This controller code is code that you create that contains
your business logic. You basically create a model, and you send the model back
to the front controller, and then the front controller, will pass that model over to your view template.
So your view template is basically like a html page, or a JSP page that will take that data, and then render
a response to the browser. So that's kind of the big picture of the MVC framework.

### Spring MVC Benefits

* The Spring way of building web app UIs in Java.
* Leverage a set of reusable UI components
* Help manage application state for web requests
* Process form data: validation, conversion etc
* Flexible configuration for the view layer

### Components of a Spring MVC application

* A set of web pages to layout Ui Components
* A collection of spring beans ( controllers, services, etc...)
* Spring configuration (XML, Annotations or Java)

### Spring MVC Front Controller

* Front controller known as DispatcherServlet
    * Part of the Spring Framework
    * Already developed by Spring Dev TEam
    
* As a developer, you will create:
    * Model objects
    * View templates
    * Controller classes
    
#### Controler 

* Code created by developer

* Contains your business logic
    * Handle the request
    * Store / retrieve data ( db, web service )
    * Place data in model (basically is a container for your data)
    
* Send to appropriate view template


#### Model 

* Model: contains your data

* Store/retrieve data via backend systems
    * database, web services, etc...
    * Use a Spring bean if you like
    
* Place your data in the model
    * Data can be any Java object/collection
    
#### View or Template

* Spring MVC is flexible
    * Supports many view templates
    
* Most common is JSP + JSTL

* Developer creates a page
    * Displays data
    
* Other biew teamplates supported 
    * Thymeleaf, Groovy
    * Velocity, Freemarker, etc...
    
#### STEPS

#### Step1: Configure Spring DispatcherServlet


In this example, we'll make use of /WEB-INF/spring-mvc-demo-servlet.xml.
You can give any name you want, here, as long as you reference it accordingly.

```
file: web.xml

<web-app>    
    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>/WEB-INF/spring-mvc-demo-servlet.xml</param-value>
        </init-param>
        
        <load-on-startup>1</load-on-startup>
    </servlet>
</web-app>
```

So, the next thing we need to do is set up the URL mappings for the Spring MVC DispatcherServlet.
So, basically, what we want to do is tell the system, "Hey, for any URL pattern coming in,
I'd like for you to pass it off to the DispatcherServlet." So, in this case, our URL pattern's going to be slash,
meaning all web requests, coming in, should be handled by the DispatcherServlet.

```
file: web.xml

<web-app>    
    <servlet>
        ...
    </servlet>
    
    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>
```

>*Notice!*
>
>One thing that's important, here, is to know here that servlet name matches with the servlet reference
that you just set up, higher up in this file, or at the top, that was set up in step one,
so those two items have to match up.

#### Component scanning

Add support for component scanning, so in our file, this is our Spring file, spring-mvc-demo-servlet.xml,
we simply add context:component-scan. And, again, this works just like we've learned so far,
that it'll basically scan this package for any special Spring beans, and make them available,
so any @component items out there, it'll make them available,
and put them into the Spring context. Alright, step four, adding support for conversion,
formatting, and validation.

```
<beans>    
    <!-- Step 3: Add support for component scanning -->
    <context:component-scan base-package="packagename" />
    
    <!-- Step 4: Add support for conversion, formatiing and validation support -->
    <mvc:annotation-driven />
    
    <!-- Step 5L define Spring MVC view resolver -->
    <bean
        class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/view/" />
        <property name="suffix" value=".jsp" />
    </bean>
</beans>
```

---

#### Controller Development process

* Annotate class with @Controller
    * @Controller inherits from @Component ... supports scanning
    
All right, so let's go ahead and start with step one, creating the controller class.
Basically, you define a class, and then you annotate it with @Controller.
Now, @Controller basically says that this is a Spring MVC controller,
and also, one thing that's really cool is that @Controller inherits from @Component,
so it's really just a specialized component that supports web MVC.
The nice thing about this is that, when Spring does its component scanning,
then it'll also pick up @Controllers because they inherit or extend from @Component.
So this is the basic process here of defining a controller class.

```
    @Controller
    public class HomeController {
    
    }
```

All right, so let's go ahead and take a look at step two, defining a controller method.
So, here I have a method, public String showMyPage. Now, this is a very simple method.
The actual method name is flexible. You can give any method name that you want.
Here I called it showMyPage, but I could've easily called it foobar. The return type here is String.
I'm simply gonna return the actual view name that I want them to show. But again, this method here is actually very flexible.
So, in Spring, when you define your controller method, you can actually pass in any number of
parameters, like request parameters, session objects, and so on, or model objects.
And you can also return different variations here.

```
    @Controller
    public class HomeController {
    
        public String showMyPage() {
            ...
        }
        
    }
```

All right, so now that we have this method defined, in step three we need to add the request mapping,
so we basically need to map some type of web request to this given method, and we do this
with an annotation, @RequestMapping. So you get the @RequestMapping and you get the actual path that you wanna map.
So, in my example, I simply wanna map on the route, so if they simply go to this website,
then they'll show this page here, or they'll actually call this method here.
And this request mapping will handle all types of requests, get, post, and so on and so forth.

```
    @Controller
    public class HomeController {
    
        @RequestMapping("/")
        public String showMyPage() {
            ...
        }
        
    }
```

All right, so now, inside of the method, with step four, we actually need to return the view name.
So, in this example I'm gonna return "main-menu". That's the name of the view.
Now, remember here, there is some magic that's gonna happen in the background.
Spring's actually gonna use information from its configuration file, and it'll actually find the view page.
So, based on the configs, it'll look in a given prefix directory, and it'll use the view name,
and then it'll pin the suffix ".jsp". So, in this example here, it's gonna look for
`/WEB-INf/view/main-menu.jsp.`

```
    @Controller
    public class HomeController {
    
        @RequestMapping("/")
        public String showMyPage() {
            return "main-menu";
        }
        
    }
```

