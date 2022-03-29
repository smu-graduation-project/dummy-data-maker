import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Random;

public class RandomDummyData {

    DummyData dummy = new DummyData();
    Random random = new Random();
    private int[] ports = {10,11,12,13};
    private BigInteger[] sequences = {new BigInteger("1"), new BigInteger("1"), new BigInteger("1"), new BigInteger("1")};

    public DummyData makeDummyData(int index) {
        dummy.setPort(ports[index]);
        dummy.setSequence(sequences[index].add(new BigInteger("1")));
        dummy.setData(makeASCII());

        return dummy;
    }
    // 데이터 누락 발생시, 테스트용
    public void addSequence(int index) {
        sequences[index].add(new BigInteger("1"));
    }
    public void resetData() {
        sequences = new BigInteger[]{new BigInteger("1"), new BigInteger("1"), new BigInteger("1"), new BigInteger("1")};
    }

    private String makeASCII(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 2; j++) {
                sb.append((byte) random.nextInt());
            }
            sb.append((byte) '.');
            for(int j = 0; j < 2; j++) {
                sb.append((byte) random.nextInt());
            }
        }
        return sb.toString();
    }

}
