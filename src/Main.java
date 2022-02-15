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

        Long id = Long.valueOf(0); // 이후 시간을 유동적으로 변경하기 위해 Long type으로 설정

        while(true) {
            // 참고 : 하루 = 86,400초
            while(id.longValue() < 100000) {
                // 1/10000확률로 데이터를 넘기지 않는다.(sequence 문제 발생)
                int rand = random.nextInt(10000);
                // index를 기반으로 sequence, prot 설정
                if (rand != 1) {
                    jdbcController.pushDummyData(randomDummyData.makeDummyData(0), conn);
                }
                else {
                    randomDummyData.addSequence(0);
                }
                if (rand != 2) {
                    jdbcController.pushDummyData(randomDummyData.makeDummyData(1), conn);
                }
                else {
                    randomDummyData.addSequence(1);
                }
                if (rand != 3) {
                    jdbcController.pushDummyData(randomDummyData.makeDummyData(2), conn);
                }
                else {
                    randomDummyData.addSequence(2);
                }
                if (rand != 4) {
                    jdbcController.pushDummyData(randomDummyData.makeDummyData(3), conn);
                }
                else {
                    randomDummyData.addSequence(3);
                }
                id++;

                try {
                    // 딜레이 시간
                    TimeUnit.SECONDS.sleep(DELAYTIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            // 데이터의 지속적인 누적을 막기위해 테이블을 reset.
            System.out.println("reset table");
            jdbcController.remakeTable(conn);
            randomDummyData.resetData();
            id = Long.valueOf(0);
        }
    }
}