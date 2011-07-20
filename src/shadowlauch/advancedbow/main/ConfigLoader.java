package shadowlauch.advancedbow.main;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//import java.util.List;

import org.bukkit.util.config.Configuration;

public class ConfigLoader{
	    public ConfigLoader(AdvancedBow instance) {
	    }
	    File file=new File("plugins/AdvancedBow/config.yml");
	    public void configCheck(){
	        new File("plugins/AdvancedBow/").mkdir();
	        if(!file.exists()){
	            try {
	                file.createNewFile();
	                addDefaults();

	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        } else {

	            loadkeys();
	        }
	    }
/*	    private void write(String root, Object x){
	        Configuration config = load();
	        config.setProperty(root, x);
	        config.save();
	    }
	    private Boolean readBoolean(String root){
	        Configuration config = load();
	        return config.getBoolean(root, true);
	    }
	    private List<String> readStringList(String root){
	        Configuration config = load();
	        return config.getKeys(root);
	    }*/
	    private int readInt(String root){
	        Configuration config = load();
	        return config.getInt(root, 0);
	    }
	    private Double readDouble(String root){
	        Configuration config = load();
	        return config.getDouble(root, 0);
	    }
	    private String readString(String root){
	        Configuration config = load();
	        return config.getString(root);
	    }
	    private boolean readBoolean(String root){
	        Configuration config = load();
	        return config.getBoolean(root, true);
	    }
	    private Configuration load(){

	        try {
	            Configuration config = new Configuration(file);
	            config.load();
	            return config;

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    private void addDefaults(){
	        copyFile("config.yml", "plugins/AdvancedBow/config.yml");
	        AdvancedBow.log.info("[AdvancedBow] Generating Default Config File...");
	        loadkeys();
	    }
	    private void loadkeys(){
	    	ad=readDouble("arrow-damage");
	    	fae=readBoolean("arrow-fire-enabled");
	    	cdm=readInt("arrow-cooldown");
	    	temp_fe=readString("templates.fire.enabled");
	    	temp_fane=readString("templates.errors.firearrownotenabled");
	    	temp_fd=readString("templates.fire.disabled");
	    	temp_cd=readString("templates.cooldown");
	    	temp_nlb=readString("templates.errors.nolavabucket");
	    	temp_nep=readString("templates.errors.nopermissions");
	    	ft=readInt("fire-ticks");
	    }
	    void copy( InputStream in, OutputStream out ) throws IOException
	    {
	      byte[] buffer = new byte[ 0xFFFF ];

	      for ( int len; (len = in.read(buffer)) != -1; )
	        out.write( buffer, 0, len );
	      
	    }
	    void copyFile( String src, String dest )
	    {
	      InputStream  fis = null;
	      FileOutputStream fos = null;

	      try
	      {
	        fis = this.getClass().getResourceAsStream("/default/" + src);
	        fos = new FileOutputStream(dest);

	        copy( fis, fos );
	      }
	      catch ( IOException e ) {
	        e.printStackTrace();
	      }
	      finally {
	        if ( fis != null )
	          try { fis.close(); } catch ( IOException e ) { }
	        if ( fos != null )
	          try { fos.close(); } catch ( IOException e ) { e.printStackTrace(); }
	      }
	    }

		protected String temp_fe;
		protected String temp_fd;
		protected String temp_cd;
		protected String temp_nlb;
		protected String temp_nep;
		protected String temp_fane;
		protected boolean fae;
		protected int cdm;
		protected double ad;
		protected int ft;
		
}
