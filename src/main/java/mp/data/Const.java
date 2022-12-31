package mp.data;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Const {
    public final static String URL = "jdbc:mysql://localhost:3306/mp";
    public final static String LOGIN = "Vadym";
    public final static String PASSWORD = "Vadym1234";

    public static Connection CONNECTION;
    public static PreparedStatement PREPARED_STATEMENT;
}
