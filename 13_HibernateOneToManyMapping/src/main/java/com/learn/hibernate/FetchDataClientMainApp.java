package com.learn.hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.learn.hibernate.entity.Address;
import com.learn.hibernate.entity.Employee;
//Change loading to lazy if want to avoid join query.
public class FetchDataClientMainApp {
   public static void main(String[] args) {
	   Employee employee = null;
      try(Session session = HibernateUtil.getSessionFactory().openSession()) {
         employee = session.load(Employee.class, 1);
         System.out.println(employee.toString());
         employee.getAddresses().stream().forEach(add -> System.out.println(add.toString()));
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      HibernateUtil.shutdown();
   }
}
