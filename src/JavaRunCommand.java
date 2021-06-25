import java.io.*;

public class JavaRunCommand {

    public static void main(String args[]) throws IOException {

        String s = null;

       
            
	    // run the Unix "ps -ef" command
            // using the Runtime exec method:
        	// Runtime.getRuntime().exec("runas /profile /user:roeysdomi /savecred /");
        	//runas /profile /user:Administrator /savecred
        Process p = Runtime
                .getRuntime()
                .exec(" netsh interface set interface name=\"roey\" admin=enabled");
        Runtime
        .getRuntime()
        .exec(" netsh interface set interface name=\"roey\" admin=disabled");
          
            BufferedReader stdInput = new BufferedReader(new 
                 InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                 InputStreamReader(p.getErrorStream()));

            // read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
            
            // read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
            
           
       
     
    }
}