/**
@author Feek <feek@psu.edu>
**/


public class Student {
    private String name;
    private String handle;
    private boolean selected; // whether or not selected to be compiled / tested
    
    public Student(String name, String handle) {
        this.name = name;
        this.handle = handle;
    }
    
    public void setSelected(boolean b) {
        this.selected = b;
    }

    public String getInfo() {
        return this.name + " | " + this.handle;
    }
}
