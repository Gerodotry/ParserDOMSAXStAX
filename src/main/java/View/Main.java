package View;
import Controller.Controller;
import Model.PaperCollection;
import Model.Paper;
import XML.PaperCollectionDOMParser;
import XML.PaperCollectionParser;
import XML.PaperCollectionSAXParser;
import XML.PaperCollectionStAXParser;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Incorrect param, use with param:\n\t{xml document} {xsd schema}");
            System.exit(1);
        }
        System.out.println("Validate " + args[0] + " with " + args[1] + " schema.");
        if (!Controller.validate(args[0], args[1])) {
            System.out.println("Validation failed.");
            System.exit(1);
        }

        Scanner in = new Scanner(System.in);
        System.out.println("Choose parser:\n" +
                "1 - DOM\n"+
                "2 - SAX\n"+
                "3 - StAX");



        PaperCollectionParser parser = null;
        int choice;
        while (true) {
            choice = in.nextInt();
            switch (choice) {
                case 1:
                    parser = new PaperCollectionDOMParser();
                    break;
                case 2:
                    parser = new PaperCollectionSAXParser();
                    break;
                case 3:
                    parser = new PaperCollectionStAXParser();
                    break;
                default:
                    System.out.println("Incorrect choice. Try again.");
                    break;
            }
            if (parser != null) {
                break;
            }
        }

        PaperCollection paperCollection = null;
        try {
            paperCollection = parser.parse(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        System.out.println("Use " + parser.getClass().getSimpleName());
        System.out.println(paperCollection.getName());

        List<Paper> paperList = paperCollection.getAllPapers();

        System.out.println("All papers:");
        System.out.println(Controller.getPapers(paperList));

        // Search by title length, sort by type
        List<Paper> filteredByLength = Controller.searchByTitleLength(paperCollection, 3, 50);
        System.out.println("Papers with title length between 2 and 50: ");
        System.out.println(Controller.getPapers(filteredByLength));

        List<Paper> sortedByType = Controller.getSortedList(paperCollection);
        System.out.println("Sorted by type papers: ");
        System.out.println(Controller.getPapers(sortedByType));

        List<Paper> sortedByNumberOfPages = Controller.getSortedListByNumberOfPages(paperCollection);
        System.out.println("Sorted by NumberOfPages papers: ");
        System.out.println(Controller.getPapers(sortedByNumberOfPages));
    }
}
