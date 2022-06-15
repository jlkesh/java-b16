package uz.jl.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Objects;

public class HibernateUtils {
    private static final SessionFactory sessionFactory = setUp();

    private static SessionFactory setUp() {
         if (Objects.isNull(sessionFactory)) {
            try {

            StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml").build();
            Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
            return meta.getSessionFactoryBuilder().build();
            }catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException("Could not create SessionFactory");
            }
        }
        throw new RuntimeException("Could not create SessionFactory");
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


}
