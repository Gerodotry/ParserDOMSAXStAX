package Controller;

import Model.PaperCollection;
import Model.Paper;
import Model.PaperComparator;
import XML.ValidatorXML;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    public static boolean validate(String file, String schema) {
        boolean result = ValidatorXML.validate(file, schema);
        if (!result) {
            System.out.println("Validation failed");
        } else {
            System.out.println("Validation succeed");
        }
        return result;
    }

    public static List<Paper> getSortedList(PaperCollection collection) {
        List<Paper> papers = collection.getAllPapers();
        Collections.sort(papers, new PaperComparator());
        return papers;
    }
    public static List<Paper> getSortedListByNumberOfPages(PaperCollection paperCollection) {
        List<Paper> paperList = paperCollection.getAllPapers();

        return paperList.stream()
                .sorted(Comparator.comparing(paper -> paper.getChars().getNumberOfPages()))
                .collect(Collectors.toList());
    }
    public static List<Paper> searchByTitleLength(PaperCollection collection, int minLength, int maxLength) {
        List<Paper> papers = collection.getAllPapers();
        List<Paper> result = new ArrayList<>();
        for (Paper paper : papers) {
            int titleLength = paper.getTitle().length();
            if (titleLength >= minLength && titleLength <= maxLength) {
                result.add(paper);
            }
        }
        return result;
    }

    public static List<Paper> searchByPageCount(PaperCollection collection, int minPages, int maxPages) {
        List<Paper> papers = collection.getAllPapers();
        List<Paper> result = new ArrayList<>();
        for (Paper paper : papers) {
            int pageCount = paper.getChars().getNumberOfPages(); // Change here
            if (pageCount >= minPages && pageCount <= maxPages) {
                result.add(paper);
            }
        }
        return result;
    }

    public static String getPapers(List<Paper> paperList) {
        StringBuilder result = new StringBuilder();
        for (Paper paper : paperList) {
            result.append("\n").append(paper);
        }
        return result.toString() + "\n***";
    }
}
