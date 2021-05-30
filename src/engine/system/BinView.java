/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.system;

import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeView;

/**
 *
 * @author cash
 */
public class BinView {
    
   
    public static TabPane TAB_PROJECT;
    public static TabPane TAB_RESOURCE;
    public static TabPane TAB_MAIN;
    public static TabPane TAB_PROPERTIES;
    public static TreeView TREE_PROJECT;
    public static TreeView TREE_RESOURCE;   
    public static ListView LIST_PROPERTIES;
    
    
    public static void initComponents(TabPane TAB_PROJECT,TabPane TAB_RESOURCE,TabPane TAB_MAIN,TabPane TAB_PROPERTIES,TreeView TREE_PROJECT,TreeView TREE_RESOURCE,ListView LIST_PROPERTIES){
    
        BinView.TAB_PROJECT = TAB_PROJECT;
        BinView.TAB_RESOURCE =TAB_RESOURCE;
        BinView.TAB_MAIN= TAB_MAIN;
        BinView.TAB_PROPERTIES=TAB_PROPERTIES;
        BinView.TREE_PROJECT=TREE_PROJECT;
        BinView.TREE_RESOURCE=TREE_RESOURCE;   
        BinView.LIST_PROPERTIES=LIST_PROPERTIES;
        
    }
    
}
