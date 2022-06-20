package uz.jl;

import jakarta.persistence.Entity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.reflections.Reflections;
import uz.jl.config.HibernateUtils;
import uz.jl.domains.Student;

import java.util.List;
import java.util.UUID;

public class App {
    public static void main(String[] args) {
        Student student = new Student();
        student.setStudentID(UUID.randomUUID().toString());
        student.setEmail(UUID.randomUUID()+"@mail.ru");
        student.setFirstName("Student");
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
//        currentSession.persist(student);
        List<Student> studentList = currentSession.createQuery("from Student ", Student.class).getResultList();
        System.out.println("studentList = " + studentList);
        currentSession.getTransaction().commit();
        sessionFactory.close();

    }
}
