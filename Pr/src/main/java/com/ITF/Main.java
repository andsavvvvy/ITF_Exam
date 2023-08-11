package com.ITF;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/itf2";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "Bobita2@1";

    public static void main(String[] args) {
        String order;
        Scanner scan = new Scanner(System.in);


        while (true) {
            System.out.print("Introduceti comanda: ");
            order = scan.nextLine();

            if (order.equals("+")) {
                String Nume, Prenume, Email;
                int Id, Varsta;

                System.out.print("Introduceti Id-ul ");
                Id = scan.nextInt();

                System.out.print("Introduceti Numele ");
                Nume = scan.next();

                System.out.print("Introduceti Preumele ");
                Prenume = scan.next();

                System.out.print("Introduceti Varsta ");
                Varsta = scan.nextInt();

                System.out.print("Introduceti Email ");
                Email = scan.next();

                Prs newMember = new Prs(Id, Nume, Prenume, Varsta, Email);
                insertPrs(newMember);

            } else if (order.equals("-")) {
                System.out.print("Id-ul clientului care a renuntat ");
                int id = scan.nextInt();
                delete_act(id);
            } else if (order.equals("~N")) {
                System.out.print("Id-ul clientului care si-a schimbat numele ");
                int id = scan.nextInt();
                System.out.print("Noul nume ");
                String NewName = scan.next();
                updatePrsName(id, NewName);
            } else if (order.equals("~E")) {
                System.out.print("Id-ul clientului care si-a schimbat email-ul ");
                int id = scan.nextInt();
                System.out.print("Noul email ");
                String NewMail = scan.next();
                updatePrsMail(id, NewMail);
            } else if (order.equals("?")) {
                System.out.print("Id-ul clientului ");
                int id = scan.nextInt();
                showById(id);
            } else if (order.equals("??")) {
                showAllCostumers();
            }


            if (order.equals("0")) {
                break;
            }
        }

    }

    private static void delete_act(int id) {

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            System.out.println("Connected to MySql database!");
            System.out.println("Prepare statement");

            PreparedStatement statement = connection.prepareStatement("DELETE FROM Costumer WHERE ID=?");
            statement.setInt(1, id);

            statement.execute();
            System.out.println("Delete executed successfully");
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }

    public static void updatePrsName(int id, String nume) {

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            System.out.println("Connected to MySql database!");
            System.out.println("Prepare statement");

            PreparedStatement statement = connection.prepareStatement("UPDATE Costumer SET NUME=? WHERE ID=?");
            statement.setString(1, nume);
            statement.setInt(2, id);

            statement.execute();
            System.out.println("Update executed successfully");
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }

    public static void updatePrsMail(int id, String email) {

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            System.out.println("Connected to MySql database!");
            System.out.println("Prepare statement");

            PreparedStatement statement = connection.prepareStatement("UPDATE Costumer SET EMAIL=? WHERE ID=?");
            statement.setString(1, email);
            statement.setInt(2, id);

            statement.execute();
            System.out.println("Update executed successfully");
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }

    public static void insertPrs(Prs Costumer) {

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            System.out.println("Connected to MySql database!");
            System.out.println("Prepare statement");

            PreparedStatement statement = connection.prepareStatement("INSERT INTO Costumer VALUES(?,?,?,?,?)");
            statement.setInt(1, Costumer.getId());
            statement.setString(2, Costumer.getNume());
            statement.setString(3, Costumer.getPrenume());
            statement.setInt(4, Costumer.getVarsta());
            statement.setString(5, Costumer.getEmail());


            statement.execute();
            System.out.println("Insert executed successfully");
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }

    public static void showAllCostumers() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            System.out.println("Connected to MySql database!");
            System.out.println("Prepare statement");
            Statement statement = connection.createStatement();
            System.out.println("Executing query: SELECT * FROM Costumer");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Costumer");
            System.out.println("Lista de persoane\n--------");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id") + " " + resultSet.getString("nume") +
                        " " + resultSet.getString("prenume") + " " + resultSet.getString("varsta") +
                        " " + resultSet.getString("email"));

            }
            System.out.println("--------");

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }

    public static void showById(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            System.out.println("Connected to MySql database!");
            System.out.println("Prepare statement");
            Statement statement = connection.createStatement();
            System.out.println("Executing query: SELECT * FROM Costumer");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Costumer");
            System.out.println("Lista de persoane\n--------");
            while (resultSet.next()) {
                if (resultSet.getInt("id") == id) {
                    System.out.println(resultSet.getInt("id") + " " + resultSet.getString("nume") +
                            " " + resultSet.getString("prenume") + " " + resultSet.getInt("varsta") +
                            " " + resultSet.getString("email"));
                    break;
                }
            }
            System.out.println("--------");

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }
}
