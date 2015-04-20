# Introduction #

Java API to interact with any ICAP server and get your content validated for virus. It accepts an input stream as input and gets it validated with any ICAP server. It's tested with c-icap server version 0.3.3 and its expected to work uniformly with other ICAP implementations as well.

## ICAP Protocol ##

The **Internet Content Adaption Protocol** is heavily inspired by HTTP but the use differs on some core aspects. ICAP is normally implemented as an addition to HTTP, where the HTTP request for a web page can be encapsulated and modified before the user gets the content. This way, a content filter like a anti-virus software, can be transparent to the end-user. In this project, it is just used as a file transfer protocol with a feedback from the server about the file's virus-status.

For further details on ICAP protocol kindly refer its [RFC](http://tools.ietf.org/html/rfc3507)

## Usage ##

The ICAP class is NOT thread-safe. If you have to transfer more than one file at a time, then instantiate the ICAP class for each connection.

## Public Java methods ##

### ICAP(String IP, int port, String ICAP service) ###

Given an IP-address, a port and an ICAP service name it will initialize a socket connection to the ICAP server. The preview size will be determined by an 'option' request from the ICAP server. This method throws an IOException if the socket connection or IO streams cannot be started. This lets the user of the class responsible for making a decision if such an error occurs. There is no good way to solve this exception  automatically.

### ICAP(String IP, int port, String ICAP service, int preview size) ###

Same as the one above, but the preview size assigned in the method call will be used to transfer files. Use this method to minimize overhead, but be sure to not change the ICAP server settings.

### scanStream(InputStream filename) ###

Given a inputstream, it will send the given inputstream through the initialized connection to the ICAP server. If the file is clean it will return true and if the file is infected it will return false.

## Example ##

```
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
```

### Download ###

Source code is available in SVN repository. Please refer the _**Source**_ Tab