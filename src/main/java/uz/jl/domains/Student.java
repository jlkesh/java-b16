package uz.jl.domains;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "student_table_3")
public class Student {

//    @Id
//    @SequenceGenerator(
//            name = "student_table_id_sequence",
//            sequenceName = "student_table_id_seq",
//            initialValue = 1,
//            allocationSize = 1
//    )
//    @GeneratedValue(generator = "student_table_id_sequence")


//    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(
//            name = "UUID",
//            strategy = "org.hibernate.id.UUIDGenerator"
//    )

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    private String id;

    @Column(unique = true, nullable = false, insertable = true, updatable = false)
    private String studentID;

    @Column(nullable = false)
    private String firstName;
    @Column(unique = true, nullable = false, name = "student_email")
    private String email;

    public Student() {
    }

    public Student(String id, String studentID, String firstName, String email) {
        this.id = id;
        this.studentID = studentID;
        this.firstName = firstName;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
