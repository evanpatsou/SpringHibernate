#### What is Hibernate?

Hibernate is a framework for persisting or saving java objects into a database.
It's a very popular framework used by a lot of enterprise Java projects.
You can download it for free from hibernate.org.

But basically at very high level, you'll have your Java application, it'll make use of this Hibernate
framework and you can use it for saving and retrieving data from the database.

_So, what are the benefits of Hibernate?_

Hibernate handles all of the low level SQL code. So, it actually minimizes the amount of JDBC code you
have to develop. So, Hibernate actually provides the Object-to-Relational Mapping.
And it makes it really easy to create apps that store and retrieve objects from the database.

_So, how does it work?_

Again Hibernate provides something called Object-To-Relational Mapping or you'll hear the buzzword
or keywork called ORM. So, as a developer you need to tell how Hibernate
how your Java class or object how it maps to data in the database.
In fact, you're gonna map your Java class to a given database table.
So, for example, we have our Java class that's a Student.
It has four fields id, firstName, lastName and email. And note how firstName and lastName is spelled as for
the camel case. We have the Hibernate framework in the middle and on the far right we have the actual database table.

So, in this example we have table called student. It has and id, which is the primary key.
There's a first_name and last_name and note it's first underscore name, last underscore name and
then a field for an email address. And what we'll do is hey, this Java class Student maps
to this given table. And you set up the one-to-one mapping between the fields and actual columns in the database.
Now you can set up this mapping via a configuration file. Using XML or you can set it up using Java annotations.

##### Saving a Java  Object with Hibernate

So the first thing we do is we create the Java object.  We just use the new keyword say
new Student("John", "Doe", "john@luv2code.com");. That's the first name, last name, email address.
Then we'll save this Java object to a database. So here we make use of session, which is a special
Hibernate object. We say session.save and then we pass in our object. What happens in the background is that Hibernate will
take the Java Object based on those mappings that have been defined earlier. Hibernate will take that information and store it
in the appropriate table, in the appropriate columns. Hibernate will do all of that work for you.

```
// Create Java object
Student theStudent = new Student("John", "Doe", "john@luvcode.com");

// save it to database
int theId = ( Integer ) session.save(theStudent);
```

##### Retrieving a Java Object with Hibernate

So that's basically how you retrieve a Java object from the database.
And again Hibernate will do all the low level work of doing the query, getting the actual data, constructing
the object and returning it back to your program. So you can see how it is really easy here to
make use of Hibernate. It minimizes a lot of the low-level JDBC code you would
have to write in the past. 

```
// Create Java object
Student theStudent = new Student("John", "Doe", "john@luvcode.com");

// save it to database
int theId = ( Integer ) session.save(theStudent);

// now retreive from database using the primary key
Student myStudent = session.get(Student.class, theId);
```

##### Querying for Java Objects


Alright, now what about the scenario where you wanted
to say hey I want all the Student objects not just one. I want query and get all of them.
I'd like to get a list of those Student objects. Hibernate has a port for queries so here I'll say
session.createQuery("from Student"). So it'll basically give you a list of all of those
Student objects. So here I'll say query.list(). So that'll actually query the database.
Get a list of all objects from the Student table. And then return it to you as a list of Student objects.

```
Query query = session.createQuery("from Student");
List<Student> students = query.list();
```

##### Hibernate and JDBC

Well, Hibernate actually uses JDBC for all database communications, so really,
Hibernate is just another layer of extraction on top of JDBC. So when your application uses the Hibernate framework,
your app will store and retrieve objects using the Hibernate API. In the background,
Hibernate does all of the low level JDBC work, and submitting the SQL queries and so on.
So Hibernate does a lot of the low level work for you, but in the background,
it all goes through the standard JDBC API. So when we actually configure Hibernate to talk to your database, we'll actually configure Hibernate
to make use of a JDBC driver.

##### Hibernate Configuration

**Configuration file**

Alright, now, the configuration file basically tells Hibernate how to connect
to the database. And again, remember that Hibernate uses JDBC in the background for communicating with the database.
So the bulk of the information that we'll have in a config file is that we'll actually have
the JDBC configuration.

_**FAQ: Why we are using JPA Annotation instead of Hibernate?**_

**QUESTION:**

