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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import main.Var;
import model.Documento;
import model.ModeloAvanzado;
import model.ModeloMulta;
import model.ModeloMultaFull;
import model.Modo;
import model.Op;
import model.OpType;
import model.Tipo;
import util.Sql;
import util.Varios;

/**
 *
 * @author Agarimo
 */
public class Query {

    public static Sql bd;

    //<editor-fold defaultstate="collapsed" desc="QUERY BÚSQUEDA AVANZADA">
    public static String searchQueryAvanced(int id, Tipo tipo) {
        return "SELECT * FROM " + Var.dbName + ".vista_multa WHERE id IN (" + searchCommonQueryAvanced(id, tipo) + ") order by fecha_vencimiento DESC;";
    }

    private static String searchCommonQueryAvanced(int id, Tipo tipo) {
        return "SELECT id FROM " + Var.dbName + ".multa_full WHERE " + tipo.getColumnMulta() + "=" + id;
    }

    public static String searchSpecificQueryAvanced(String busqueda, Tipo tipo, Modo modo) {
        return "SELECT " + tipo.getItemsAvanzado() + " FROM " + tipo.getTable() + " WHERE " + tipo.getColumn() + modo.getQuery(busqueda, tipo);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="QUERY BUSQUEDA NORMAL">
    public static String searchQuery(String busqueda, Tipo tipo, Modo modo) {
        return "SELECT * FROM " + Var.dbName + ".vista_multa WHERE id IN (" + searchCommonQuery(busqueda, tipo, modo) + ") order by fecha_vencimiento DESC;";
    }

    private static String searchCommonQuery(String busqueda, Tipo tipo, Modo modo) {
        String query;

        if (Var.modoAdmin) {
            query = "SELECT id FROM " + Var.dbName + ".multa_full WHERE " + tipo.getColumnMulta() + "=(" + searchSpecificQuery(busqueda, tipo, modo) + ")";
        } else {
            query = "SELECT id FROM " + Var.dbName + ".multa_limited WHERE " + tipo.getColumnMulta() + "=(" + searchSpecificQuery(busqueda, tipo, modo) + ")";
        }

        return query;
    }

    private static String searchSpecificQuery(String busqueda, Tipo tipo, Modo modo) {
        return "SELECT id FROM " + tipo.getTable() + " WHERE " + tipo.getColumn() + modo.getQuery(busqueda, tipo);
    }
    //</editor-fold>

    public static Documento getDocumento(String id) {
        ResultSet rs;
        Documento aux = null;

        try {
            bd = new Sql(Var.con);
            rs = bd.ejecutarQueryRs("SELECT * FROM " + Var.dbName + ".documento WHERE id=" + id);

            if (rs.next()) {
                aux = new Documento();
                aux.setId(rs.getInt("id"));
                aux.setFecha(rs.getString("fecha"));
                aux.setCodigo(rs.getString("codigo"));
            }

            bd.close();

        } catch (SQLException ex) {
            error(ex);
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aux;
    }

    public static ModeloMultaFull getModeloMultaFull(int id) {
        ResultSet rs;
        ModeloMultaFull aux = null;

        try {
            bd = new Sql(Var.con);
            rs = bd.ejecutarQueryRs("SELECT * FROM " + Var.dbName + ".vista_multa_full WHERE id=" + id);

            if (rs.next()) {
                aux = new ModeloMultaFull();
                aux.setId(rs.getInt("id"));
                aux.setnBoe(rs.getString("n_boe"));
                aux.setFase(rs.getString("fase"));
                aux.setOrganismo(rs.getString("organismo"));
                aux.setFechaPublicacion(rs.getString("fecha_publicacion"));
                aux.setFechaVencimiento(rs.getString("fecha_vencimiento"));
                aux.setCif(rs.getString("cif"));
                aux.setNombre(rs.getString("nombre"));
                aux.setLocalidad(rs.getString("localidad"));
                aux.setMatricula(rs.getString("matricula"));
                aux.setCodigo(rs.getString("codigo"));
                aux.setExpediente(rs.getString("expediente"));
                aux.setFechaMulta(rs.getString("fecha_multa"));
                aux.setArticulo(rs.getString("articulo"));
                aux.setCuantia(rs.getString("cuantia"));
                aux.setPuntos(rs.getString("puntos"));
                aux.setLinea(rs.getString("linea"));
            }

            rs = bd.ejecutarQueryRs("SELECT id FROM " + Var.dbName + ".documento WHERE id=" + Varios.entrecomillar(aux.getnBoe()));

            if (rs.next()) {
                aux.setDocumento(true);
            } else {
                aux.setDocumento(false);
            }

            rs.close();
            bd.close();

        } catch (SQLException ex) {
            error(ex);
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }

        return aux;
    }
    
     public static List<Documento> listaDocumento(String query) {
        List<Documento> list = new ArrayList();
        ResultSet rs;
        Documento aux;

        try {
            bd = new Sql(Var.con);
            rs = bd.ejecutarQueryRs(query);

            while (rs.next()) {
                aux = new Documento();
                aux.setId(rs.getInt("id"));
                aux.setFecha(rs.getString("fecha"));
                aux.setCodigo(rs.getString("codigo"));
                
                list.add(aux);
            }

            rs.close();
            bd.close();

        } catch (SQLException ex) {
            error(ex);
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public static List<ModeloMulta> listaModeloMulta(String query) {
        List<ModeloMulta> list = new ArrayList();
        ResultSet rs;
        ModeloMulta aux;

        try {
            bd = new Sql(Var.con);
            rs = bd.ejecutarQueryRs(query);

            while (rs.next()) {
                aux = new ModeloMulta();
                aux.id = rs.getInt("id");
                aux.organismo.set(rs.getString("organismo"));
                aux.cif.set(rs.getString("cif"));
                aux.matricula.set(rs.getString("matricula"));
                aux.expediente.set(rs.getString("expediente"));
                aux.fase.set(rs.getString("fase"));
                aux.fecha_publicacion.set(rs.getString("fecha_publicacion"));
                aux.fecha_vencimiento.set(rs.getString("fecha_vencimiento"));
                aux.nombre.set(rs.getString("nombre"));
                aux.puntos.set(rs.getString("puntos"));
                aux.cuantia.set(rs.getString("cuantia"));
                aux.linea.set(rs.getString("linea"));
                list.add(aux);
            }

            rs.close();
            bd.close();

        } catch (SQLException ex) {
            error(ex);
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public static List<ModeloAvanzado> listaModeloAvanzado(String query) {
        List<ModeloAvanzado> list = new ArrayList();
        ResultSet rs;
        ModeloAvanzado aux;

        try {
            bd = new Sql(Var.con);
            rs = bd.ejecutarQueryRs(query);

            while (rs.next()) {
                aux = new ModeloAvanzado();
                aux.setId(rs.getInt("id"));
                aux.setCodigo(rs.getString("codigo"));
                aux.setAddData(rs.getString("addData"));

                list.add(aux);
            }

            rs.close();
            bd.close();

        } catch (SQLException ex) {
            error(ex);
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    private static void error(SQLException ex) {
        Op op = new Op();
        op.setOpType(OpType.EXCEPTION);
        op.setOpDetail("Query.error - "+ex.getLocalizedMessage());
        op.run();
        
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR DE CONEXIÓN");
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
        });
    }
}
