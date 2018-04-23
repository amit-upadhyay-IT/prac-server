

import javax.persistence.Entity;

@Entity
public class Person {

 // Persistent Fields:
 private String firstName;
 private String lastName;
 private int age;

 // Constructors:
 public Person() {}
 public Person(String firstName, String lastName, int age) {
 this.firstName = firstName;
 this.lastName = lastName;
 this.age = age;
 }

 // String Representation:
 public String toString() {
 return firstName + " " + lastName + " (" + age + ")";
 }
 }