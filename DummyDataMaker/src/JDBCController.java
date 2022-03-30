import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static java.time.LocalTime.now;

public class JDBCController {

    private Statement stmt;
    public void pushDummyData(DummyData dummyData, Connection conn) {
        // data 입력
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO dummyData (" +
                    "port, sequence, date, data)" +
                    "values ("
                    +dummyData.getPort()+", "
                    +dummyData.getSequence()+","
                    +now() +","
                    +dummyData.getData()+");");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Table 제거 및 재생성
    public void remakeTable(Connection conn) {
        dropTable(conn);
        makeTable(conn);
    }

    private void makeTable(Connection conn) {
        // 테이블 만들기 (기준은 dummyData)
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(" CREATE TABLE dummyData ( " +
                    "id BIGINT NOT NULL AUTO_INCREMENT," +
                    "port INT NOT NULL, " +
                    "sequence  BIGINT NOT NULL," +
                    "date TIMESTAMP NOT NULL," +
                    "data TEXT," +
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