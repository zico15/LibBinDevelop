/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.list;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.plaf.metal.MetalIconFactory;

/**
 *
 * @author 35191
 */
public class ListPane extends JList{
    
    public ListPane(){
     
    setModel(new javax.swing.AbstractListModel<Object>() {
        
    Object[] strings = { "details", "computer", "folder", "computer"};
    
    @Override
    public int getSize() { return strings.length; }
    @Override
    public Object getElementAt(int i) { return strings[i]; }
        });
        
    Map<Object, Icon> icons = new HashMap<>();
    icons.put("details",MetalIconFactory.getFileChooserDetailViewIcon());

    icons.put("folder",MetalIconFactory.getTreeFolderIcon());      
    icons.put("computer",MetalIconFactory.getTreeComputerIcon());
    this.setCellRenderer(new IconListRenderer(icons));
    setDragEnabled(true);
//    setTransferHandler(new ExportTransferHandler(this));
    
           DropTargetListener dropTargetListener = new DropTargetListener() {
               @Override
               public void dragEnter(DropTargetDragEvent dtde) {
                                          System.out.println("dragEnter");
               }

               @Override
               public void dragOver(DropTargetDragEvent dtde) {
                                  System.out.println("dragOver");
               }

               @Override
               public void dropActionChanged(DropTargetDragEvent dtde) {
                                        System.out.println("dropActionChanged");
               }

               @Override
               public void dragExit(DropTargetEvent dte) {
                        System.out.println("dragExit");
               }

               @Override
               public void drop(DropTargetDropEvent dtde) {
                   System.out.println("drop");
               }
           };
           
                  DropTarget  dropTarget = new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE,dropTargetListener, true, null);
            setDropTarget(dropTarget) ;
   
    }
    

    
    
}
