/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.dragged;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import javax.swing.TransferHandler;

/**
 *
 * @author 35191
 */
public abstract class ImportTransferHandler extends TransferHandler {

        @Override
        public boolean canImport(TransferHandler.TransferSupport supp) {
            return supp.isDataFlavorSupported(DataFlavor.stringFlavor);
        }
     
        @Override
        public boolean importData(TransferHandler.TransferSupport supp) {
            valide(supp.getTransferable());
            return true;
        }
        
        public abstract void valide(Transferable t);
    }