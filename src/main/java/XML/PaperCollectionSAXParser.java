package XML;

import Model.CharsType;
import Model.Paper;
import Model.PaperCollection;
import Model.YesNoType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class PaperCollectionSAXParser implements PaperCollectionParser {

    @Override
    public PaperCollection parse(String fileName) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        PaperCollectionHandler handler = new PaperCollectionHandler();
        saxParser.parse(new File(fileName), handler);

        return handler.getPaperCollection();
    }

    private static class PaperCollectionHandler extends DefaultHandler {
        private PaperCollection paperCollection;
        private Paper currentPaper;
        private CharsType currentChars;

        private StringBuilder currentElementValue;

        public PaperCollectionHandler() {
            currentElementValue = new StringBuilder();
        }

        public PaperCollection getPaperCollection() {
            return paperCollection;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            currentElementValue.setLength(0);

            if ("Papers".equals(qName)) {
                paperCollection = new PaperCollection(attributes.getValue("xmlns:xsi"));
            } else if ("Paper".equals(qName)) {
                currentPaper = new Paper();
                currentPaper.setID(attributes.getValue("ID"));
            } else if ("Chars".equals(qName)) {
                currentChars = new CharsType();
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            currentElementValue.append(ch, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ("Title".equals(qName)) {
                currentPaper.setTitle(currentElementValue.toString());
            } else if ("Type".equals(qName)) {
                currentPaper.setType(currentElementValue.toString());
            } else if ("Monthly".equals(qName)) {
                YesNoType isMonthly = "true".equalsIgnoreCase(currentElementValue.toString()) ? YesNoType.TRUE : YesNoType.FALSE;
                currentPaper.setMonthly(isMonthly);
            } else if ("Color".equals(qName)) {
                currentChars.setColor(Boolean.parseBoolean(currentElementValue.toString()));
            } else if ("NumberOfPages".equals(qName)) {
                currentChars.setNumberOfPages(Integer.parseInt(currentElementValue.toString()));
            } else if ("Glossy".equals(qName)) {
                currentChars.setGlossy(Boolean.parseBoolean(currentElementValue.toString()));
            } else if ("HasSubscriptionIndex".equals(qName)) {
                currentChars.setHasSubscriptionIndex(Boolean.parseBoolean(currentElementValue.toString()));
            } else if ("Paper".equals(qName)) {
                currentPaper.setChars(currentChars);
                paperCollection.addPaper(currentPaper);
            }
        }
    }
}
