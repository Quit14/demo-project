package org.demoproject;

import org.demoproject.dao.CustomerDao;
import org.demoproject.domain.Customer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class App {

    @SuppressWarnings("unused")
    private static void prepareTable() throws Exception {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost:5432/postgres");
        ds.setUsername("postgres");
        ds.setPassword("000000");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate( "CREATE TABLE customer ( firstName varchar(50), lastName varchar(50), test JSONB )" );
        stmt.close();
        conn.close();
    }

    public static void main(String[] args) throws Exception {


        AbstractApplicationContext context = new AnnotationConfigApplicationContext(DataProvider.class);
        CustomerDao repository = context.getBean(CustomerDao.class);

        // сохраним несколько пользователей
        repository.save(new Customer("Mihhail", "Popov"));
        repository.save(new Customer("Alex", "Nazarov"));
        repository.save(new Customer("Konstantin", "Kuznetsov"));
        repository.save(new Customer("Maria", "Sedova"));
        repository.save(new Customer("Victoria", "Polyakova"));

        // получим данные пользователей
        Iterable<Customer> customers = repository.findAll();
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
        System.out.println();

        // поиск пользователя по ID
        Customer customer = repository.findById(61L).orElse(null);
        System.out.println("Customer found with findOne(61L):");
        System.out.println("--------------------------------");
        System.out.println(customer);
        System.out.println();

        // поиск по фамилии
        List<Customer> bauers = repository.findByLastName("Nazarov");
        System.out.println("Customer found with findByLastName('Nazarov'):");
        System.out.println("--------------------------------------------");
        for (Customer bauer : bauers) {
            System.out.println(bauer);
        }

        // fetch customers by JSONB's last name
        List<Customer> bauers2 = repository.findByJsonbLastName("Polyakova");
        System.out.println("Customer found with findByJsonbLastName('Polyakova'):");
        System.out.println("--------------------------------------------");
        for (Customer bauer : bauers2) {
            System.out.println(bauer);
        }


        context.close();
    }
}
