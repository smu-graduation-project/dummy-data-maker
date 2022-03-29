import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DummyData {

    /** id, port, sequence, date, data
     * 전압, 전류, 온도, 위치
     * sequence는 순서대로 1씩 증가한다.( sequence가 중간에 없는 경우는 sensor node에서 값을 제대로 보내지 못한 것이다.)
     * date는 이후에 날짜별로 데이터를 보여줘야 하는 경우 사용하면 좋을 것 같다.
     **/

//    private Long id; // pk로 설정하여 제외
    private int port;
    private BigInteger sequence;
    private String data; // 이후에 사용 (실제 데이터는 data 형식이 아스키코드로 되어있음.)
//    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 날짜 포멧 (mysql: timestamp)

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public BigInteger getSequence() {
        return sequence;
    }

    public void setSequence(BigInteger sequence) {
        this.sequence = sequence;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
