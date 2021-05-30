/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import java.util.List;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;


/**
 *
 * @author 35191
 */
public class JFileChooserView {
    
    
    public static List<File> getFiles(String title,String directory,Window window,ExtensionFilter... filter){
        FileChooser  fileChooser = new FileChooser ();//(directory != null ? new File(directory) : FileSystemView.getFileSystemView().getHomeDirectory());
       
        fileChooser.setTitle(title);   
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));                 
            fileChooser.getExtensionFilters().addAll(filter);
 

        List<File> returnValue = fileChooser.showOpenMultipleDialog(window);
        if (returnValue != null) {
            
          
            return  returnValue;            
        }
        
        return null;
    }
    
}
