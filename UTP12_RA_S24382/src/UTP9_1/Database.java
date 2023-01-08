package UTP9_1;

import java.sql.*;

public class Database {
    private TravelData travelData;
    private Connection connection;

    public Database(String url, TravelData travelData ) {
        this.travelData = travelData;
        try {
//            connection = DriverManager.getConnection(url, "s24382","");
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
                throw new RuntimeException(e);
        }
    }

    public void create() {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute("IF (SELECT * FROM TRAVELDATA) IS NULL CREATE TABLE TRAVELDATA (data TEXT);");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO TRAVELDATA (data) VALUES (?)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showGui() {
    }
}
