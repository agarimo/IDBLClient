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
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import main.Var;
import model.Documento;
import model.ModeloMultaFull;
import model.Op;
import model.OpType;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.Ftp;
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
    private Op op;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GlyphsDude.setIcon(btCerrar, MaterialIcon.ARROW_DROP_DOWN, "32");
        GlyphsDude.setIcon(btDocumento, MaterialIcon.CLOUD_DOWNLOAD, "32");
        GlyphsDude.setIcon(btPrint, MaterialIcon.PRINT, "32");
        pgProgreso.setVisible(false);
    }

    public void multaSet(int id) {
        multa = Query.getModeloMultaFull(id);

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
            document = Query.getDocumento(multa.getnBoe());
            btDocumento.setDisable(false);
            lbInfoDoc.setText("Documento disponible.");
            lbInfoDoc.setTextFill(Color.GREEN);
        } else {
            document = null;
            btDocumento.setDisable(true);
            lbInfoDoc.setText("Documento no disponible.");
            lbInfoDoc.setTextFill(Color.RED);
        }
    }

    @FXML
    void pdfShow(ActionEvent event) {
        Thread a = new Thread(() -> {
            Platform.runLater(() -> {
                lbInfoDoc.setTextFill(Color.ORANGE);
                lbInfoDoc.setText("Descargando fichero");
                pgProgreso.setVisible(true);
            });

            try {
                File file = new File(Var.runtimeData, document.getCodigo() + ".pdf");

                if (!file.exists()) {
                    Ftp ftp = new Ftp(Var.conFtp);
                    ftp.downloadFile(document.getFile(), file);
                    ftp.close();
                }

                Platform.runLater(() -> {
                    lbInfoDoc.setTextFill(Color.ORANGE);
                    lbInfoDoc.setText("Abriendo fichero");
                });

                Var.hostServices.showDocument(file.getAbsolutePath());

                Platform.runLater(() -> {
                    lbInfoDoc.setText("");
                    pgProgreso.setVisible(false);
                });

            } catch (IOException ex) {
                op = new Op();
                op.setOpType(OpType.EXCEPTION);
                op.setOpDetail("DetalleControl.pdfShow - "+ex.getLocalizedMessage());
                op.run();

                Platform.runLater(() -> {
                    lbInfoDoc.setTextFill(Color.RED);
                    lbInfoDoc.setText("ERROR");
                    pgProgreso.setVisible(false);

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Error obteniendo el documento.");
                    alert.setContentText(ex.getLocalizedMessage());
                    alert.showAndWait();

                    lbInfoDoc.setText("");
                });
            }
        });

        if (document != null) {
            a.start();
        }
    }

    @FXML
    void printReport(ActionEvent event) {
        try {
            Sql bd = new Sql(Var.con);
            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream("/resources/ReporteSancion.jasper"));

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("ID_MULTA", multa.getId());

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, bd.getCon());

            JasperExportManager.exportReportToPdfFile(jasperPrint, new File(Var.runtimeData, "report.pdf").getAbsolutePath());
            JasperViewer.viewReport(jasperPrint, false);

            op = new Op();
            op.setOpType(OpType.PRINT);
            op.setOpDetail("Printed " + multa.getCodigo());
            op.run();
        } catch (SQLException | JRException ex) {
            op = new Op();
            op.setOpType(OpType.EXCEPTION);
            op.setOpDetail("DetalleControl.printReport - "+ex.getLocalizedMessage());
            op.run();
            Logger.getLogger(MultaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void xitDetail(ActionEvent event) {
        Nav.mainController.botonDetalle();
    }

}
