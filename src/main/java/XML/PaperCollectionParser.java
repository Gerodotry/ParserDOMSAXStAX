package XML;

import Model.PaperCollection;

public interface PaperCollectionParser {
    PaperCollection parse(String fileName) throws Exception;
}

