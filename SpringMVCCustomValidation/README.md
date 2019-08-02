#### Custom Validation

* Perform custom validation based on your business rules
    * Our example: Course Code must start with "LUV" string
    
* Spring MVC calls our custom validation
* Custom validation returns boolean value for pass/fail (true/false)

##### Step 1a: Create @CourseCode annotation

So the first thing to notice here's that we're creating this annotation.
Now, when you create an annotation, you make use of some special syntax.
So here we have public @interface CourseCode.

So it's a special type of interface. So use the @interface.
In effect, this is how you define a custom annotation for anything in Java.
But here we're gonna use it for our Spring MVC. So you use the @interface, so that's not a typo,
@interface, and then you give the name of your annotation, CourseCode.

So up top we have Constraint. It's gonna be validatedBy and you give the actual class
that has the business rules for validating this process. So CourseCodeConstraintValidator.class.
We'll cover that in Step 1b, that's coming up. Then we have @Target. So this basically says hey,
where can you apply this annotation or where can you list this annotation? So here we're gonna
say you can use this annotation on a method or on a field. So either way, a method or
a field. Then for @Retention, it says all right, this annotation that's here, how long should 
we retain it? So there's different types here. Here we're gonna say RetentionPolicy.RUNTIME 
meaning keep this annotation in the compiled Java bytecode so we can use it
, and introspect on it, and instrument on it during runtime.

```
@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {

	// define default course code
	public String value() default "LUV";
	
	// define default error message
	public String message() default "must start with LUV";
	
	...
}
```

##### Step 1b: Create CourseCodeConstraintValidator

Now we're moving ahead here and we look at the actual ConstraintValidator. So this is the guy who 
actually has the business rules that'll give you true, false if the actual value passes. All righty,
so CourseCodeConstraintValidator implements ConstraintValidator, has generics so they'll pass in the
actual CourseCode annotation and the actual type of data that we're validating against. Then 
I setup my field here, private String coursePrefix. And then I have public void initialize so when
they create this validator, they actually initialize it so I get a handle to the actual annotation
that was passed in and I say theCourseCode.value. So that would give me the actual prefix that 
we're gonna use to validate data against. So this basically just sets everything up. So our 
validator's up and running, it's ready. Now someone can actually use it. Someone can actually 
say hey, is the given value valid? That takes us to this next method here, isValid. So this is
what Spring MVC will call at runtime when they actually say hey, the user just submitted a form,
here's the data, is this data valid? And then we apply our business rules to it.

```
public class CourseCodeConstraintValidator 
	implements ConstraintValidator<CourseCode, String> {

	private String coursePrefix;
	
	@Override
	public void initialize(CourseCode theCourseCode) {
		coursePrefix = theCourseCode.value();
	}

	@Override
	public boolean isValid(String theCode, 
						ConstraintValidatorContext theConstraintValidatorContext) {

		boolean result;
		
		if (theCode != null) {
			result = theCode.startsWith(coursePrefix);
		}
		else {
			result = true;
		}
		
		return result;
	}
}
```

***FAQ: Spring MVC Custom Validation - Possible to validate with multiple strings?***

Spring MVC Custom Validation - FAQ: Is it possible to integrate multiple validation string in one 
annotation?

**Question:**

Is it possible to integrate multiple validation string in one annotation? For example, validate against both LUV and TOPS.


Yes, you can do this. In your validation, you will make use of an array of strings.

Here's an overview of the steps.

1. Update CourseCode.java to use an array of strings

Change the value entry to an array of Strings:
```
    // define default course code
    public String[] value() default {"LUV"};
```

Note the use of square brackets for the array of Strings. Also, the initialized value uses
curley-braces for array data.

2. Update CourseCodeConstraintValidator.java to validate against array of strings
``` Change the field for coursePrefixes to an array ```

Update the isValid(...) method to loop through the course prefixes. In the loop, check to see if the code matches any 
of the course prefixes.

```
    @Override
    public boolean isValid(String theCode, 
                        ConstraintValidatorContext theConstraintValidatorContext) {
        boolean result = false;
        
        if (theCode != null) {
            
            //
            // loop thru course prefixes
            //
            // check to see if code matches any of the course prefixes
            //
            for (String tempPrefix : coursePrefixes) {
                result = theCode.startsWith(tempPrefix);
                
                // if we found a match then break out of the loop
                if (result) {
                    break;
                }
            }
        }
        else {
            result = true;
        }
        
        return result;
  }
```

3. Update Customer.java to validate using array of strings

```
    @CourseCode(value={"TOPS", "LUV"}, message="must start with TOPS or LUV")
    private String courseCode;
```
Note the use of curley braces.