package UTP9_1;

import java.sql.*;

public class Database {
    private TravelData travelData;
    private Connection connection;

    public Database(String url, TravelData travelData ) {
        this.travelData = travelData;
        try {
            //ToDo Be Careful. You need to download and setup the sqlite library, for the project to work
            Class.forName("org.sqlite.JDBC");
//            connection = DriverManager.getDriver("net.sourceforge.jtds.jdbc.Driver").connect(url, null);
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void create() {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute("CREATE TABLE if not exists TRAVELDATA (data TEXT);");
            PreparedStatement insert = connection.prepareStatement("INSERT INTO TRAVELDATA (data) VALUES (?)");
            for (Travel travel : travelData.getData()) {
                insert.setString(1, travel.toString());
                insert.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showGui() {
        Window window = new Window(travelData.getData());
        window.showGUI();
    }
}
