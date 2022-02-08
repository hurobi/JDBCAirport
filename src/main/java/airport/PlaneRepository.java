package airport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static airport.DatabaseConfig.*;

public class PlaneRepository implements AutoCloseable {


    private Connection connection;


    public PlaneRepository() {
        try {
            this.connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }


    public void initPlaneTable() {
        String sql = "CREATE TABLE IF NOT EXISTS plane (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "serial_number VARCHAR(100) NOT NULL, " +
                "number_of_seats INT NOT NULL)";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlane(Plane newPlane) {

        String sql = "INSERT INTO plane (serial_number, number_of_seats) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newPlane.getSerialNumber());
            preparedStatement.setInt(2, newPlane.getNumberOfSeats());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Plane findPlaneById(int id) {
        Plane plane = null;

        String sql = "SELECT serial_number, number_of_seats FROM plane WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                plane = new Plane(
                        id,
                        resultSet.getString("serial_number"),
                        resultSet.getInt("number_of_seats"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return plane;
    }

    public List<Plane> listAllPlanes() {
        List<Plane> planeList = new ArrayList<>();
        String sql = "SELECT id, serial_number, number_of_seats FROM plane";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                planeList.add(new Plane(
                        resultSet.getInt("id"),
                        resultSet.getString("serial_number"),
                        resultSet.getInt("number_of_seats")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return planeList;
    }

}
