package com.learn.hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.learn.hibernate.entity.Address;
import com.learn.hibernate.entity.Employee;

public class MainAppPersist {
   public static void main(String[] args) {
      Session session = null;
      Transaction transaction = null;
      try
      {
         session = HibernateUtil.getSessionFactory().openSession();
         transaction = session.getTransaction();
         transaction.begin();

         Address address1 = new Address("Bengaluru", "Karnataka", "India", "560016");
         Address address2 = new Address("Jaipur", "Rajasthan", "India", "302017");
         Address address3 = new Address("Jaipur1", "Rajasthan1", "India1", "3020171");

         // Employee1 have 2 addresses
         Employee employee1 = new Employee("Ravindra Singh", "Sales Manager", 450000);
         employee1.getAddresses().add(address1);
         employee1.getAddresses().add(address2);

         // Employee2 have 1 address
         Employee employee2 = new Employee("Mohit Sharma", "Software Engineer", 850000);
         employee2.getAddresses().add(address1);

         session.persist(employee1);
         session.persist(employee1);
         transaction.commit();
         System.out.println("User saved successfully");
         
         
         employee2.setName("Changes in Name");
         employee2.getAddresses().add(address3);
         session.persist(employee2);

         transaction.begin(); 
         Employee e = (Employee) session.get(Employee.class, 1L);
         System.out.println(e.getName()); 
         transaction.commit();
			 
      } catch (Exception e) {
         if (transaction != null) {
            System.out.println("Transaction is being rolled back.");
            transaction.rollback();
         }
         e.printStackTrace();
      } finally {
         if (session != null) {
            session.close();
         }
      }
      HibernateUtil.shutdown();
   }
}
