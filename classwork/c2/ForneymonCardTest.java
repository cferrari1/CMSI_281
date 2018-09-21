// Christopher Ferrari

package classwork.c2;

public class ForneymonCardTest {
    public static void main(String[] args) {
        FlippingForneymonCard terribly = new FlippingForneymonCard("terribly", "Dampymon", true);
        // "?: ?"
        System.out.println(terribly);
        terribly.flip();
        // "Dampymon: terribly"
        System.out.println(terribly + "\n");

        FlippingForneymonCard terribly2 = new FlippingForneymonCard("terribly", "Dampymon", true);
        // "?: ?"
        System.out.println(terribly2 + "\n");

        FlippingForneymonCard terribly3 = new FlippingForneymonCard("terribly", "Burnymon", false);
        // "Burnymon: terribly"
        System.out.println(terribly3 + "\n");

        // "2"
        System.out.println(terribly.match(terribly2));
        terribly2.flip();

        // "1"
        System.out.println(terribly.match(terribly2));

        // "0"
        System.out.println(terribly.match(terribly3));

    }

}
