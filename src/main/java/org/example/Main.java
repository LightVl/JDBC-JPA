package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MyApplicationContextConfiguration.class);
        DataSource dataSource = ctx.getBean(DataSource.class);
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rst = stmt.executeQuery("select f_name, l_name, title from employee_data");
            while (rst.next()) {
                System.out.print(rst.getString(1)+" ");
                System.out.print(rst.getString(2)+" ");
                System.out.println(rst.getString(3));
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}