package shadowlauch.advancedbow.main;


import java.io.*;
import java.net.*;
public class Update {

public static double getVersion() {

  URL u;
  InputStream is = null;
  DataInputStream dis;
  String s;
  String op = "";

  try {
     u = new URL("https://raw.github.com/Shadowlauch/AdvancedBow/master/version.txt");
     is = u.openStream();         // throws an IOException
     dis = new DataInputStream(new BufferedInputStream(is));

     while ((s = dis.readLine()) != null) {
        op=op+s;
     }

  } catch (MalformedURLException mue) {

     System.out.println("Ouch - a MalformedURLException happened.");
     mue.printStackTrace();
     System.exit(1);

  } catch (IOException ioe) {

     System.out.println("Oops- an IOException happened.");
     ioe.printStackTrace();
     System.exit(1);

  } finally {
     try {
        is.close();
     } catch (IOException ioe) {
     }
  }
  double v=Double.valueOf(op);
  return v;
}
}
