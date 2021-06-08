/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.system;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 *
 * @author 35191
 */
public class SystemLib {
    
    public static final String NAME_BASE_LIB = "LibBinDevelop";
    
    
    private static final ArrayList<Lib> libs = new ArrayList<>();
    public static final File  PATH_LIB = new File(new File(System.getProperty("user.dir")),"lib");
    
    
    public static void load(){
        
      
        File files = PATH_LIB;
        if(files.exists()){  
            
            for(File f : files.listFiles()){
               installLib(f);           
            }
          
        }
    }
    
    
    public static void installBaseLib(){
      File baseLib = new File(PATH_LIB, NAME_BASE_LIB+".jar");
      
            if(baseLib.exists()){
                    installLib(baseLib);
            }
    
    }
    
      public static void installLibs(List<File> files){
          
          files.stream().filter((file) -> (!file.isDirectory() &&  !file.getName().contains("BaseLibSpace") && file.getName().contains(".jar"))).forEachOrdered((file) -> {
              installLib(file);
        });
      }
    
    public static void installLib(File file){
        try {
            Lib lib = addLib(file);
            if(lib != null){lib.install(); }
          }
          catch(Exception e) {
            System.out.println("Err Lib: "+e.getMessage());
          }
    
    }
    
    public static void reload() {
        libs.clear();
 
     
     FileFilter jarFilefilter  = (File file1) -> {
        
         return file1.getName().endsWith(".jar");
     }; 
     
    
     
//      for(File file : new File(Resource.getDiretorio()+"/Plugins").listFiles(jarFilefilter)){
//          System.out.println("File: "+file);
//    
//             Plugin plugin = loadPlugin(file);
//             if(plugin != null && plugin.install()){ 
//                 plugins.add(plugin);
//             }
//      
//      }
    
    
    }
    
    public static String getInstanceFile(File file){
    
        if(file!= null && file.exists()){          
        try {
            
            System.out.println("file: "+file);
            ZipFile zipFile = new ZipFile(file);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();

		while (entries.hasMoreElements()) {
			ZipEntry zipEntry = (ZipEntry) entries.nextElement();
			String name = zipEntry.getName();
                        //System.out.println(name);
			if (!zipEntry.isDirectory() && name.contains("InstanceLib")){
                                name = name.substring(0, name.lastIndexOf(".class"));
                                name = name.replace('/', '.');				
                                return name;
			}
		}
        } catch (IOException ex) {
             System.out.println("file: "+file);
          //  Logger.getLogger(SystemLib.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        return null;
    }
    
    public static Lib addLib(File file) {
    // try {
       // Getting the jar URL which contains target class
       Lib lib = null;
   
       if(file!=null && file.exists() && file.length()>0){
        URL[] classLoaderUrls = null;
    
        try {
            classLoaderUrls = new URL[]{file.toURI().toURL()};
        } catch (MalformedURLException ex) {
            Logger.getLogger(SystemLib.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(classLoaderUrls!=null) { 
         
        // Create a new URLClassLoader 
        URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);
        //System.out.println("urlClassLoader: "+urlClassLoader != null);
         
        // Crie uma nova instância da classe carregada
        Class<?> pluginClass = null;
        try {
            pluginClass = urlClassLoader.loadClass(getInstanceFile(file));
            //System.out.println("Plugin: "+pluginClass != null); 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SystemLib.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(pluginClass!=null){
        // Create a new instance from the loaded class
        Constructor<?> constructor;
        try {
            constructor = pluginClass.getConstructor();
            //System.out.println("constructor: "+constructor != null); 
            Object libObj = constructor.newInstance();         
            //System.out.println("pluginObj: "+pluginObj != null);
            
           lib = (Lib) libObj;
           if(lib!=null){  
               boolean updadeVersao = false;
               File fileVersao = lib.getUrlVersao() != null ? new File(lib.getUrlVersao()) : null;
               
               if(fileVersao != null && fileVersao.exists() ){                   
                    updadeVersao = getFileDate(file).compareTo(getFileDate(fileVersao)) < 0;                
               }
               
               if(updadeVersao ){
                   System.out.println("Lib Atualizada \n URL: "+lib.getUrlVersao());
                   return lib;
                   
               }else {
               copyFile(file, new File(new File(System.getProperty("user.dir")), "lib"));
               }
           }
         Method method = pluginClass.getDeclaredMethod("instance",Integer.class,String.class);    
        method.invoke("instance", "oi");
//   
//            lib =  (Lib) method.invoke(libObj);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(SystemLib.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    } }
         return lib;
    }
    
    public ArrayList<Lib> getALL(){    
        return libs;
    
    }
    
    public static FileTime getFileDate(File file){
              
		try {
		     BasicFileAttributes attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
		     return attrs.creationTime();		
		} catch (IOException e) {
		 return null;
		}   
    }
    
    public static void copyFile(File source, File destination){
        FileChannel sourceChannel = null;
        FileChannel destinationChannel = null;

   

        if(!destination.exists()) destination.mkdirs();
        destination = new File(destination, source.getName());
        if(!source.equals(destination)){          
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destinationChannel = new FileOutputStream(destination).getChannel();
            sourceChannel.transferTo(0, sourceChannel.size(),      destinationChannel);
        }   catch (FileNotFoundException ex) {
                Logger.getLogger(SystemLib.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SystemLib.class.getName()).log(Level.SEVERE, null, ex);
            } finally {

                try {
                    if (sourceChannel != null && sourceChannel.isOpen()) sourceChannel.close();              
                    if (destinationChannel != null && destinationChannel.isOpen()) destinationChannel.close();           
                    
                   
                     BasicFileAttributes copia = Files.readAttributes(source.toPath(), BasicFileAttributes.class);
                     destination.setLastModified(copia.creationTime().toMillis());
               
                     

            } catch (IOException ex) {
                Logger.getLogger(SystemLib.class.getName()).log(Level.SEVERE, null, ex);
            }

       }}
    }
    
    
}
