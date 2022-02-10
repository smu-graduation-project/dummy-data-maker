import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCController {

    private Statement stmt;
    private String makeQuary = "";
    private String dropQuary = "";

    public void remakeTable(Connection conn) {
        dropTable(conn);
        makeTable(conn);
    }

    private void makeTable(Connection conn) {
        // 테이블 만들기 (기준은 dummyData)
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(makeQuary);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dropTable(Connection conn) {
        // 테이블 drop
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(dropQuary);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
