/*
 * The MIT License
 *
 * Copyright 2016 Agarimo.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ctrl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.ModeloAvanzado;

/**
 * FXML Controller class
 *
 * @author Agarimo
 */
public class AvanzadoControl implements Initializable {
    
    //<editor-fold defaultstate="collapsed" desc="FXML VAR">
    @FXML
    private TableView tabla;
    
    @FXML
    private TableColumn codigoCL;
    
    @FXML
    private TableColumn addDataCL;
    
    @FXML
    private Button btAceptar;
    
    @FXML
    private Button btCancelar;
    
    @FXML
    private ProgressIndicator pgProgreso;
    
    @FXML
    private Label lbInfoProgreso;
//</editor-fold>

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeTable();
        // TODO
    } 
    
    private void initializeTable(){
        
    }
    
    public void tableLoad(List<ModeloAvanzado> list){
        
    }
    
    @FXML
    void btAceptar(ActionEvent event){
        
    }
    
    @FXML
    void btCancelar(ActionEvent event){
        
    }
    
}