package Model;

import java.util.Comparator;


public class PaperComparator implements Comparator<Paper> {


    private int getNumber(String type) {
        if (type.equals("Newspaper"))
            return 1;
        if (type.equals("Magazine"))
            return 2;
        if (type.equals("Brochure"))
            return 3;
        // Add more types as needed

        return 0;
    }

    @Override
    public int compare(Paper o1, Paper o2) {
        return getNumber(o1.getType()) - getNumber(o2.getType());
    }
}
