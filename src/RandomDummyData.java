import java.time.LocalDate;
import java.util.Random;

public class RandomDummyData {

    DummyData dummy = new DummyData();
    Random random = new Random();
    LocalDate localDate;
    private int[] ports = {10,11,12,13};
    private int[] sequences = {1,1,1,1};

    public DummyData makeDummyData(Long id, int index) {
        dummy.setId(id);
        dummy.setPort(ports[index]);
        dummy.setSequence(sequences[index]++);
//        dummy.setDate(new Date(System.currentTimeMillis()));
        dummy.setData(random.nextDouble()*100);

        return dummy;
    }

}
