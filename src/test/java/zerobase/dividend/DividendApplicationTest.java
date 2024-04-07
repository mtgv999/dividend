package zerobase.dividend;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.springframework.boot.test.context.SpringBootTest;
//@SpringBootTest
public class DividendApplicationTest {//[2]
    @Test void add(){Math math=new Math();
    List<List<Integer>>list=List.of(List.of(1,2,3),
            List.of(2,3,5),List.of(10,10,20));
list.forEach(e->assertEquals(e.get(2),math.add(e.get(0),e.get(1))));}}