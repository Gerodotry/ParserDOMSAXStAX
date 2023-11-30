package Model;

public enum YesNoType {
    TRUE("true"),
    FALSE("false");

    private String value;

    YesNoType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
