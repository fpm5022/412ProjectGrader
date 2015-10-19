package model;

/**
@author Feek <feek@psu.edu>
**/


public class Student {
    private String name;
    private String handle;
    
    public Student(String name, String handle) {
        this.name = name;
        this.handle = handle;
    }

    public String getInfo() {
        return this.name + " | " + this.handle;
    }
}
