package week_8.q3_garden;

import java.util.Map;

/**
 * You should not need to modify this file,
 * but you will need to call the methods here.
 */


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

        // TODO you don't need to modify this method, but you will need to call it.
        //  In GardenGUI, create a HashMap with invoice data, using the keys and values described above,
        //  and call this method to generate the invoice String.

        // Add in the gardener info String
        invoiceData.put(GARDENER_CONTACT, GardenServiceData.gardenerContactString);
        
        // Create a String Substitutor with the HashMap;
        // The HashMap keys will be used to look for placeholders in the invoice template
        // When a placeholder, eg. ${LEAVES} is found in the template, it will be replaced
        // with the value for the LEAVES key in the HashMap.
//        StrSubstitutor sub = new StrSubstitutor(invoiceData);
//
//        // Use our own template prefix
//        sub.setVariablePrefix("&{");
//
//        // Replace named placeholders with data from Map
//        String invoice = sub.replace(invoiceTemplate);
//        return invoice;
        return null;
    }

    
}
