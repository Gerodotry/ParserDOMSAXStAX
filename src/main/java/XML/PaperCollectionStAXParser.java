package XML;

import Model.CharsType;
import Model.Paper;
import Model.PaperCollection;
import Model.YesNoType;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class PaperCollectionStAXParser implements PaperCollectionParser {

    @Override
    public PaperCollection parse(String fileName) throws Exception {
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(fileName);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(fileInputStream);

            PaperCollection paperCollection = null;
            Paper currentPaper = null;
            CharsType currentChars = null;
            String currentElement = null;

            while (reader.hasNext()) {
                int event = reader.next();

                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        currentElement = reader.getLocalName();
                        if ("Papers".equals(currentElement)) {
                            paperCollection = new PaperCollection();
                        } else if ("Paper".equals(currentElement)) {
                            currentPaper = new Paper();
                            currentPaper.setID(reader.getAttributeValue(null, "ID"));
                        } else if ("Chars".equals(currentElement)) {
                            currentChars = new CharsType();
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        String text = reader.getText().trim();
                        if (!text.isEmpty()) {
                            if ("Title".equals(currentElement)) {
                                currentPaper.setTitle(text);
                            } else if ("Type".equals(currentElement)) {
                                currentPaper.setType(text);
                            } else if ("Monthly".equals(currentElement)) {
                                currentPaper.setMonthly(YesNoType.valueOf(text.toUpperCase()));
                            } else if ("Color".equals(currentElement)) {
                                currentChars.setColor(Boolean.parseBoolean(text));
                            } else if ("NumberOfPages".equals(currentElement)) {
                                currentChars.setNumberOfPages(Integer.parseInt(text));
                            } else if ("Glossy".equals(currentElement)) {
                                currentChars.setGlossy(Boolean.parseBoolean(text));
                            } else if ("HasSubscriptionIndex".equals(currentElement)) {
                                currentChars.setHasSubscriptionIndex(Boolean.parseBoolean(text));
                            }
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        currentElement = reader.getLocalName();
                        if ("Paper".equals(currentElement)) {
                            currentPaper.setChars(currentChars);
                            paperCollection.addPaper(currentPaper);
                        }
                        break;
                }
            }

            return paperCollection;
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
    }
}
