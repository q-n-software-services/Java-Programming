package week_8.q3_garden;

import java.util.Map;

public class InvoiceGenerator {
    
    static final String GARDENER_CONTACT = "GARDENER_CONTACT";
    
    static final String NAME = "NAME";
    static final String ADDRESS = "ADDRESS";
    static final String DATE = "DATE";
    static final String GARDEN_SIZE = "GARDEN_SIZE";
    static final String MOWING = "MOWING";
    static final String LEAVES = "LEAVES";
    static final String TOTAL = "TOTAL";


    private static String invoiceTemplate =
            "************ Garden Services Invoice ************" +
                    "\n" +
                    "\n&{GARDENER_CONTACT}" +
                    "\n" +
                    "\nCustomer Name: &{NAME}" +
                    "\nAddress of garden: &{ADDRESS}" +
                    "\n" +
                    "\nDate of service: &{DATE}" +
                    "\nSize of garden: &{GARDEN_SIZE}" +
                    "\n" +
                    "\nLawn mowing service charge: $ &{MOWING}" +
                    "\nLeaf raking service charge: $ &{LEAVES}" +
                    "\n" +
                    "\nTotal: $ &{TOTAL}" +
                    "\n" +
                    "\nPlease send payment to the address above." +
                    "\nThank you for your business.";


    public static String generate(Map<String, String> invoiceData) {


        // Add in the gardener info String
        invoiceData.put(GARDENER_CONTACT, GardenServiceData.gardenerContactString);
        

        return null;
    }

    
}
