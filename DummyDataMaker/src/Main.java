import org.json.simple.JSONObject;
import org.json.simple.parser.*;


import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigInteger;
import java.sql.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final int DELAYTIME = 5; //데이터 전송 주기

    private static JDBCController jdbcController = new JDBCController();
    private static RandomDummyData randomDummyData = new RandomDummyData();
    private static Random random = new Random();
    private static Connection conn;

    public static void main(String[] args) throws Exception {
        JSONObject settingJDBC = getJsonObject();
        setJDBC(settingJDBC);

        jdbcController.remakeTable(conn);

        while (true) {
            makeAndPushData();
        }
    }

    private static void makeAndPushData() {
        // 확률적으로 데이터를 넘기지 않는다.(sequence 문제 발생을 가정)
        int rand = random.nextInt(100000);
        for (int i = 0; i < 4; i++) {
            pushData(jdbcController, randomDummyData, conn, rand, i);
        }
        delayTime();
    }

    private static void pushData(JDBCController jdbcController, RandomDummyData randomDummyData, Connection conn, int rand, int i) {
        if (rand != i) {
            jdbcController.pushDummyData(randomDummyData.makeDummyData(i), conn);
        } else {
            randomDummyData.addSequence(i);
        }
    }

    private static void setJDBC(JSONObject settingJDBC) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection((String) settingJDBC.get("JDBCURL"), (String)settingJDBC.get("MYSQLID"), (String) settingJDBC.get("MYSQLPASSWORD"));

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject getJsonObject() throws IOException, ParseException {
        Reader reader = new FileReader(Main.class.getResource("").getPath() + "settingJDBC.json");
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(reader);

        JSONObject settingJDBC = (JSONObject) obj;
        return settingJDBC;
    }

    private static void delayTime() {
        try {
            TimeUnit.SECONDS.sleep(DELAYTIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void resetDB() {
        System.out.println("reset table");
        jdbcController.remakeTable(conn);
        randomDummyData.resetData();
    }
}