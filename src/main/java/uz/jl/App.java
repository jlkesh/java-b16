package uz.jl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import uz.jl.config.HibernateUtils;
import uz.jl.domains.Student;
import uz.jl.domains.University;

import java.util.List;
import java.util.UUID;

public class App {
    static StudentService studentService = ApplicationContextHolder.getBean(StudentService.class);

    public static void main(String[] args) {
        Student student = new Student();
        student.setStudentID(UUID.randomUUID().toString());
        student.setFirstName("Iron man");
        student.setEmail("Robert Dovniy");
        studentService.create(student);
        System.out.println("student = " + student);
        List<Student> students = studentService.getAll();
        System.out.println("students = " + students);
//        University university = new University();


    }
}


class StudentService {
    private static StudentService instance;
    private final static StudentRepository studentRepository = ApplicationContextHolder.getBean(StudentRepository.class);

    private StudentService() {
    }

    public static StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAll() {
        return studentRepository.getAll();
    }
}

class StudentRepository {
    static SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    private static StudentRepository instance;

    private StudentRepository() {
    }

    public static StudentRepository getInstance() {
        if (instance == null)
            instance = new StudentRepository();
        return instance;
    }

    public Student save(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(student);
        session.getTransaction().commit();
        return student;
    }

    public List<Student> getAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Student> students = session.createQuery("from Student t", Student.class).getResultList();
        session.getTransaction().commit();
        return students;
    }
}

class ApplicationContextHolder {
    public static <T> T getBean(Class<T> clazz) {
        return switch (clazz.getSimpleName()) {
            case "StudentRepository" -> (T) StudentRepository.getInstance();
            case "StudentService" -> (T) StudentService.getInstance();
            default -> throw new RuntimeException("Bean not found");
        };
    }

    public static Object getBean(String beanName) {
        return switch (beanName) {
            case "StudentRepository" -> StudentRepository.getInstance();
            case "StudentService" -> StudentService.getInstance();
            default -> throw new RuntimeException("Bean not found");
        };
    }
}