package com.learn.hibernate;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.learn.hibernate.entity.Address;
import com.learn.hibernate.entity.Customer;
import com.learn.hibernate.entity.Employee;

public class HibernateUtil {
   private static StandardServiceRegistry registry;
   private static SessionFactory sessionFactory;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory == null) {
         try {
            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

            Map<String, String> settings = new HashMap<>();
            settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            settings.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/hibernatedb");
            settings.put("hibernate.connection.username", "devuser");
            settings.put("hibernate.connection.password", "pass");
            settings.put("hibernate.show_sql", "true");
            settings.put("hibernate.hbm2ddl.auto", "create");
            settings.put("hibernate.current_session_context_class", "thread");
            registry = registryBuilder.applySettings(settings).build();

            MetadataSources sources = new MetadataSources(registry)
                //  .addAnnotatedClass(Employee.class)
                  //.addAnnotatedClass(Address.class)
                  .addAnnotatedClass(Customer.class);


            Metadata metadata = sources.getMetadataBuilder().build();

            sessionFactory = metadata.getSessionFactoryBuilder().build();
         } catch (Exception e) {
            System.out.println("SessionFactory creation failed");
            if (registry != null) {
               StandardServiceRegistryBuilder.destroy(registry);
            }
         }
      }
      return sessionFactory;
   }

   public static void shutdown() {
      if (registry != null) {
         StandardServiceRegistryBuilder.destroy(registry);
      }
   }
}
