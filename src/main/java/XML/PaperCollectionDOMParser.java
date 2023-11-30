package XML;

import Model.PaperCollection;
import Model.Paper;
import Model.YesNoType;
import Model.CharsType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class PaperCollectionDOMParser implements PaperCollectionParser {

    @Override
    public PaperCollection parse(String fileName) throws Exception {
        File inputFile = new File(fileName);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        NodeList papersNodes = doc.getElementsByTagName("Paper");
        NodeList nameNode = doc.getElementsByTagName("Papers");

        PaperCollection paperCollection = new PaperCollection(nameNode.item(0).getAttributes().getNamedItem("xmlns:xsi").getTextContent());

        for (int i = 0; i < papersNodes.getLength(); i++) {
            Node node = papersNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String ID = element.getAttribute("ID");
                String title = element.getElementsByTagName("Title").item(0).getTextContent();
                String type = element.getElementsByTagName("Type").item(0).getTextContent();
                String monthly = element.getElementsByTagName("Monthly").item(0).getTextContent();
                YesNoType isMonthly = "true".equalsIgnoreCase(monthly) ? YesNoType.TRUE : YesNoType.FALSE;

                Element charsElement = (Element) element.getElementsByTagName("Chars").item(0);
                boolean color = Boolean.parseBoolean(charsElement.getElementsByTagName("Color").item(0).getTextContent());
                int numberOfPages = Integer.parseInt(charsElement.getElementsByTagName("NumberOfPages").item(0).getTextContent());
                boolean glossy = Boolean.parseBoolean(charsElement.getElementsByTagName("Glossy").item(0).getTextContent());
                boolean hasSubscriptionIndex = Boolean.parseBoolean(charsElement.getElementsByTagName("HasSubscriptionIndex").item(0).getTextContent());

                CharsType charsType = new CharsType();
                charsType.setColor(color);
                charsType.setNumberOfPages(numberOfPages);
                charsType.setGlossy(glossy);
                charsType.setHasSubscriptionIndex(hasSubscriptionIndex);

                Paper paper = new Paper(ID, title, type, isMonthly, charsType);
                paperCollection.addPaper(paper);
            }
        }

        return paperCollection;
    }
}
