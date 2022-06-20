package uz.jl.domains;

import jakarta.persistence.*;

@Entity
@Table(name = "student_java_config2", schema = "hr")
public class Student {

    @Id
    @SequenceGenerator(schema = "hr", name = "id", allocationSize = 1)
    @GeneratedValue(generator = "id", strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false, insertable = true, updatable = false)
    private String studentID;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false, name = "student_email")
    private String email;

    public Student() {
    }

    public Student(long id, String studentID, String firstName, String email) {
        this.id = id;
        this.studentID = studentID;
        this.firstName = firstName;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", studentID='" + studentID + '\'' + ", firstName='" + firstName + '\'' + ", email='" + email + '\'' + '}';
    }
}
