package courses_management;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private final ArrayList<Student> studentList;
    private String code;
    private String name;


    public Course(String code, String name) {
        this.studentList = new ArrayList<>();
        this.code = code;
        this.name = name;
    }

    // Getters and Setters

    // Methods
    public void enrollStudent(Student student) {
        this.studentList.add(student);
    }

    public List<Student> getStudentList() {
        return studentList.stream().toList();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
