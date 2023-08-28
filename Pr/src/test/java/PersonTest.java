import com.ITF.Person;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.sql.*;

public class PersonTest {


    private static final String DB_URL = "jdbc:mysql://localhost:3306/itf2";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "Bobita2@1";

    @Test
    public void AddPersonToDB() {

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            Person test = new Person(0, "test", "test", 0, "test");

            PreparedStatement statement = connection.prepareStatement("INSERT INTO Costumer VALUES(?,?,?,?,?)");
            statement.setInt(1, test.getId());
            statement.setString(2, test.getNume());
            statement.setString(3, test.getPrenume());
            statement.setInt(4, test.getVarsta());
            statement.setString(5, test.getEmail());
            statement.execute();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Costumer");
            boolean ok = false;
            while (resultSet.next()) {
                if (resultSet.getInt("id") == 0) {
                    ok = true;
                    break;
                }

            }

            assertEquals(true, ok);

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }


    @Test
    public void ChangePersonToDB() throws SQLException {

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            PreparedStatement statement = connection.prepareStatement("UPDATE Costumer SET NUME=? WHERE ID=?");
            statement.setString(1, "tested");
            statement.setInt(2, 0);
            statement.execute();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Costumer");
            boolean ok = false;
            while (resultSet.next()) {
                if (resultSet.getInt("id") == 0 && resultSet.getString("nume").equals("tested")) {
                    ok = true;
                    break;
                }

            }

            assertEquals(true, ok);

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }
}


