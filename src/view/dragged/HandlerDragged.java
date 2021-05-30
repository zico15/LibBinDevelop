/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.dragged;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.JComponent;
import static view.table.jTabbedPaneView.dialog;
import static view.table.jTabbedPaneView.dialog2;
import static view.table.jTabbedPaneView.tableSelect;

/**
 *
 * @author 35191
 */
public class HandlerDragged extends MouseAdapter implements PropertyChangeListener { 
        
        private final JComponent component;
        private final ArrayList<JComponent> components;
        
 
        public HandlerDragged(JComponent component,ArrayList<JComponent> components) {   
            this.component = component;
             this.components=components;
            component.addMouseListener(this);
            component.addMouseMotionListener(this);
            component.addPropertyChangeListener(this);
        
        }

        
        public void updadeGraphicsSelect(Point point){
                  
                
            Graphics g = dialog2.getGraphics();
            component.paintAll(g);   
            dialog2.setCursor(new Cursor(Cursor.HAND_CURSOR));
            g.setColor(Color.red);
            g.fillRect(0, 0, 20, component.getSize().height);
            dialog2.setLocation(point);

   
            dialog.setVisible(false);                
            dialog2.setSize(component.getSize()); 
            dialog2.setVisible(true);

        }
        
        public void updadeGraphicsDragged(Point point){
            Graphics g = dialog.getGraphics();       
            component.paintAll(g);           
            dialog.setLocation(point);         
            dialog.setSize(component.getSize()); 
            dialog2.setVisible(false);
            dialog.setVisible(true);

        }
      
        private boolean isDraggerPane(MouseEvent e){
            if(components!=null){
        Point point = new  Point(e.getX(), e.getY());
    
      Rectangle rectangle = new  Rectangle( );
      
        for(JComponent t : components){
                rectangle.setRect(t.getX(), t.getY(), t.getWidth(), t.getHeight());
                if(rectangle.contains(point)){
                     updadeGraphicsSelect(new Point(e.getLocationOnScreen().x-5,e.getLocationOnScreen().y-5));
                     return true;
                }  
        }
     
     }
        return false;
    }

        // MouseListener
        @Override 
        public void mousePressed(MouseEvent e) {


           dialog.setLocation(e.getLocationOnScreen().x + 10,e.getLocationOnScreen().y + 5);
           dialog.pack();
           dialog.setVisible(false);
           dialog2.setVisible(false);


        }

        @Override 
        public void mouseDragged(MouseEvent e) {
         if(!isDraggerPane(e))updadeGraphicsDragged(new Point(e.getLocationOnScreen().x,e.getLocationOnScreen().y ));


        }

            @Override
            public void mouseReleased(MouseEvent e) {
                  //System.out.println("mouseReleased");
                    dialog.setVisible(false);
                    dialog2.setVisible(false);
                    System.out.println("tableSelect: "+(tableSelect!=null) );         

                   tableSelect= null;
            }

            @Override
            public void propertyChange(PropertyChangeEvent evt) {

            }
    
}