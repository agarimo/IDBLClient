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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import main.Query;
import main.Var;
import static main.Var.con;
import model.Documento;
import model.ModeloMultaFull;
import util.Sql;

/**
 * FXML Controller class
 *
 * @author Agarimo
 */
public class DetalleControl implements Initializable {

    //<editor-fold defaultstate="collapsed" desc="FXML VAR">
    @FXML
    private VBox root;
    @FXML
    private Button btCerrar;
    @FXML
    private Button btDocumento;
    @FXML
    private Button btPrint;
    @FXML
    private Label lbNBoe;
    @FXML
    private Label lbFase;
    @FXML
    private Label lbFechaPublicacion;
    @FXML
    private Label lbFechaVencimiento;
    @FXML
    private Label lbOrganismo;
    @FXML
    private Label lbCif;
    @FXML
    private Label lbMatricula;
    @FXML
    private Label lbLocalidad;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbCodigo;
    @FXML
    private Label lbExpediente;
    @FXML
    private Label lbCuantia;
    @FXML
    private Label lbPuntos;
    @FXML
    private Label lbFecha;
    @FXML
    private Label lbArticulo;
    @FXML
    private Label lbLinea;
    @FXML
    private Label lbInfoDoc;
    @FXML
    private ProgressIndicator pgProgreso;
//</editor-fold>
    private ModeloMultaFull multa;
    private Documento document;

    @FXML
    void cerrarVista(ActionEvent event) {
        Nav.mainController.botonDetalle();
    }

    public void getDocument(String id) throws SQLException, FileNotFoundException, IOException {
        document = new Documento(id);
        Sql bd = new Sql(con);

        String sql = "SELECT id,codigo,data FROM " + Var.dbName + ".documento WHERE id=" + document.getId() + ";";
        PreparedStatement stmt = bd.getCon().prepareStatement(sql);
        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            document.setCodigo(resultSet.getString(2));
            document.setFile(new File(Var.runtimeData, document.getId() + ".pdf"));

            try (FileOutputStream fos = new FileOutputStream(document.getFile())) {
                byte[] buffer = new byte[1];
                InputStream is = resultSet.getBinaryStream(3);
                while (is.read(buffer) > 0) {
                    fos.write(buffer);
                }
                fos.close();
            }
        }

        bd.close();
        document.setIsReady(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pgProgreso.setVisible(false);
    }

    @FXML
    void printMulta(ActionEvent event) {
        PrintInforme pt = new PrintInforme(multa.getId());
        pt.print();
    }

    public void setMulta(int id) {
        btDocumento.setDisable(true);
        multa = Query.getModeloMultaFull(id);
        showMulta();
        File doc = new File(Var.runtimeData, multa.getnBoe() + ".pdf");

        if (multa.isDocumento()) {

            Thread a = new Thread(() -> {

                try {
                    if (!doc.exists()) {
                        getDocument(multa.getnBoe());
                    } else {
                        document.setCodigo("YA GENERADO");
                        document.setFile(new File(Var.runtimeData, multa.getnBoe() + ".pdf"));
                        document.setIsReady(true);
                    }

                } catch (Exception ex) {
                    Platform.runLater(() -> {
                        lbInfoDoc.setText("Error en documento.");
                        lbInfoDoc.setTextFill(Color.RED);
                        pgProgreso.setVisible(false);

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("ERROR DESCARGANDO EL DOCUMENTO");
                        alert.setContentText(ex.getMessage());

                        alert.showAndWait();
                    });
                }

                if (document.isReady()) {
                    Platform.runLater(() -> {
                        this.setDescargado();
                    });
                }

            });
            a.start();
        }
    }

    public void setDescargado() {
        pgProgreso.setVisible(false);
        btDocumento.setDisable(false);
        lbInfoDoc.setVisible(true);
        lbInfoDoc.setText("Documento disponible.");
        lbInfoDoc.setTextFill(Color.GREEN);
    }

    private void showMulta() {
        lbNBoe.setText(multa.getnBoe());
        lbFase.setText(multa.getFase());
        lbFechaPublicacion.setText(multa.getFechaPublicacion());
        lbFechaVencimiento.setText(multa.getFechaVencimiento());
        lbOrganismo.setText(multa.getOrganismo());

        lbCif.setText(multa.getCif());
        lbMatricula.setText(multa.getMatricula());
        lbLocalidad.setText(multa.getLocalidad());
        lbNombre.setText(multa.getNombre());

        lbCodigo.setText(multa.getCodigo());
        lbExpediente.setText(multa.getExpediente());
        lbCuantia.setText(multa.getCuantia());
        lbPuntos.setText(multa.getPuntos());
        lbFecha.setText(multa.getFechaMulta());
        lbArticulo.setText(multa.getArticulo());
        lbLinea.setText(multa.getLinea());

        if (multa.isDocumento()) {
            pgProgreso.setVisible(true);
            btDocumento.setDisable(true);
            lbInfoDoc.setVisible(true);
            lbInfoDoc.setText("Descargando Documento.");
            lbInfoDoc.setTextFill(Color.ORANGE);
        } else {
            pgProgreso.setVisible(false);
            btDocumento.setDisable(true);
            lbInfoDoc.setVisible(true);
            lbInfoDoc.setText("Documento no disponible.");
            lbInfoDoc.setTextFill(Color.RED);
        }
    }

    @FXML
    void verDocumento(ActionEvent event) {
        Var.hostServices.showDocument(document.getFile().getAbsolutePath());
    }

}
