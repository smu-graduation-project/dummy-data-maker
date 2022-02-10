import java.sql.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        JDBCController jdbcController = new JDBCController();
        RandomDummyData randomDummyData = new RandomDummyData();
        Random random = new Random();
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/loraDummyData?useUnicode=true&" +
                            "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "root");
            Statement stmt = conn.createStatement();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        Long id = Long.valueOf(0);

        while(true) {
            while(id.longValue() < 10000) {
                int rand = random.nextInt(10000);
                if (rand != 1) jdbcController.pushDummyData(randomDummyData.makeDummyData(id++, 0), conn);
                if (rand != 2) jdbcController.pushDummyData(randomDummyData.makeDummyData(id++, 1), conn);
                if (rand != 3) jdbcController.pushDummyData(randomDummyData.makeDummyData(id++, 2), conn);
                if (rand != 4) jdbcController.pushDummyData(randomDummyData.makeDummyData(id++, 3), conn);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.println("reset table");
            jdbcController.remakeTable(conn);
            id = Long.valueOf(0);
        }
    }
}