Why we are using JPA Annotation instead of Hibernate ?
For example, why we are not using this org.hibernate.annotations.Entity?

**ANSWER:**

JPA is a standard specification. Hibernate is an implementation of the JPA specification.
Hibernate implements all of the JPA annotations.
The Hibernate team recommends the use of JPA annotations as a best practice.

**Two key players**

| Class           | Description |
|:---------------:|:------------|
| SessionFactory  | Reads the hibernate config file <br> Creates Session objects <br> Heavy-weight object <br> Only create once in your app |
|  Session        | Wraps a JDBC connection <br> Main object used to save/retrieve objects <br> Short-lived object <br> retrieved from SessionFactory  |

```
    public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {	
		    // now use the session object to save/retrieve Java objects
        } finally {
            factory.close();
        }
    }
```

##### Save a Java object

The first thing I do is I create the student, so saying new Student, give first name,
last name, email address, again, this is plain old Java here, nothing hibernates specifically,
I simply create an object. Then I actually start a transaction. So here I say session.beginTransaction.
Then I save the student, so here I'll say session.save and I give tempStudent, 
so here I'm gonna save a given object to the database. Now behind the scenes, right, Hibernate knows how to connect to our database
based on our configuration file. Hibernate also knows how to map this Student class or object to the actual database
based on those annotations we took care of in the previous video.
So Hibernate knows kind of where things should fall. So here I say session.save,
puts it in the database, and then I also do a commit on this transaction.
So here I say session.getTransaction().commit() and that actually stores it in the database
once I do a commit. There's also a rollback method if you like to do that
if your not happy with this transaction. So that's the basic game plan, that's the basic happy path
of how you actually save a Java object in the database.

```
    try {			
        // create a student object
        System.out.println("Creating new student object...");
        Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");
        
        // start a transaction
        session.beginTransaction();
        
        // save the student object
        System.out.println("Saving the student...");
        session.save(tempStudent);
        
        // commit transaction
        session.getTransaction().commit();
        
        System.out.println("Done!");
    }
    finally {
        factory.close();
    }
``` 

---

**_Terminology_**

> _Primary key_
>
> Uniquely identifies each row in a table must be a unique value, cannot contain NULL values.

_Hibernate Identity - Primary key_

```
@Entity
@Table(name="student")
public class Student {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    ...
    
}
```

**ID Generation Strategies**

|           Name          |                 Description                                                |
|:-----------------------:|:---------------------------------------------------------------------------|
| GenerationType.AUTO     | Pick an appropriate strategy for the particular database                   |
| GenerationType.IDENTITY | Assign primary keys using database identity column                         |
| GenerationType.SEQUENCE | Assign primary keys using a database sequence                              |
| GenerationType.TABLE    | Assign primary keys using n underlying database table to ensure uniqueness |

**Custom Strategy**

* Create implementation of org.hibernate.id.IdentifierGenerator
* Override the method: public Serializable generate(...)

For my company we have a custom implementation or a custom requirement, where we need to generate our own ID
using our own custom business logic. So this is where I say, and wait, there's more!
So, you can actually create your own custom generator, all right? So, you can write your own custom code
for generating that ID.

So, the basic development process is to create an implementation of the identifier generator interface,
and then in your subclass you would override the method generate. So, inside of this generate method,
you would do your own custom business logic for determining what the next ID is
and you return that as a value. Then, you simply plug that into Hibernate,
and then Hibernate will use your custom generator. Now, a word of warning here is that you have to make sure
that your generator will always generate a unique value.

**_Warning_**

1. Always generate unique value
2. Work in high-volume, multi-threaded environment
3. If using server clusters, always generate unique value

##### Hibernate Query Language (HQL)

* Query language for retrieving objects
* Similar in nature to SQL
    * where, like, order by, join, in, etc...
    
Retrieving Students: lastName = 'Doe'
```
List<Student> theStudents = 
    session
    .createQuery("from Student s where s.lastName='Doe'")
    .getResultList();
```

---

Practice Activity #8 - Hibernate Development

 

Overview

Create an app using Hibernate to read/write data to database table. Here is the database table design:

Table: Employee

Columns
- first_name : varchar
- last_name: varchar
- company : varchar


Steps You Must Complete 

