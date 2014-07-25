package com.icap;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class VirusScanTest {
    public static void main(String[] args)
    {
        try{
        	// Replace with your ICAP hostname/IP address, icap port and icap service name
            ICAP icap = new ICAP("127.0.0.1",1344,"srv_clamav");
            
            // Replace with path to files
            String[] files = new String[]{
                 "C:/Users/userName/Desktop/Output.txt"
            };
            
            for(String file : files) {
                try {
                    System.out.print(file + ": ");
                    FileInputStream is = new FileInputStream(new File(file));
                   /* String myString = "X5O!P%@AP[4\\PZX54(P^)7CC)7}$EICAR-STANDARD-ANTIVIRUS-TEST-FILE!$H+H*";
                    
                    InputStream is = new ByteArrayInputStream (myString.getBytes( "UTF-8" ) );*/
                    boolean result = icap.scanStream(is);
                    System.out.println(result == true ? "Clean" : "Infected");
                } catch (ICAPException ex) {
                    System.err.println("Could not scan file " + file + ": " + ex.getMessage());
                    ex.printStackTrace();
                } catch (IOException ex) {
                    System.err.println("IO error occurred when scanning file " + file + ": " );
                    ex.printStackTrace();
                }
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        catch(ICAPException e){
            System.out.println(e.getMessage());
        }
        
   }
}