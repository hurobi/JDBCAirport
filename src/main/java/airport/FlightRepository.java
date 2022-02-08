package airport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static airport.DatabaseConfig.*;

public class FlightRepository implements AutoCloseable {


    private Connection connection;


    public FlightRepository() {
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


    public void initFlightTable() {
        String sql = "CREATE TABLE IF NOT EXISTS flight (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "plane_id INT NOT NULL, " +
                "city_from_id INT NOT NULL, " +
                "city_to_id INT NOT NULL)";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createFlight(Flight newFlight) {

        String sql = "INSERT INTO city (name, longitude, latitude) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newCity.getName());
            preparedStatement.setDouble(2, newCity.getLongitude());
            preparedStatement.setDouble(3, newCity.getLatitude());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public City findCityById(int id) {
        City city = null;

        String sql = "SELECT name, longitude, latitude FROM city WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                city = new City(
                        id,
                        resultSet.getString("name"),
                        resultSet.getDouble("longitude"),
                        resultSet.getDouble("latitude"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    public List<City> listAllCities() {
        List<City> cityList = new ArrayList<>();
        String sql = "SELECT id, name, longitude, latitude FROM city";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                cityList.add(new City(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("longitude"),
                        resultSet.getDouble("latitude")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cityList;
    }

}




