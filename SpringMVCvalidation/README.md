### Spring MVC FROM Validation

##### Required fields

So we'll have our customer form, the user enters their data.
They hit submit, pass over a customer object to our customer controller. In the customer controller,
we'll actually perform the validation. If the validation is successful, we'll send them to the confirmation page.
If the validation fails, we'll actually send them back to the customer form for them to fix
up the form and resubmit again.

*Development Process*

1 Add validation rule to Customer class
```

public class Customer {
    @NotNull(message="is required")
    @Size(min=1, message="is required")
    private String lastName;
    
    // getter/setter methods
    
}

```

2 Display error messages on HTML form
```
<form:form action="processForm" modelAttribute="customer">
    First name: <form:input path="ﬁrstName" />
    <br><br>
    Last name (*): <form:input path="lastName" /> 
    <form:errors path="lastName" cssClass="error" /> 
    <br><br> 
    <input type="submit" value="Submit" />
 </form:form>     
```

3 Perform validation in the Controller class
```
@RequestMapping("/processForm")
public String processForm(          
    @Valid @ModelAttribute("customer") Customer theCustomer, 
    BindingResult theBindingResult
) {
    if (theBindingResult.hasErrors()) {
        return "customer-form"; 
    } else { 
        return "customer-conﬁrmation";
    } 
}
```

4 Update confirmation page
```
<html>
    <body>
        The customer is conﬁrmed: ${customer.ﬁrstName} ${customer.lastName}
     </body>
</html>
```

#### Special Note about BindingResult Parameter Order

When performing Spring MVC validation, the location of the BindingResult parameter is very important.
In the method signature, the BindingResult parameter must appear immediately after the model attribute.
 
If you place it in any other location, Spring MVC validation will not work as desired. In fact,
your validation rules will be ignored.

```
    @RequestMapping("/processForm")
    public String processForm(
            @Valid @ModelAttribute("customer") Customer theCustomer,
            BindingResult theBindingResult) {
        ...            
    }
```

*Here is the relevant section from the Spring Reference Manual*

_Defining @RequestMapping methods_

@RequestMapping handler methods have a flexible signature and can choose from a range of supported 
controller method arguments and return values.

The Errors or BindingResult parameters have to follow the model object that is being
bound immediately ...

Source: https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-methods

##### White Space

* Our previous example had a problem with white space
    * Last name field with all whitespace passed
    * Should have *failed*!
    
* We need to trim whitespace from input fields

*InitBinder* 

* @InitBinder annotation works as a pre-processor
* It will pre-process each web request to our controller
* Method annotated with @InitBinder is executed

* We will use this to trim Strings
    * Remove leading an trailing white space

* if String only has white spaces ... trim it to null
* Will resolve our validation problem


Once I have this object created then I'll use that dataBinder and I'll register this as a custom editor.
So what I'm doing here is I'm saying for every string class, apply the StringTrimmerEditor.
So in a nutshell what this does is it will pre-process every string as far as its form data.
It will remove the leading and trailing white space. And again if the string only had white space
it will trim it down to null.

```
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
```

### Validate a Number Range

* Add a new input field on our form for: *Free Passes*
* User can only enter a range: 0 to 10

*Recap Development process*

Step 1: Add validation rule to Customer class
```
public class Customer {
    
    @Min(value=0, message="must be greater than or equal to zero")
    @Max(value=10, message="must be less than or equal to 10")
    private int freePasses;
    
    // getter/setter methods
}
```

### Regular Expressions

* A sequence of characters that define a search pattern
    * This pattern is used to find or match strings
    
* Regular Expressions is like its own language (advanced topic)
    * I will assume you already know about regular expressions
    
* If not, then plenty of free tutorials available
    * https://docs.oracle.com/javase/tutorial/essential/regex/
    
*Validate a Postal Code*
* Add a new input ﬁeld on our form for: Postal Code
* User can only enter 5 chars / digits
* Apply Regular Expression 

*Development Process*

1. Add validation rule to Customer class 
2. Display error messages on HTML form 
3. Update conﬁrmation page

Step 1: Add validation rule to Customer class

```
import javax.validation.constraints.Pattern;

public class Customer {
    @Pattern(regexp="^[a-zA-Z0-9]{5}", message="only 5 chars/digits") 
    private String postalCode;
     
     // getter/setter methods
}
```
 
##### Handle String Input for Integer Fields

Development Process

We have a development process that we can use to resolve this issue. And here it is, step by step.
So, what we'll do is we'll first create a custom error message, so we won't see that big, long, ugly error message.
We'll simply give them a simple message of, "Hey, invalid number." And then in step two, we'll actually load that custom resource into the Spring configuration
file, and then that information will be displayed and used on our screen during spring validation.
So in effect, we're simply going to add our own custom error message for that scenario.

1. Create custom error message
    * src/SpringMVCvalidation.resources/messages.properties
    
2. Load custom messages resource in Spring config file
    * ../WEB-INF/srping-mvc-demo-servlet.xml



