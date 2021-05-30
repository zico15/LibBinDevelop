/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.dragged;

import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;

/**
 *
 * @author 35191
 */
public  class ExportTransferHandler extends TransferHandler {
        
        private final JList<String> source;
        
        public ExportTransferHandler(JList<String> source){ this.source=source;}
        
        @Override
        public int getSourceActions(JComponent c){
            return TransferHandler.COPY_OR_MOVE;
        }
     
        @Override
        public Transferable createTransferable(JComponent c) {
            return new StringSelection(source.getSelectedValue());
        }
    }
    
    
