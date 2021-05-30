/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.base;

import engine.system.Lib;
import java.io.File;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import engine.system.SystemLib;
import java.util.List;
import javafx.stage.FileChooser;
import view.JFileChooserView;

/**
 *
 * @author 35191
 */
public class InstanceLib extends Lib{

    @Override
    public boolean install() {
        System.out.println("install BaseLib");
        BaseEngine.getMenu().removeAll();
        
        JMenu jFile = new JMenu("File");
        
        JMenuItem addLib = new JMenuItem("Add Lib");
        addLib.addActionListener( (a -> {
        List<File> files = JFileChooserView.getFiles("Select an lib",null,null,new FileChooser.ExtensionFilter("Lib jar", "*.jar"));

       if(files!=null){ 
            for(File file : files){
             SystemLib.addLib(file).install();
            }
            BaseEngine.reloadView();
       }
        }) );
        
        jFile.add(addLib);
        
        JMenuItem cleanLib = new JMenuItem("RemoveAll Lib");
        cleanLib.addActionListener( (a -> {
            SystemLib.installBaseLib();
        }) );
        
         jFile.add(cleanLib);
        
        BaseEngine.getMenu().add(jFile);
        BaseEngine.getPaneMain().removeAll();
        BaseEngine.getProjects().removeAll();
        BaseEngine.getResource().removeAll(); 
        
        return true;
    }

    @Override
    public boolean uninstall() {
           return true;
    }

    @Override
    public String getUrlVersao() {
        return "C:\\Users\\35191\\Documents\\NetBeansProjects\\BaseLibSpace\\dist\\BaseLibSpace.jar";
    }
    
    
    
    
}
