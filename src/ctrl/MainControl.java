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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import org.controlsfx.control.HiddenSidesPane;

/**
 * FXML Controller class
 *
 * @author Agarimo
 */
public class MainControl implements Initializable {

    @FXML
    private HiddenSidesPane vistaHolder;

    @FXML
    private Button btBuscar;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Node content = FXMLLoader.load(Nav.class.getResource(Nav.MULTA));
            
            vistaHolder.setContent(content);

            Node left = FXMLLoader.load(Nav.class.getResource(Nav.SEARCH));
            vistaHolder.setLeft(left);
            
            Node bottom = FXMLLoader.load(Nav.class.getResource(Nav.DETALLE));
            vistaHolder.setBottom(bottom);
            
            vistaHolder.setTriggerDistance(0);
        } catch (IOException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Replaces the vista displayed in the vista holder with a new vista.
     *
     * @param node the vista node to be swapped in.
     * @throws java.io.IOException
     */
    public void setVista(Node node) throws IOException {
        vistaHolder.setContent(node);
    }

    @FXML
    void botonBuscar(ActionEvent event) {
        if (vistaHolder.getPinnedSide() != null) {
            vistaHolder.setPinnedSide(null);
        } else {
            vistaHolder.setPinnedSide(Side.LEFT);
        }
    }
    
    void botonDetalle(){
        if (vistaHolder.getPinnedSide() != null) {
            vistaHolder.setPinnedSide(null);
        } else {
            vistaHolder.setPinnedSide(Side.BOTTOM);
        }
    }

}
