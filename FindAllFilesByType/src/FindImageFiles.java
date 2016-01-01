import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.io.*;



public class FindImageFiles {
	public static Map<String,Integer> freq; // = new HashMap<String, Integer>();
    // File representing the folder that you select using a FileChooser
   // static final File dir = new File("/Users/davidhartman/test");
    //static final File dir = new File("//psf/Users/dhartman/Pictures/iPhoto Library.photolibrary/");
    static final String search_dir = "//psf/Home/Dropbox/test/";

    // array of supported extensions (use a List if you prefer)
    static final String[] EXTENSIONS = new String[]{
        "gif", "png", "bmp","JPG" // and other formats you need
    };
    // filter to identify images based on their extensions
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };
    public static void recListFiles (String strDir) {
    	 File[] faFiles = new File(strDir).listFiles();
    	 try { 
    	 for(File file: faFiles){
//    	    if(file.getName().matches("^(.*?)")){
//    	      String ext = FilenameUtils.getExtension(file.getAbsolutePath());
//    	      System.out.println(file.getAbsolutePath() + " has extension: " + ext);
//    	      if(freq.containsKey(ext)) {
//    	    	  Integer count = freq.get(ext);
//    	    	  freq.put(ext, count+1);
//    	      } else {
//    	    	  freq.put(ext, 1);
//    	      }
//    	    }
    	    if(file.isDirectory()){
    	      recListFiles(file.getAbsolutePath());
    	    } else {
      	      String ext = FilenameUtils.getExtension(file.getAbsolutePath());
      	      if (file.getName().matches("^(.*jpg)")) {
          	    
      	    	  BufferedImage img = ImageIO.read(file);
          		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
          		//System.out.println("Found an image..." + file.);
                //System.out.println("After Format : " + sdf.format(file.lastModified()));
          		//System.out.println("date: " + sdf.format(file.lastModified()));
                if (img.getPropertyNames() != null) {
	          	  for (String prop : img.getPropertyNames()) {
	          	    	  System.out.println("prop: " + prop);
	          	      }
          	      }
      	      }
      	      //System.out.println(file.getAbsolutePath() + " has extension: " + ext);
      	      if(freq.containsKey(ext)) {
      	    	  Integer count = freq.get(ext);
      	    	  freq.put(ext, count+1);
      	      } else {
      	    	  freq.put(ext, 1);
      	      }
    	    }
    	  }
    	 } catch (Exception e) {
    		 e.printStackTrace();
    	 }
    }
    public static void main(String[] args) {
       freq = new HashMap<String,Integer>();
       //String test_dir ="//psf/Home/Pictures/iPhoto Library.photolibrary/";
       //String test_dir = "//psf/Home/test";
       //new File("//psf/Home/test");
       
       recListFiles(search_dir);
       for (String extList : freq.keySet()) {
    	   System.out.println("ext " + extList + " has: " + freq.get(extList));
       }
      // for (File test_file : test_dir.li)
//        if (dir.isDirectory()) { // make sure it's a directory
//            for (final File f : dir.listFiles(IMAGE_FILTER)) {
//                BufferedImage img = null;
//
//                try {
//                    img = ImageIO.read(f);
//
//                    // you probably want something more involved here
//                    // to display in your UI
//                    System.out.println("image: " + f.getName());
//                    //System.out.println(" width : " + img.get);
//                    System.out.println(" height: " + img.getHeight());
//                    System.out.println(" size  : " + f.length());
//                } catch (final IOException e) {
//                    // handle errors here
//                }
//            }
//        }
    }
}