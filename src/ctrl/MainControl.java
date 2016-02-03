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

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.octicons.OctIcon;
import java.awt.Color;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import main.Main;
import main.Var;
import static main.Var.modoAdmin;
import static main.Var.popup;
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

    @FXML
    private Button btConfig;

    @FXML
    private Button btCerrar;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vistaHolder.setLeft(loadSearch());
        vistaHolder.setBottom(loadDetalle());
        vistaHolder.setTriggerDistance(0);
        Nav.adminMode(modoAdmin);
        
        String color="#4f59ea";
        
//        Text a = GlyphsDude.createIcon(MaterialDesignIcon.POWER, "28");
//        a.setFill(Paint.valueOf(color));
//        btCerrar.setGraphic(a);
        
        GlyphsDude.setIcon(btBuscar, MaterialIcon.DEHAZE,"28");
        GlyphsDude.setIcon(btConfig, OctIcon.GEAR,"28");
        GlyphsDude.setIcon(btCerrar, MaterialDesignIcon.POWER,"28");
        
        
    }

    public Node loadAvanzado() {
        try {
            FXMLLoader loader = new FXMLLoader();

            Node node = (Node) loader.load(getClass().getResourceAsStream(Nav.AVANZADO));
            AvanzadoControl controller = loader.getController();
            Nav.setAvanzadoController(controller);

            return node;
        } catch (IOException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Node loadDetalle() {
        try {
            FXMLLoader loader = new FXMLLoader();

            Node node = (Node) loader.load(getClass().getResourceAsStream(Nav.DETALLE));
            DetalleControl controller = loader.getController();
            Nav.setDetalleController(controller);

            return node;
        } catch (IOException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Node loadMulta() {
        try {
            FXMLLoader loader = new FXMLLoader();

            Node node = (Node) loader.load(getClass().getResourceAsStream(Nav.MULTA));
            MultaControl controller = loader.getController();
            Nav.setMultaController(controller);

            return node;
        } catch (IOException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Node loadSearch() {
        try {
            FXMLLoader loader = new FXMLLoader();

            Node node = (Node) loader.load(getClass().getResourceAsStream(Nav.SEARCH));
            SearchControl controller = loader.getController();
            Nav.setSearchController(controller);

            return node;
        } catch (IOException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private void startPopup() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Nav.CONFIG));
            Pane nodo = (Pane) fxmlLoader.load();
            popup = new Stage();
            popup.initOwner(Var.stage);
            popup.setResizable(false);
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.initStyle(StageStyle.UTILITY);
            popup.setTitle("Configuración");
            popup.setScene(new Scene(nodo));
            popup.show();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setContent(Node node) {
        vistaHolder.setContent(node);
    }

    @FXML
    void botonBuscar(ActionEvent event) {
        if (vistaHolder.getPinnedSide() != null) {
            vistaHolder.setPinnedSide(null);
            GlyphsDude.setIcon(btBuscar, MaterialIcon.DEHAZE,"28");
        } else {
            vistaHolder.setPinnedSide(Side.LEFT);
            GlyphsDude.setIcon(btBuscar, MaterialDesignIcon.BACKBURGER,"28");
        }
    }

    @FXML
    void botonConfig(ActionEvent event) {
        startPopup();
    }

    @FXML
    void botonCerrar(ActionEvent event) {
        Var.stage.fireEvent(new WindowEvent(Var.stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    void botonDetalle() {
        if (vistaHolder.getPinnedSide() != null) {
            vistaHolder.setPinnedSide(null);
        } else {
            vistaHolder.setPinnedSide(Side.BOTTOM);
        }
    }
}
