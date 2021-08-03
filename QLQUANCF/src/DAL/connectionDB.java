package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionDB {

    private Connection connection;

    private connectionDB(){
        try {
        	
//        	String url ="jdbc:mysql://localhost:3036/test"+","+"root"+","+"20211516h";
        	String url = "jdbc:mysql://localhost:3306/qlquancafe?user=root&password=20211516h&useUnicode=true&characterEncoding=utf8";
            connection = DriverManager.getConnection(url);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static class ConnectionHelper {
        
        public static final connectionDB instance = new connectionDB();
    }

    public static connectionDB getInstance() {

        return ConnectionHelper.instance;
    }

    public Connection getConnection() {

        return connection;
    }

}
