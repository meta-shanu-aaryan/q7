import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SparseMatrixTest {
    SparceMat sm;

    @Test
    public void transposeTest() {
        List<int[]> ls = new ArrayList<>();
        ls.add(new int[] { 0, 0, 1 });
        ls.add(new int[] { 1, 1, 3 });
        sm = new SparceMat(3, 2, ls);
    }
}