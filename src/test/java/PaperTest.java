import Model.Paper;
import Model.YesNoType;
import Model.CharsType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PaperTest {

    @Test
    public void testPaperEquals() {
        Paper paper1 = new Paper("p1", "Title", "Type", YesNoType.TRUE, new CharsType());
        Paper paper2 = new Paper("p1", "Title", "Type", YesNoType.TRUE, new CharsType());
        Paper paper3 = new Paper("p2", "Title", "Type", YesNoType.TRUE, new CharsType());

        assertEquals(paper1, paper2);
        assertNotEquals(paper1, paper3);
    }

    @Test
    public void testPaperHashCode() {
        Paper paper1 = new Paper("p1", "Title", "Type", YesNoType.TRUE, new CharsType());
        Paper paper2 = new Paper("p1", "Title", "Type", YesNoType.TRUE, new CharsType());

        assertEquals(paper1.hashCode(), paper2.hashCode());
    }
}
