import Model.PaperCollection;
import XML.PaperCollectionDOMParser;
import org.junit.jupiter.api.Test;
import Model.Paper;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaperCollectionDOMParserTest {

    @Test
    public void testParse() throws Exception {
        String fileName = "src/main/resources/paper.xml";
        PaperCollectionDOMParser parser = new PaperCollectionDOMParser();
        PaperCollection paperCollection = parser.parse(fileName);

        // Assert the expected size of the paper collection
        assertEquals(3, paperCollection.getAllPapers().size());

    }
}
