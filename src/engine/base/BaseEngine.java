/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.base;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTree;
import view.table.jTabbedPaneView;

/**
 *
 * @author 35191
 */
public class BaseEngine {
    
    private static JFrame frame;
    private static JMenuBar menu;
    private static JTree project;
    private static JTree resource;
    private static jTabbedPaneView paneMain,pane1,pane2,pane3;
    private static HashMap<String,String> icons = new HashMap<>();
    private static  ArrayList<BaseEvent> componentAdapter = new ArrayList<>();
    public static JMenuBar getMenu(){ return menu; }
        
    public static String getIconDefault(String fileName){
     return null;
    }
    
    public static JTree getProjects(){ return project;}
    
    public static JTree getResource(){ return resource;}

    /**
     * @param aMenu the menu to set
     */
    public static void setMenu(JMenuBar aMenu) {
        menu = aMenu;
    }

    /**
     * @param aProject the project to set
     */
    public static void setProject(JTree aProject) {
        project = aProject;
    }

    /**
     * @param aResource the resource to set
     */
    public static void setResource(JTree aResource) {
        resource = aResource;
    }

    /**
     * @return the paneMain
     */
    public static jTabbedPaneView getPaneMain() {
        return paneMain;
    }

    /**
     * @param aPaneMain the paneMain to set
     */
    public static void setPaneMain(jTabbedPaneView aPaneMain) {
        paneMain = aPaneMain;
    }
    
    public static void reloadView(){
    
        if(menu!=null) menu.validate();
        if(paneMain!=null) paneMain.validate();
        if(resource!=null) resource.validate();
        if(project!=null) project.validate();
        if(frame!=null) frame.validate();
    }

    /**
     * @return the icons
     */
    public static HashMap<String,String> getIcons() {
        return icons;
    }

    /**
     * @param aIcons the icons to set
     */
    public static void setIcons(HashMap<String,String> aIcons) {
        icons = aIcons;
    }

    /**
     * @return the pane1
     */
    public static jTabbedPaneView getPane1() {
        return pane1;
    }

    /**
     * @param aPane1 the pane1 to set
     */
    public static void setPane1(jTabbedPaneView aPane1) {
        pane1 = aPane1;
    }

    /**
     * @return the pane2
     */
    public static jTabbedPaneView getPane2() {
        return pane2;
    }

    /**
     * @param aPane2 the pane2 to set
     */
    public static void setPane2(jTabbedPaneView aPane2) {
        pane2 = aPane2;
    }

    /**
     * @return the pane3
     */
    public static jTabbedPaneView getPane3() {
        return pane3;
    }

    /**
     * @param aPane3 the pane3 to set
     */
    public static void setPane3(jTabbedPaneView aPane3) {
        pane3 = aPane3;
    }

    /**
     * @return the frame
     */
    public static JFrame getFrame() {
        return frame;
    }

    /**
     * @param aFrame the frame to set
     */
    public static void setFrame(JFrame aFrame) {
        frame = aFrame;
    }

    /**
     * @return the componentAdapter
     */
    public static ArrayList<BaseEvent> getComponentAdapter() {
        if(componentAdapter == null){componentAdapter = new ArrayList<>();}
        return componentAdapter;
    }
}
