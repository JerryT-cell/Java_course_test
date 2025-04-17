package org.example.project2;

public abstract class Person {
    // Private fields for encapsulation
    private final String id;                  // Unique identifier for the person, to test !
    private String name;
    private int age;

    // Constructor: intended to be called via super() in subclasses
    public Person(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Abstract method that every subclass must implement to indicate the role
    public abstract String getRole();

    // Concrete method returning a summary of the person's details
    public String getDetails() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Role: " + getRole();
    }
}

