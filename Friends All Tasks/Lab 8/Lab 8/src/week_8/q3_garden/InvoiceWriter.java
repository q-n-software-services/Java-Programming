package week_8.q3_garden;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InvoiceWriter {
    
    static final String INVOICE_DIRECTORY = "GardeningInvoices";
    
    private static final String dateFormatString = "MMM_dd_yyyy";   // e.g. "sep_09_2017"
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatString);
    
    
    static {
        
        File invoiceDir = new File(INVOICE_DIRECTORY);
        try {
            invoiceDir.mkdir();   // We may ignore the warning about return value being ignored.
        } catch (SecurityException e) {
            if (!invoiceDir.exists()) {
                System.out.println("ERROR - could not create Invoice Directory.  " + INVOICE_DIRECTORY);
            }
            
        }
    }
    

    public static String createFileName(String customer, String date) {
        
        String name = eliminateBadCharacters(customer);
        if (name.length() == 0) {
            name = "Customer";
        }

        
        String filename = String.format("%s_%s_invoice", name, date);
        filename = eliminateBadCharacters(filename);
        filename = filename + ".txt";

        
        return filename;
        
    }
    
    
    protected static String eliminateBadCharacters(String st) {

        return st.replaceAll("[^a-zA-Z0-9]", "");
    }
    

    
    public static boolean writeToFile(String filename, String text) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(INVOICE_DIRECTORY, filename)))) {
            writer.write(text);
        } catch (IOException e) {
            System.out.println("Error in file writing" + filename + ". Error message:\n" + e.getMessage());
            return false;
        }
        return true;
    }
}
