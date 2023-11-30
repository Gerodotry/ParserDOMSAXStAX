package Model;

import java.util.ArrayList;
import java.util.List;

public class PaperCollection {

    public String getName() {
        return name;
    }

    String name;
    List<Paper> papers;

    public enum Fields {
        NAME("name"),
        PAPERS("papers");

        private String name;

        Fields(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public void addPaper(Paper paper) {
        this.papers.add(paper);
    }

    public void removePaper(Paper paper) {
        this.papers.remove(paper);
    }

    public PaperCollection() {
        this.papers = new ArrayList<>();
    }

    public PaperCollection(String name) {
        this.name = name;
        this.papers = new ArrayList<>();
    }

    public PaperCollection(String name, List<Paper> papers) {
        this.name = name;
        this.papers = papers;
    }

    public List<Paper> getAllPapers() {
        return this.papers;
    }

    public void setName(String name) {
        this.name = name;
    }
}
