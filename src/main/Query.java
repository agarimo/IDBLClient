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
package main;

import model.Boletin;
import model.MultaS;
import model.Sancion;
import model.Sancionado;
import model.Vehiculo;
import model.VistaAvanzado;
import model.VistaMulta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import model.ModeloMulta;
import util.Sql;
import util.Varios;

/**
 *
 * @author Agarimo
 */
public class Query {
    
    public static List<ModeloMulta> listaModeloMulta(String query) {
        List<ModeloMulta> list = new ArrayList();
        Sql bd;
        ResultSet rs;
        ModeloMulta aux;

        try {
            bd = new Sql(Var.con);
            rs = bd.ejecutarQueryRs(query);

            while (rs.next()) {
                aux= new ModeloMulta();
                aux.id=rs.getInt("id");
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR DE CONEXIÓN");
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    @Deprecated
    public static List<VistaMulta> listaMultas(String query) {
        List<VistaMulta> list = new ArrayList();
        Sql bd;
        ResultSet rs;
        VistaMulta aux;

        try {
            bd = new Sql(Var.con);
            rs = bd.ejecutarQueryRs(query);

            while (rs.next()) {
                aux = new VistaMulta(rs.getInt("idMulta"), rs.getString("organismo"), rs.getString("nif"), rs.getString("matricula"), rs.getString("expediente"), rs.getString("fase"), rs.getDate("fechaPublicacion"), rs.getDate("fechaVencimiento"));
                list.add(aux);
            }

            rs.close();
            bd.close();

        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR DE CONEXIÓN");
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    @Deprecated
    public static List<VistaAvanzado> listaMultasA(String query, String column, int typ) {
        List<VistaAvanzado> list = new ArrayList();
        Sql bd;
        ResultSet rs;
        VistaAvanzado aux = null;

        try {
            bd = new Sql(Var.con);
            rs = bd.ejecutarQueryRs(query);
            System.out.println(query);

            while (rs.next()) {
                switch (typ) {
                    case 1:
                        aux = new VistaAvanzado(rs.getInt("idSancionado"), rs.getString("nif"), rs.getString("nombre"));
                        break;

                    case 2:
                        aux = new VistaAvanzado(rs.getInt("idVehiculo"), rs.getString("matricula"), rs.getString("matricula"));
                        break;

                    case 3:
                        aux = new VistaAvanzado(rs.getInt("idSancion"), rs.getString("expediente"), rs.getString("nombre"));
                        break;
                }

                list.add(aux);
            }

            rs.close();
            bd.close();

        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR DE CONEXIÓN");
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    @Deprecated
    public static MultaS cargaMultaS(int aux) {
        Sql bd;
        ResultSet rs;
        MultaS multa = null;

        try {
            bd = new Sql(Var.con);
            rs = bd.ejecutarQueryRs("SELECT * FROM idbl.multa WHERE idMulta=" + aux);

            if (rs.next()) {
                multa = new MultaS();
                multa.setId(rs.getInt("idMulta"));
                multa.setBol(new Boletin(rs.getInt("idBoletin")));
                multa.setVeh(new Vehiculo(rs.getInt("idMatricula")));
                multa.setSan(new Sancionado(rs.getInt("idSancionado")));
                multa.setSanc(new Sancion(rs.getString("idSancion")));
                multa.setFase(rs.getString("fase"));
                multa.setPlazo(rs.getInt("plazo"));
                multa.setFechaEntrada(rs.getDate("fechaEntrada"));
                multa.setFechaVencimiento(rs.getDate("fechaVencimiento"));
            }

            rs = bd.ejecutarQueryRs("SELECT * FROM idbl.boletin where idBoletin=" + multa.getBol().getId());

            if (rs.next()) {
                multa.getBol().setnBoe(rs.getString("nBoe"));
                multa.getBol().setIdOrigen(rs.getInt("origen"));
                multa.getBol().setFechaPublicacion(rs.getDate("fechaPublicacion"));
            }

            rs = bd.ejecutarQueryRs("select * from idbl.origen where idOrigen=" + multa.getBol().getIdOrigen());

            if (rs.next()) {
                multa.getBol().setSOrigen(rs.getString("nombreOrigen"));
            }

            rs = bd.ejecutarQueryRs("select * from idbl.vehiculo where idVehiculo=" + multa.getVeh().getId());

            if (rs.next()) {
                multa.getVeh().setMatricula(rs.getString("matricula"));
            }

            rs = bd.ejecutarQueryRs("select * from idbl.sancionado where idSancionado=" + multa.getSan().getId());

            if (rs.next()) {
                multa.getSan().setCif(rs.getString("nif"));
                multa.getSan().setTipoJuridico(rs.getString("tipoJuridico"));
            }

            rs = bd.ejecutarQueryRs("select * from idbl.sancion where idSancion=" + Varios.entrecomillar(multa.getSanc().getId()));

            if (rs.next()) {
                multa.getSanc().setExpediente(rs.getString("expediente"));
                multa.getSanc().setFechaMulta(rs.getDate("fechaMulta"));
                multa.getSanc().setArticulo(rs.getString("articulo"));
                multa.getSanc().setCuantia(rs.getString("cuantia"));
                multa.getSanc().setPuntos(rs.getString("puntos"));
                multa.getSanc().setNombre(rs.getString("nombre"));
                multa.getSanc().setLinea(rs.getString("linea"));
            }

            rs.close();
            bd.close();
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR DE CONEXIÓN");
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
        return multa;
    }
}