Create the database table.
Set up the Hibernate configuration file.
Create a Java class (entity) with Hibernate annotations.
Develop a main application.
Develop code to save objects.
Develop code to retrieve an object by primary key.
Develop code to query objects to find employees for a given company.
Develop code to delete an object by primary key.
  

  The application should use the same style and approach as shown in the videos. 



Compare your code to the solution. The solution is available here:
- http://www.luv2code.com/downloads/udemy-spring-hibernate/solution-practice-activities.zip

---

**FAQ: Handling Dates with Hibernate**

How can I read date strings from the command-line and store them as dates in the database?

**Answer:**

You can make use of a combination of Java's date formatting class and Hibernate annotations.

Sample output:

Student [id=50, firstName=Paul, lastName=Doe, email=paul@luv2code.com, dateOfBirth=null]
Student [id=51, firstName=Daffy, lastName=Duck, email=daffy@luv2code.com, dateOfBirth=null]
Student [id=52, firstName=Paul, lastName=Doe, email=paul@luv.com, dateOfBirth=31/12/1998]

**Development Process Overview**

1. Alter database table for student
2. Add a date utils class for parsing and formatting dates
3. Add date field to Student class
4. Add toString method to Student class
5. Update CreateStudentDemo

**Detailed steps**

1.) Alter database table for student

We need to alter the database table to add a new column for "date_of_birth".

Run the following SQL in your MySQL Workbench tool.

```
ALTER TABLE `hb_student_tracker`.`student` 
ADD COLUMN `date_of_birth` DATETIME NULL AFTER `last_name`;
```

2.) Add a date utils class for parsing and formatting dates

We need to add a DateUtils class to handle parsing and formatting dates. The source code is here. The class should be placed in the package: com.luv2code.hibernate.demo.

The date formatter uses special symbols for formatting/parsing.

-  dd:  day in month (number)
-  MM:  month in year (number)
- yyyy: year

See this link for details: https://docs.oracle.com/javase/tutorial/i18n/format/simpleDateFormat.html

```
package com.luv2code.hibernate.demo;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class DateUtils {
    
    // The date formatter
    // - dd:   day in month (number)
    // - MM:   month in year (number)
    // - yyyy: year
    //
    // See this link for details: https://docs.oracle.com/javase/tutorial/i18n/format/simpleDateFormat.html
    //
    //
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    
    // read a date string and parse/convert to a date
    public static Date parseDate(String dateStr) throws ParseException {
        Date theDate = formatter.parse(dateStr);
        
        return theDate;        
    }
    
    // read a date and format/convert to a string
    public static String formatDate(Date theDate) {
        
        String result = null;
        
        if (theDate != null) {
            result = formatter.format(theDate);
        }
        
        return result;
    }
}
```

3. Add date field to Student class

We need to add a date field to the Student class. We map this field to the database column,
"date_of_birth". Also, we make use of the @Temporal annotation. This is a Java annotation for 
storing dates.

```
 @Column(name="date_of_birth")
      @Temporal(TemporalType.DATE)    
      private Date dateOfBirth;
```
```
package com.luv2code.hibernate.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.luv2code.hibernate.demo.DateUtils;

@Entity
@Table(name="student")
public class Student {
    
    @Id
    @Column(name="id")
    private int id;
    
    @Column(name="first_name")
    private String firstName;
    
    @Column(name="last_name")
    private String lastName;
    
    @Column(name="email")
    private String email;
    
    @Column(name="date_of_birth")
    @Temporal(TemporalType.DATE)    
    private Date dateOfBirth;
    
    public Student() {
        
    }

    public Student( String firstName, String lastName, String email, Date theDateOfBirth) {
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = theDateOfBirth;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", dateOfBirth=" + DateUtils.formatDate(dateOfBirth) + "]";
    }
        
}
```

4.) Add toString method to Student class

We will make an update to the toString method in our Student class.  It will use the formatter from 
our DateUtils.class. This code is already included in Student.java from the previous step. I'm just 
highlighting it here for clarity.

```
return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
            + ", dateOfBirth=" + DateUtils.formatDate(dateOfBirth) + "]";
```

5. Update CreateStudentDemo

Now for the grand finale. In the main program, read the date as a String and parse/convert it to a 
date. Here's the snippet of code.

