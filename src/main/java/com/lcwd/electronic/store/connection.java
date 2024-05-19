package com.lcwd.electronic.store;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class connection {



        public static void main(String[] args) throws SQLException {
            // JDBC URL for your database, replace it with your actual URL
            String jdbcUrl = "jdbc:mysql://localhost:3306/electronic_store";

            // Database credentials
            String username = "root";
            String password = "root";

            Connection connection= DriverManager.getConnection(jdbcUrl,username,password);


//            try {
//                // Load the JDBC driver
//                Class.forName("com.mysql.cj.jdbc.Driver");
//
//                // Establish the connection
//                Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
//
//                // Check if connection is successful
//                if (connection != null) {
//                    System.out.println("Connection successful!");
//                    connection.close(); // Close the connection
//                } else {
//                    System.out.println("Failed to make connection!");
//                }
//            } catch (ClassNotFoundException e) {
//                System.out.println("MySQL JDBC Driver not found!");
//                e.printStackTrace();
//            } catch (SQLException e) {
//                System.out.println("Connection failed! Check output console");
//                e.printStackTrace();
//            }
        }
    }

