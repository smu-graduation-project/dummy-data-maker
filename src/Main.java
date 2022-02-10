import com.mysql.cj.xdevapi.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


import java.io.FileReader;
import java.io.Reader;
import java.sql.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    public static int DELAYTIME = 1; //데이터 전송 주기

    public static void main(String[] args) throws Exception {

        JDBCController jdbcController = new JDBCController();
        RandomDummyData randomDummyData = new RandomDummyData();

        Reader reader = new FileReader(Main.class.getResource("").getPath()+"settingJDBC.json");
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(reader);

        JSONObject settingJDBC = (JSONObject)obj;

        Random random = new Random();

        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection((String)settingJDBC.get("JDBCURL"), (String)settingJDBC.get("MYSQLID"), (String)settingJDBC.get("MYSQLPASSWORD"));
            Statement stmt = conn.createStatement();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        Long id = Long.valueOf(0);

        while(true) {
            // 기준 : 하루 = 86,400초
            while(id.longValue() < 100000) {
                int rand = random.nextInt(10000);
                // index를 기반으로 sequence, prot 설정
                if (rand != 1) jdbcController.pushDummyData(randomDummyData.makeDummyData(0), conn);
                if (rand != 2) jdbcController.pushDummyData(randomDummyData.makeDummyData(1), conn);
                if (rand != 3) jdbcController.pushDummyData(randomDummyData.makeDummyData(2), conn);
                if (rand != 4) jdbcController.pushDummyData(randomDummyData.makeDummyData(3), conn);

                id++;

                try {
                    // 딜레이 시간
                    TimeUnit.SECONDS.sleep(DELAYTIME);
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