```
package com.luv2code.hibernate.demo;
 
import java.text.ParseException;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.luv2code.hibernate.demo.entity.Student;
 
public class CreateStudentDemo {
 
    public static void main(String[] args) {
        
        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
                .buildSessionFactory();
 
        // create a session
        Session session = factory.getCurrentSession();
 
        try {
            // create a student object
            System.out.println("creating a new student object ...");
 
            String theDateOfBirthStr = "31/12/1998";
 
            Date theDateOfBirth = DateUtils.parseDate(theDateOfBirthStr);
 
            Student tempStudent = new Student("Pauly", "Doe", "paul@luv.com", theDateOfBirth);
 
            // start transaction
            session.beginTransaction();
 
            // save the student object
            System.out.println("Saving the student ...");
            session.save(tempStudent);
 
            // commit transaction
            session.getTransaction().commit();
 
            System.out.println("Success!");
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            factory.close();
        }
    }
    
}
```

#### Basic Mapping

So far in the course we've covered a very basic mapping, we had a Student class and then we mapped it to a student table.
And it worked fine just to kinda get us started with Hibernate, but now let's go ahead and move forward into some more advanced features.
So with advanced mappings, in your database you most likely will have multiple tables and you'll also have relationships between tables
and we need to somehow model this with Hibernate. We'll look at three different types of advanced mappings,
we'll look at one-to-one, we'll also cover one-to-many, and many-to-one, we'll take a look at many-to-many mappings.

* In the database, you most likely will have
    * Multiple Tables
    * Relationships between Tables
    
**Advanced Mappings**

* One-to-one
* One-to-Many, Many-to-one
* Many-to-Many

**One-to-One Mapping**

* An instructor can have an "instructor detail" entity
    * Similar to an "instructor profile"
    
**One-to-Many Mapping**

* An instructor can have many courses

```
Instructor -> Course 1
           -> Course 2
           -> ...
```

**Many-to-Many Mapping**

* A course can have many students
* A student can have many courses

```
Course 1 -> Student 1
Course 1 -> Student 2
Course 2 -> Student 2
Course 2 -> Student 1
```

**Important Database Concepts**

* Primary key and foreign key: identify a unique row in a table
* Cascade: Apply the same operation to related entities

* Foreign key:
    * Link tables together
    * a field in one table that refers to primary key in another table
    * Main purpose is to preserve relationship between tables
        * Referential Integrity: this helps prevent operations that would destroy  the relationship between those tables
        * Ensures that only valid data is inserted into the foreign key column.
            * Can only contain valid reference to a primary key in another table
    
**!!! Cascade Delete**

_!!!Developer can configure cascading!!!_

**Fetch Types: Eager vs Lazy Loading**

* When we fetch / retrieve data, should we retrieve EVERYTHING?
    * **Eager** will retrieve everything
    * **Lazy** will retrieve on request
    
So here I have an instructor and an instructor also has courses. So when I retrieve the instructor object,
should I get all of the courses for that instructor immediately? Or should I grab the courses only on request?
So Eager will retrieve everything in one shot. Lazy will retrieve the data on request,
and I'll show you how to configure and set that up when we make use of these advanced mappings.

#### Uni-Directional

So with uni-directional, you have an instructor and then you actually have the instructor detail.
So you start with the instructor object, you load the instructor, and then from there, you can access the instructor detail.
So it's really a one way relationship.

#### Bi-Directional

So here we have the instructor, and then they have the relationship with the instructor detail,
but then we can also go the other way, so we can load the instructor detail and have a reference to 
the given instructor.


#### Entity LifeCycle

The Entity Lifecycle is basically a set of states that a Hibernate entity can go through
when you're using it in your application.

| Operations  |                                 Description                                            |
|:------------|:---------------------------------------------------------------------------------------|
| Detach      | If entity is detached, it os not associated with a Hibernate session                   |
| Merge       | If instance is detached from session, then merge will reattach to session              |
| Persist     | Transitions new instances to managed state. Next flush/commit will save in db          |
| Remove      | Transitions managed entity to be removed. Next flush/commit will delete from database  |
| Refresh     | Reload /synch object with data from db. Prevents stale data                            |

#### @OneToOne - Cascade Types LifeCycle

