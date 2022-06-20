package uz.jl.domains;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import uz.jl.config.HibernateUtils;

import javax.management.MBeanAttributeInfo;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
public class Book {


    @Id
    @SequenceGenerator(name = "book_id_seq", sequenceName = "book_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_seq")
    private Long id;

    private String name;

    private String author;

}

class GenericDAO<T> {
    private final Class<T> persistentClass;

    public GenericDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }


    public T save(T book) {
        try (SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.persist(book);
            session.getTransaction().commit();
            return book;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<T> findAll() {
        try (SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            return session.createQuery("from  " + persistentClass.getSimpleName(), persistentClass)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

class BookDAO extends GenericDAO<Book> {

}

class BookTest {
    public static void main(String[] args) {
        BookDAO bookDAO = new BookDAO();
//        Book book = Book.builder()
//                .name("Daxshat")
//                .author("Primqul Qodirov")
//                .build();
//
//        System.out.println("bookDAO.save(book) = " + bookDAO.save(book));
        System.out.println("bookDAO.findAll() = " + bookDAO.findAll());

    }
}
