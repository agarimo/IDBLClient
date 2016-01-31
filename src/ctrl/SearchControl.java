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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.Modo;
import model.Tipo;

/**
 * FXML Controller class
 *
 * @author Agarimo
 */
public class SearchControl implements Initializable {
    
    //<editor-fold defaultstate="collapsed" desc="FXML VAR">
    @FXML
    private VBox panelBusqueda;
    
    @FXML
    private VBox panelTipo;
    
    @FXML
    private VBox panelModo;
    
    @FXML
    private TextField tfBuscar;
    
    @FXML
    private Button btBuscar;
    
    @FXML
    private RadioButton rbCif;
    
    @FXML
    private RadioButton rbMatricula;
    
    @FXML
    private RadioButton rbExpediente;
    
    @FXML
    private RadioButton rbNormal;
    
    @FXML
    private RadioButton rbComienza;
    
    @FXML
    private RadioButton rbContiene;
//</editor-fold>
    
    Tipo tipo;
    Modo modo;
    boolean activo;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        panelModo.setVisible(false);
        tipo = Tipo.CIF;
        modo = Modo.NORMAL;
        activo=false;
    }
    
    @FXML
    void search(ActionEvent event){
        
    }
    
    @FXML
    void rbCif(MouseEvent event){
        this.tipo=Tipo.CIF;
    }
    
    @FXML
    void rbMatricula(MouseEvent event){
        this.tipo=Tipo.MATRICULA;
    }
    
    @FXML
    void rbExpediente(MouseEvent event){
        this.tipo=Tipo.EXPEDIENTE;
    }
    
    @FXML
    void rbNormal(MouseEvent event){
        this.modo=Modo.NORMAL;
    }
    
    @FXML
    void rbComienza(MouseEvent event){
        this.modo=Modo.COMIENZA;
    }
    
    @FXML
    void rbContiene(MouseEvent event){
        this.modo=Modo.CONTIENE;
    }
}