The Entity Lifecycle is basically a set of states that a Hibernate entity can go through
when you're using it in your application.

| Operations  |                                 Description                                                  |
|:------------|:---------------------------------------------------------------------------------------------|
| Persist     | If entity is persisted / saved, related entity will also be persisted                        |
| Remove      | If entity is removed / deleted, related entity will also be deleted                          |
| Refresh     | If entity is refreshed, related entity will also be refreshed                                |
| Detach      | If entity is detached (not associated w. session), then related entity will also be detached |
| Merge       | If entity is merged, then related entity will also be merged                                 |
| ALL         | All of above                                                                                 |

#### More mappedBy

* mappedBy tells Hibernate
    * Look at the instructor property in the Course class
    * Use information from the *Course* class @JoinColumn
    * To help find associated courses for instructor

#### Add support for Cascading

```
@Entity
@Table(name="instructor")
public class Instructor {
    
    @OneToMany(mappedBy="instructor",
               cascade={CascadeType.PERSIST, CascadeType.MERGE,
                        CascadeType.DETACH, CascadeType.REFRESH})
                        
    private List<Course> courses;
    
    ...
    
    // constuctors, getters / setters
    
}
```

#### Add convenience methods for bi-directional

```
@Entity
@Table(name="instructor")
public class Instructor {
    ...
    // add convenience methods for bi-directional relationship
    
    public void add(Course tempCourse) {
        if ( courses == null ) {
            courses = new ArrayList<>();
            
            courses.add(tempCourse);
            tempCourse.setInstructor(this);
        }
        
        ...
    }
}
```

---

#### Fetch Types: Eager vs Lazy Loading

* When we fetch / retrieve data, should everything?
    * Eager will retrieve everything
    * Lazy will retrieve on request
    
So, imagine we have an instructor. An instructor has a list of courses. Depending on the loading
type, that will determine when and how the data is loaded from the data base by Hibernate.
And, it'll also has an implication on your actual performance of your given app.

#### Eager Loading

* Eager loading will load all dependent entities
    * Load instructor and all of their courses at once
    
So, this could easily turn into a performance nightmare. So, if we were to load a course and then actually load
all of the students for the course, then this could possibly slow down our application or not even possibly
most likely, it will slow down our application. Because, just like the course you are attending now,
a given course could have 10,000, 20,000, maybe 50,000 students and we really don't need all that data
at this point. So, in our app, if we're simply searching for a course by keyword, like just doing your normal
search there, we only want a list of matching courses. But eager loading, it will still load all the students
for each course and that's not good.

The best practice in the industry is to only load data when absolutely needed.
So, you should prefer lazy loading instead of eager loading. Now, there's always exceptions to
the rule. The best practice in the industry is to only load data when absolutely needed.
So, you should prefer lazy loading instead of eager loading. Now, there's always exceptions to
the rule And there's also special use cases, but in general, the recommendation is to prefer lazy loading over
eager loading.

#### Lazy Loading

So, lazy loading will load the main entity first, and then it will actually load the dependent
entities on demand, at a later time. So, here we have a course, so it will actually
load the course first and then, when you need a list of students, then that's when it will
actually go to the database and load those students on demand. So, they will be loaded at a later time.
And, again, remember, the preference here is on making use of lazy loading to make sure
our application performs in a fast manner.

#### Fetch Type

So when you define the mapping relationship between two entities or classes,
you can specify the fetch type of being eager or lazy.

```
@Entity
@Table(name="instructor")
public class Instructor {
    ...
    
    @OneToMany(fetch=FetchType.Lazy, mappedBy="instructor")
    private List<Course> courses;
    
    ...
} 
```

**Default Fetch Types**

|  Mapping    | Default Fetch Type |
|:---------   | :----------------- |
| @OneToOne   | FetchType.Eager    |
| @OneToMany  | FetchType.LAZY     |
| @ManyToOne  | FetchType.Eager    |
| @ManyToMany | FetchType.LAZY     |

#### More about Lazy Loading

* When you lazy load, the data is only retrieved on demand
* However, this requires an open Hibernate session

* If the Hibernate session is closes
    * And you attempt to retrieve lazy data
    * Hibernate will throw an exception
    
