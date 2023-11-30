package Model;

import java.util.Objects;

public class Paper {
    private String ID;
    private String title;
    private String type;
    private YesNoType monthly;
    private CharsType chars;

    public Paper(String ID, String title, String type, YesNoType monthly, CharsType chars) {
        this.ID = ID;
        this.title = title;
        this.type = type;
        this.monthly = monthly;
        this.chars = chars;
    }
    public Paper() {}

    public enum Fields {
        ID("ID"),
        TITLE("title"),
        TYPE("type"),
        MONTHLY("monthly"),
        CHARS("chars");

        private String fieldName;

        Fields(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFieldName() {
            return fieldName;
        }
    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public YesNoType getMonthly() {
        return monthly;
    }

    public void setMonthly(YesNoType monthly) {
        this.monthly = monthly;
    }

    public CharsType getChars() {
        return chars;
    }

    public void setChars(CharsType chars) {
        this.chars = chars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paper)) return false;
        Paper paper = (Paper) o;
        return Objects.equals(getID(), paper.getID()) &&
                Objects.equals(getTitle(), paper.getTitle()) &&
                Objects.equals(getType(), paper.getType()) &&
                getMonthly() == paper.getMonthly() &&
                Objects.equals(getChars(), paper.getChars());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getTitle(), getType(), getMonthly(), getChars());
    }

    @Override
    public String toString() {
        return "Paper{" +
                "ID='" + ID + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", monthly=" + monthly +
                ", chars=" + chars +
                '}';
    }
}
