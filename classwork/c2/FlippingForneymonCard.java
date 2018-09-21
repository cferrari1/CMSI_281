// Christopher Ferrari

package classwork.c2;

public class FlippingForneymonCard extends ForneymonCard {

    private boolean faceDown;

    public FlippingForneymonCard () {
        super();
        faceDown = true;
    }

    public FlippingForneymonCard (String fName, String fType, boolean down) {
        super(fName, fType);
        faceDown = down;
    }

    public boolean flip() {
        if (faceDown) {
            faceDown = false;
        } else {
            faceDown = true;
        }

        return faceDown;
    }

    public int match (FlippingForneymonCard other) {
        if (other.faceDown || this.faceDown) {
            return 2;
        } else if (this.getName().equals(other.getName()) && this.getType().equals(other.getType())) {
            return 1;
        } else {
            return 0;
        }        
    }

    public String toString() {
        if (faceDown) {
            return "?: ?";
        }

        return super.toString();
    }

}