-> Options retrieve lay data: (most common)
1. session.get and call appropriate getter methods(s)
2. Hibernate query with HQL

#### FAQ: How load the courses at a later time in the application?

**Question**

I've watched your 2 solutions for loading related data after session closing. Both, either getting related 
courses before closing session and using JOIN FETCH seem to be negating of lazy loading (using those 
solutions we completely resign of lazy loading. Is there any good solution to load these data 
somewhere else in the app? Should I open new session?

**Answer**

Yes, you can load it later with using a new session, just make use of HQL
Here's the code snippet. Make note of HQL in bold:

```
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class GetCoursesLater {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Instructor.class)
                                .addAnnotatedClass(InstructorDetail.class)
                                .addAnnotatedClass(Course.class)
                                .buildSessionFactory();
        
        // create session
        Session session = factory.getCurrentSession();
        
        try {            
            
            // start a transaction
            session.beginTransaction();
                        
            // get the instructor from db
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);                    
            
            System.out.println("luv2code: Instructor: " + tempInstructor);    
            
            // commit transaction
            session.getTransaction().commit();
            
            // close the session
            session.close();

            System.out.println("\nluv2code: The session is now closed!\n");

            //
            // THIS HAPPENS SOMEWHERE ELSE / LATER IN THE PROGRAM

            // YOU NEED TO GET A NEW SESSION
            //
            
            System.out.println("\n\nluv2code: Opening a NEW session \n");

            session = factory.getCurrentSession();
            
            session.beginTransaction();
            
            // get courses for a given instructor
            Query<Course> query = session.createQuery("select c from Course c "
                                                    + "where c.instructor.id=:theInstructorId",    
                                                    Course.class);
            
            query.setParameter("theInstructorId", theId);
            
            List<Course> tempCourses = query.getResultList();
            
            System.out.println("tempCourses: " + tempCourses);
            
            // now assign to instructor object in memory
            tempInstructor.setCourses(tempCourses);
            
            System.out.println("luv2code: Courses: " + tempInstructor.getCourses());
            
            session.getTransaction().commit();
            
            System.out.println("luv2code: Done!");
        }
        finally {
            
            // add clean up code
            session.close();
            
            factory.close();
        }
    }

}
```

--- 

#### @JoinColum ... where does it find the column?

**Question**

In the Course class,we have OneToMany relation with reviews with join column course_id.
But in course table we do not have column course_id. Ideally when we say @JoinColumn a new column
should be created in course table ... isn't it?

How does @JoinColum know where to find the join column?

**Answer**

The JoinColumn is actually fairly complex and it goes through a number of advanced steps to find the
desired column. 
This info below is from the documentation

Source: http://docs.oracle.com/javaee/7/api/javax/persistence/JoinColumn.html#name--

The table in which it is found depends upon the context.

- If the join is for a OneToOne or ManyToOne mapping using a foreign key mapping strategy, the foreign key column is in the table of the source entity or embeddable.
- If the join is for a unidirectional OneToMany mapping using a foreign key mapping strategy, the foreign key is in the table of the target entity.
- If the join is for a ManyToMany mapping or for a OneToOne or bidirectional ManyToOne/OneToMany mapping using a join table, the foreign key is in a join table.
- If the join is for an element collection, the foreign key is in a collection table.


So as you can see, it depends on the context. In our training video, we are using @OneToMany uni-directional (course has one-to-many reviews).
As a result, the join column / foreign key column is in the target entity. In this case, the target entity is the Review class. So, you will find the join column "course_id" in the "review" table.

----
#### ManyToMany

##### Join Table

> A table that provides a mapping between two tables.
>
> It has foreign keys for each table to define the mapping relationship.

**Many-to-Many Mapping**

Scenario: 

* A course can have many students
* A student can have many courses

We somehow need to keep track of relationships. We need to keep track of which student is in which course
and vice-versa. And what we can do is actually make use of a join table. So this join table's a special table
that'll maintain the relationships between a course and students. So you can use this join table to find out
who signed up for which course.

**Join Table**

> A table that provides a mapping between two tables.
>
> It has foreign keys for each table to define the mapping relationship.

**Development Process: Many-to-Many**

1.  Prep Work - Define database tables
2.  Update **Course** class
3.  Update **Student** class
4.  Create **Main** app

 