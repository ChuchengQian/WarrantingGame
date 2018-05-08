package comp1110.ass2.gui;

public class CardInfo {
    private String countryName;
    private String peopleName;
    private String nameInEnglish;
    private String color;
    private String code;

    public CardInfo(String countryName, String peopleName, String nameInEnglish, String color, String code) {
        this.countryName = countryName;
        this.peopleName = peopleName;
        this.nameInEnglish = nameInEnglish;
        this.color = color;
        this.code = code;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopelName) {
        this.peopleName = peopelName;
    }

    public String getNameInEnglish() {
        return nameInEnglish;
    }

    public void setNameInEnglish(String nameInEnglish) {
        this.nameInEnglish = nameInEnglish;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCode() {
        return code; }

    public void setCode(String code) {
        this.code = code; }
}
