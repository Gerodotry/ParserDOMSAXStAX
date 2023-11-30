import Controller.Controller;
import Model.Paper;
import Model.PaperCollection;
import Model.YesNoType;
import Model.CharsType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerTest {

    @Test
    public void testValidateValidFile() {
        String validXmlFile = "src/main/resources/paper.xml";
        String schemaFile = "src/main/resources/papers.xsd";

        boolean isValid = Controller.validate(validXmlFile, schemaFile);

        assertEquals(true, isValid, "Validation should succeed for a valid XML file.");
    }


    @Test public void testGetSortedList() {

        // Create a sample PaperCollection for testing
        PaperCollection collection = new PaperCollection("namespace");
        Paper paper1 = new Paper("p1", "Title1", "Type", YesNoType.TRUE, new CharsType());
        Paper paper2 = new Paper("p2", "Title2", "Type", YesNoType.FALSE, new CharsType());
        Paper paper3 = new Paper("p3", "Title3", "Type", YesNoType.TRUE, new CharsType());
        collection.addPaper(paper1);
        collection.addPaper(paper2);
        collection.addPaper(paper3);

        List<Paper> sortedList = Controller.getSortedList(collection);

        // Assuming PaperComparator sorts papers by title
        assertEquals("Title1", sortedList.get(0).getTitle(), "First paper should have Title1");
        assertEquals("Title2", sortedList.get(1).getTitle(), "Second paper should have Title2");
        assertEquals("Title3", sortedList.get(2).getTitle(), "Third paper should have Title3");
    }

    // Add similar tests for other Controller methods...
}
