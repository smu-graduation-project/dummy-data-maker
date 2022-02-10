import java.text.SimpleDateFormat;

public class DummyData {

    // id, port, sequence, date, data

    // port 별로 전압, 전류, 온도, 위치 값이 다르다.
    // sequence는 순서대로 1씩 증가한다.( sequence가 중간에 없는 경우는 sensor node에서 값을 제대로 보내지 못한 것이다.)
    // date는 이후에 날짜별로 데이터를 보여줘야 하는 경우 사용하면 좋을 것 같다.

    private Long id;
    private int port;
    private int sequence;
    private String date; //  SimpleDateFormat "yyyy-mm-dd" 형태로 받아서, 처리
    private double data; // 일단 double로 설정, 실제 데이터는 다를 수 있음.

    public DummyData(Long id, int port, int sequence, SimpleDateFormat date, double data) {
        this.id = id;
        this.port = port;
        this.sequence = sequence;
        this.date = date.toString();
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getDate() {
        return date;
    }

    public void setDate(SimpleDateFormat date) {
        this.date = date.toString();
    }

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }
}
