package client;

import enty.Boletin;
import enty.MultaS;
import enty.Sancion;
import enty.Sancionado;
import enty.Vehiculo;
import enty.VistaAvanzado;
import enty.VistaMulta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import util.Sql;
import util.Varios;

/**
 *
 * @author Agarimo
 */
public class SqlIDBL {

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
            Logger.getLogger(SqlIDBL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

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
            Logger.getLogger(SqlIDBL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

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
                multa.getBol().setOrigen(rs.getInt("origen"));
                multa.getBol().setFechaPublicacion(rs.getDate("fechaPublicacion"));
            }

            rs = bd.ejecutarQueryRs("select * from idbl.origen where idOrigen=" + multa.getBol().getOrigen());

            if (rs.next()) {
                multa.getBol().setOrigenS(rs.getString("nombreOrigen"));
            }

            rs = bd.ejecutarQueryRs("select * from idbl.vehiculo where idVehiculo=" + multa.getVeh().getId());

            if (rs.next()) {
                multa.getVeh().setMatricula(rs.getString("matricula"));
            }

            rs = bd.ejecutarQueryRs("select * from idbl.sancionado where idSancionado=" + multa.getSan().getId());

            if (rs.next()) {
                multa.getSan().setNif(rs.getString("nif"));
                multa.getSan().setTipoJuridico(rs.getString("tipoJuridico"));
            }

            rs = bd.ejecutarQueryRs("select * from idbl.sancion where idSancion=" + Varios.entrecomillar(multa.getSanc().getIdSancion()));

            if (rs.next()) {
                multa.getSanc().setExpediente(rs.getString("expediente"));
                multa.getSanc().setFechaMulta(rs.getDate("fechaMulta"));
                multa.getSanc().setArticulo(rs.getString("articulo"));
                multa.getSanc().setCuantia(rs.getString("cuantia"));
                multa.getSanc().setPuntos(rs.getString("puntos"));
                multa.getSanc().setNombre(rs.getString("nombre"));
                multa.getSanc().setLinea(rs.getString("linea"));
                multa.getSanc().setLink(rs.getString("link"));
            }

            rs.close();
            bd.close();
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR DE CONEXIÓN");
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
            Logger.getLogger(SqlIDBL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return multa;
    }
}
