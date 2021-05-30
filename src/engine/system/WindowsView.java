/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.system;


import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author zequi
 */
public class WindowsView {
    
    
    
    public static void setViewController(Stage stage){
    
      
      stage.initStyle(StageStyle.UNDECORATED);
      stage.getScene().addEventFilter(MouseEvent.MOUSE_MOVED, (MouseEvent mouseEvent) -> {
         System.out.println("mouse click detected! " + mouseEvent.getSource());
      });
    
    }
    
    
    
}
