/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author 35191
 */
public class WebPane extends JFXPanel{
    
    public WebView view; 
    public WebEngine engine;
    
    public  WebPane(){  
         // create JavaFX scene
        Platform.runLater(() -> {
            view = new WebView();
            engine = view.getEngine();       
            engine.setJavaScriptEnabled(true);
            setScene(new Scene(view));
        });
        
    
    }
    
      public  WebPane(String url){  
         // create JavaFX scene
        Platform.runLater(() -> {
            view = new WebView();
            engine = view.getEngine();   
            engine.load(url);
            setScene(new Scene(view));
        });
        
    
    }
    
    public void load(String url){
        Platform.runLater(() -> {            
            engine.load(url);     
        });
    }
    
}
