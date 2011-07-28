package shadowlauch.advancedbow.main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.List;
//import java.util.List;

import org.bukkit.util.config.Configuration;

public class ConfigLoader{
	    public ConfigLoader(AdvancedBow instance) {
	    }
	    File file=new File("plugins/AdvancedBow/config.yml");
	    FileWriter writer;
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
	    private void write(String root, Object x){
	        Configuration config = load();
	        config.setProperty(root, x);
	        config.save();
	    }
	    /*	 private Boolean readBoolean(String root){
	        Configuration config = load();
	        return config.getBoolean(root, true);
	    }*/
	    private List<Integer> readIntList(String root){
	        Configuration config = load();
	        return config.getIntList(root, fari);
	    }
	    private int readInt(String root){
	        Configuration config = load();
	        return config.getInt(root, -1);
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
	    	Configuration config = load();
	    	ad=readDouble("arrow-damage");
	    	cdm=readInt("arrow-cooldown");
	    	temp_cd=readString("templates.cooldown");
	    	temp_nep=readString("templates.errors.nopermissions");
	    	ft=readInt("fire-ticks");
	    	perm=config.getString("permissions","permission");
	    	
	    	fae=readBoolean("firearrows.enabled");
	    	facdm=readInt("firearrows.cooldown");
	    	fari=readIntList("firearrows.required-items");
	    	fadiwd=readBoolean("firearrows.damage-items-with-durability");
	    	fasiwd=readBoolean("firearrows.subtract-items-without-durability");
	    	temp_nlb=readString("templates.errors.notrequiredfirearrowitems");
	    	temp_fane=readString("templates.errors.firearrownotenabled");
	    	temp_fe=readString("templates.fire.enabled");
	    	temp_fd=readString("templates.fire.disabled");
	    	temp_fgd=readString("templates.fire.got-disabled");
	    	
	    	
	    	eae=readBoolean("explosivearrows.enabled");
	    	eacdm=readInt("explosivearrows.cooldown");
	    	eari=readIntList("explosivearrows.required-items");
	    	eaer=readInt("explosivearrows.explosion-radius");
	    	eadiwd=readBoolean("explosivearrows.damage-items-with-durability");
	    	easiwd=readBoolean("explosivearrows.subtract-items-without-durability");
	    	temp_neri=readString("templates.errors.notrequiredexplosivearrowitems");
	    	temp_eane=readString("templates.errors.explosivearrownotenabled");
	    	temp_ed=readString("templates.explosive.disabled");
	    	temp_ee=readString("templates.explosive.enabled");
	    	temp_egd=readString("templates.explosive.got-disabled");
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


		protected String temp_cd;
		protected String temp_nep;
		protected int cdm;
		protected double ad;
		protected int ft;
		protected String perm;
		
		protected boolean fae;
		protected int facdm;
		protected boolean fadiwd;
		protected boolean fasiwd;
		protected String temp_fe;
		protected String temp_fgd;
		protected String temp_fd;
		protected String temp_nlb;
		protected List<Integer> fari;
		protected String temp_fane;

		protected boolean eae;
		protected int eacdm;
		protected int eaer;
		protected boolean eadiwd;
		protected boolean easiwd;
		protected String temp_ee;
		protected String temp_egd;
		protected String temp_ed;
		protected String temp_neri;
		protected List<Integer> eari;
		protected String temp_eane;
		
		
		
		
}
