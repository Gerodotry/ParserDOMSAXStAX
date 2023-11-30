package Model;

import java.util.List;
import java.util.ArrayList;

public class Papers {
    private List<Paper> paperList;

    public Papers() {
        paperList = new ArrayList<>();
    }

    public List<Paper> getPaperList() {
        return paperList;
    }

    public void setPaperList(List<Paper> paperList) {
        this.paperList = paperList;
    }

    public void addPaper(Paper paper) {
        paperList.add(paper);
    }
}
