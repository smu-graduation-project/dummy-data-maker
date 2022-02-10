import java.time.LocalDate;
import java.util.Random;

public class RandomDummyData {

    DummyData dummy = new DummyData();
    Random random = new Random();
    LocalDate localDate;
    private int[] ports = {10,11,12,13};
    private int[] sequences = {1,1,1,1};

    public DummyData makeDummyData(int index) {
        dummy.setPort(ports[index]);
        dummy.setSequence(sequences[index]++);
        dummy.setData(random.nextDouble()*100);

        return dummy;
    }

}
