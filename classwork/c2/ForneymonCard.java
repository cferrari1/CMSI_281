// Christopher Ferrari

package classwork.c2;

public class ForneymonCard {

    private String name;
    private String type;

    public ForneymonCard() {
        name = "MissingNu";
        type = "Burnymon";
    }

    public ForneymonCard(String fName, String fType) {
        if (fName.equals("")) {
            throw new IllegalArgumentException();
        }

        if ((!fType.equals("Burnymon")) && (!fType.equals("Dampymon")) && (!fType.equals("Leafymon"))) {
            throw new IllegalArgumentException();
        }

        name = fName;
        type = fType;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return (type + ": " + name);
    }

}
