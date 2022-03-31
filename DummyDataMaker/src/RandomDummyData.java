import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Random;

public class RandomDummyData {

    DummyData dummy = new DummyData();
    Random random = new Random();
    private int[] ports = {10,11,12,13};
    private BigInteger[] sequences = {new BigInteger("0"), new BigInteger("0"), new BigInteger("0"), new BigInteger("0")};

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

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 2; j++) {
                sb.append(random.nextInt(9)+30);
            }
            sb.append("2e");
            for(int j = 0; j < 2; j++) {
                sb.append(random.nextInt(9)+30);
            }
        }
        return sb.toString();
    }

}
