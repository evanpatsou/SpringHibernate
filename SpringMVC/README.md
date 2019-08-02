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

--- 

#### Spring Model

* The *Model* is a container for your application data

* In your Controller
    * You can put anything in the *model*
    * strings, objects, info from database, etc...
    
* Your View page (JSP) can access data from the *model*

##### Code example:

* We want to create a new method to process form data
* Read the form data: student's name.
* Convert the name to upper case.
* Add the uppercase version to the model.

---

### Request Params and Request Mappings

*Instead of using HttpServletRequest*

```
    @RequestMapping("/processFormVersionTwo")
    public String letsShoutDude(HttpServletRequest request, Model model) {
        // read the request parameter from the HTML form
        String theName = request.getParameter("studentName");
        
        ...
    }
```

*Bind variable using @RequestParamAnnotation*

```
    @RequestMapping("/processFormVersionTwo")
    public String letsShoutDude(
        @RequestParam("studentName") String theName,
         Model model
     ) {
        // now we can use the variable: theName
        
    }
```

So behind the scenes, Spring will actually read that data from the request parameters,
and then it will take that data and bind it to this variable that I have here called theName.
All right, so Spring will read the request parameter, and bind it to variable theName, and at that point
we have the variable and we can use it in our application. But the key here is that Spring has that
special annotation here called @RequestParam for reading form data for you.

### Add Controller @RequestMapping

* Serves as parent mapping for controller
* All request mappings on methods in the controller are relative
* Similar to folder directory structures

So for show form, we'd have /funny/showForm, so all of the method mappings are relative to
the actual controller mapping. And this is just a very nice way
for you to group your request mappings together, and also a great technique
for resolving any problems or conflicts that you may have with other request mappings.

```
    @RequestMapping("/funny")
     public class FunnyController {
    
        @RequestMapping("/showForm")
        public String showForm() {
            ...
        }
        
        @RequestMapping("/processForm")
        public String process(HttpServletRequest request, Model model) {
            ...
        }
        
    }
```

#### FAQ: How does "processForm" work for "/hello"?

*FAQ:*

*Question: Can you please clarify how /hello is getting appended to the jsp file action for "processForm"?*

*Answer*

You can use "processForm" because it is a relative path to the controller "/hello" request mapping. Here is how it works.

1. When you wish to view the form, the HTML link points to "hello/showForm". This calls the controller and it displays the form.

2. At this point the browser URL/path is: http://localhost:8080/spring-mvc-demo/hello

3. The HTML form uses "processForm" for the form action. Notice that it does not have a forward slash, as a result, this will be relative to the current browser URL. Since the current browser URL is http://localhost:8080/spring-mvc-demo/hello

Then the actual form URL submission will send it to http://localhost:8080/spring-mvc-demo/hello/processForm

The part in bold with map to the controller with top-level request mapping "/hello" and then map to request mapping in that class "/processForm"

The key here is relative path of showing the form and then submitting to relative path.

--- 

### Spring MVC Form Tag - Text Field

*In your Spring Controller*

* Before you show the form, you must add a model attribute
* This is a bean that will hold form data for the data binding

##### Show form - Add Model Attribute

So when we show the form, we need to add a model attribute. So here I have showForm.
This method takes a new parameter here called Model, theModel.
And remember, theModel is an object that we can use to kind of pass data around between controllers and views.
So here what I'll do is I'll say, theModel.addAttribute. I give the model name of student
and then the actual value of new Student. So, think with me, I'm creating a new Student object,
I'm creating an empty Student object that I'll pass to the form for the form to make use of with data binding.
Now, the important thing here is that, when I give the addAttribute, the attribute name of student,
that's the same name that our form will use to reference this model attribute.

```
@RequestMapping("/showForm")
public String showForm(Model theModel) {
    theModel.addAttribute("student", new Student());
    
    return "student-form";
}
```

##### Setting up HTML Form - Data Binding

When they have path= firstName, Spring MVC will call student.getFirstName,
and they use that model attribute from up top to retrieve that data.
If it's null, then that form field will simply be empty. So in our case of creating a new student from scratch,
then it'll be empty, but you can easily prepopulate that accordingly if you'd like.

```
<form:form action="processForm" modelAttribute="student">
    First name: <form:input path="firstName" />
    
    <br><br>
    Last name: <form:input path="lastName" />
    
    <br><br>
    
    <input type="submit" value="Submit"
</form:form>
```

##### Handling Form Submission in the Controller

```
    @RequestMapping("/processForm")
    public String processForm(@ModelAttribute("student") Student theStudent) {
    
        // log the input data
        System.out.println("theStudent: " + theStudent.getLastName());
        
        return "student-confirmation";
    }
        
```

---

#### Form Validation

So, what's the need for validation?

Well, you may have a form and you may want to validate the fields to make sure you have required fields.
You also many want to validate numbers in a given range. You may want to validate the format, for example, for a postal
code or you may want to add your own custom business rule for validation.

##### Java's standard Bean validation API

* Java's Standard Bean Validation API
* Defines a metadata model and API for entity validation
* Not tied to eitherr the web tier or the persistence tier
* Available for server-sed apps and also client-side JavaFX/Swing apps

##### Spring and Validation

* Spring version 4 and higher supports Bean Validation API
* Preferred method for validation when building Spring apps
* Simply and Validation JARs to our project

| Annotation    | Description                                 |
| : ------------| :------------------------------------------ |
| @Not Null     | Checks that the annotated value is not null |
| @Min          | Must be a number >= value                   |
| @Max          | Must be a number <= value                   |
| @Size         | Size must match the given size              |
| @Pattern      | Must match a regular expression  pattern    |
| @Future/@Post | Date must be in future or past of given     |
| others...     | ...                                         |