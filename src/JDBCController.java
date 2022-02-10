import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCController {

    private Statement stmt;
    public void pushDummyData(DummyData dummyData, Connection conn) {
        // data 입력
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO dummyData (" +
                    "port, sequence, date, data)" +
                    "values ("+dummyData.getPort()+", "+dummyData.getSequence()+", now(), "+String.format("%.3f", dummyData.getData())+");");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remakeTable(Connection conn) {
        dropTable(conn);
        makeTable(conn);
    }

    private void makeTable(Connection conn) {
        // 테이블 만들기 (기준은 dummyData)
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(" CREATE TABLE dummyData ( " +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "port INT NOT NULL, " +
                    "sequence  INT NOT NULL," +
                    "date TIMESTAMP NOT NULL," +
                    "data DOUBLE," +
                    "PRIMARY KEY(id));");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dropTable(Connection conn) {
        // 테이블 drop
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("DROP TABLE dummyData;");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}