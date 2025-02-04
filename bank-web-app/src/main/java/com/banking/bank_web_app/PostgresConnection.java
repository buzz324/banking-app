package com.banking.bank_web_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresConnection {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/transaction-db";
        String user = "postgres";
        String password = "12345";

        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");

            Statement statement = connection.createStatement();

            String createEnumSQL = "DO $$ BEGIN " +
                                   "IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'account_type_enum') THEN " +
                                   "CREATE TYPE account_type_enum AS ENUM ('savings', 'checking', 'business'); " +
                                   "END IF; " +
                                   "END $$;";
            statement.executeUpdate(createEnumSQL);
            System.out.println("ENUM type 'account_type_enum' created successfully.");

            String createUserTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                                        "id SERIAL PRIMARY KEY, " +
                                        "name VARCHAR(100) NOT NULL)";
            statement.executeUpdate(createUserTableSQL);
            System.out.println("Table 'users' created successfully.");

            String createAccountTableSQL = "CREATE TABLE IF NOT EXISTS accounts (" +
                                           "id SERIAL PRIMARY KEY, " +
                                           "user_id INT NOT NULL, " +
                                           "balance DECIMAL(10, 2) NOT NULL, " +
                                           "account_type account_type_enum NOT NULL, " +
                                           "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE)";
            statement.executeUpdate(createAccountTableSQL);
            System.out.println("Table 'accounts' created successfully.");

            String createTransactionTableSQL = "CREATE TABLE IF NOT EXISTS transactions (" +
                                               "id SERIAL PRIMARY KEY, " +
                                               "user_id INT NOT NULL, " +
                                               "account_from account_type_enum NOT NULL, " +
                                               "account_to account_type_enum NOT NULL, " +
                                               "amount DECIMAL(10, 2) NOT NULL, " +
                                               "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE)";
            statement.executeUpdate(createTransactionTableSQL);
            System.out.println("Table 'transactions' created successfully.");

            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM users");
            rs.next();
            if (rs.getInt(1) == 0) {
                String insertUsersDataSQL = "INSERT INTO users (name) VALUES " +
                                            "('Alice'), " +
                                            "('Bob'), " +
                                            "('Charlie')";
                statement.executeUpdate(insertUsersDataSQL);
                System.out.println("Inserted raw data into 'users' table successfully.");
            }

            rs = statement.executeQuery("SELECT COUNT(*) FROM accounts");
            rs.next();
            if (rs.getInt(1) == 0) {
                String insertAccountsDataSQL = "INSERT INTO accounts (user_id, balance, account_type) VALUES " +
                                               "(1, 1000.00, 'savings'), " +
                                               "(1, 1500.50, 'checking'), " +
                                               "(1, 2000.75, 'business'), " +
                                               "(2, 1100.00, 'savings'), " +
                                               "(2, 1600.50, 'checking'), " +
                                               "(2, 2100.75, 'business'), " +
                                               "(3, 1200.00, 'savings'), " +
                                               "(3, 1700.50, 'checking'), " +
                                               "(3, 2200.75, 'business')";
                statement.executeUpdate(insertAccountsDataSQL);
                System.out.println("Inserted raw data into 'accounts' table successfully.");
            }

            rs = statement.executeQuery("SELECT COUNT(*) FROM transactions");
            rs.next();
            if (rs.getInt(1) == 0) {
                String insertTransactionsDataSQL = "INSERT INTO transactions (user_id, account_from, account_to, amount) VALUES " +
                                                   "(1, 'savings', 'checking', 100.00), " +
                                                   "(2, 'checking', 'business', 200.50), " +
                                                   "(3, 'business', 'savings', 300.75)";
                statement.executeUpdate(insertTransactionsDataSQL);
                System.out.println("Inserted raw data into 'transactions' table successfully.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}