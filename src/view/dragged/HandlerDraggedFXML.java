/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.dragged;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.DragEvent;
import static view.table.jTabbedPaneView.dialog;
import static view.table.jTabbedPaneView.dialog2;

/**
 *
 * @author 35191
 */
public class HandlerDraggedFXML  { 
        
        private final Parent component;
        private final ArrayList<Parent> components;
        
 
        public HandlerDraggedFXML(Parent component,ArrayList<Parent> components) {   
            this.component = component;
             this.components=components;      
             
            component.setOnDragDetected(v -> { System.out.println("setOnDragDetected"); });
            component.setOnDragDone((v -> { System.out.println("setOnDragDone"); }));
            component.setOnDragDropped((v -> { System.out.println("setOnDragDropped"); }));
            component.setOnDragEntered((v -> { System.out.println("setOnDragEntered"); }));
            component.setOnDragExited((v -> { System.out.println("setOnDragExited"); }));
            component.setOnDragOver((v -> { System.out.println("setOnDragOver"); }));
        
        }

        
        public void updadeGraphicsSelect(Point point){
                  
                
            Graphics g = dialog2.getGraphics();
            
            dialog2.setCursor(new Cursor(Cursor.HAND_CURSOR));
            g.setColor(Color.red);
            g.fillRect(0, 0, 20, (int)component.getBoundsInLocal().getHeight());
            dialog2.setLocation(point);

   
            dialog.setVisible(false);                
            dialog2.setSize(new Dimension((int)component.getBoundsInLocal().getWidth(),(int)component.getBoundsInLocal().getHeight())); 
            dialog2.setVisible(true);

        }
        
        public void updadeGraphicsDragged(Point point){
//            Graphics g = dialog.getGraphics();       
//            component.paintAll(g);           
//            dialog.setLocation(point);         
//            dialog.setSize(component.getSize()); 
//            dialog2.setVisible(false);
//            dialog.setVisible(true);

        }
      
        private boolean isDraggerPane(MouseEvent e){
            if(components!=null){        
                for(Parent t : components){

                       // rectangle.setRect(t.getX(), t.getY(), t.getWidth(), t.getHeight());
                        if(t.getBoundsInLocal().contains(e.getX(), e.getY(),e.getX()+5, e.getY()+5)){
                             updadeGraphicsSelect(new Point(e.getLocationOnScreen().x-5,e.getLocationOnScreen().y-5));
                             return true;
                        }  
                }
     
           }
        return false;
    }

      
    
}