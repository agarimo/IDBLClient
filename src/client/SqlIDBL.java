package client;

import entidades.Boletin;
import entidades.MultaS;
import entidades.Sancion;
import entidades.Sancionado;
import entidades.Vehiculo;
import entidades.VistaMulta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Sql;
import util.Varios;

/**
 *
 * @author Agarimo
 */
public class SqlIDBL {

    public static List<VistaMulta> listaProducto(String query) {
        List<VistaMulta> list = new ArrayList();
        Sql bd;
        ResultSet rs;
        VistaMulta aux;

        try {
            bd = new Sql(Variables.con);
            rs = bd.ejecutarQueryRs(query);

            while (rs.next()) {
                aux = new VistaMulta(rs.getString("idSancion"), rs.getString("fase"), rs.getDate("fechaPublicacion"), rs.getDate("fechaVencimiento"));
                list.add(aux);
            }

            rs.close();
            bd.close();

        } catch (SQLException ex) {
            Logger.getLogger(SqlIDBL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    
    public static List<String> listaProductoA(String query,String column) {
        List<String> list = new ArrayList();
        Sql bd;
        ResultSet rs;
        String aux;

        try {
            bd = new Sql(Variables.con);
            rs = bd.ejecutarQueryRs(query);

            while (rs.next()) {
                aux = rs.getString(column);
                list.add(aux);
            }

            rs.close();
            bd.close();

        } catch (SQLException ex) {
            Logger.getLogger(SqlIDBL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public static MultaS cargaMultaS(String aux) {
        Sql bd;
        ResultSet rs;
        MultaS multa = null;

        try {
            bd = new Sql(Variables.con);
            rs = bd.ejecutarQueryRs("SELECT * FROM historico.multa WHERE idSancion=" + Varios.entrecomillar(aux));

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

            rs = bd.ejecutarQueryRs("SELECT * FROM historico.boletin where idBoletin=" + multa.getBol().getId());

            if (rs.next()) {
                multa.getBol().setnBoe(rs.getString("nBoe"));
                multa.getBol().setOrigen(rs.getInt("origen"));
                multa.getBol().setFechaPublicacion(rs.getDate("fechaPublicacion"));
            }

            rs = bd.ejecutarQueryRs("select * from historico.origen where idOrigen=" + multa.getBol().getOrigen());

            if (rs.next()) {
                multa.getBol().setOrigenS(rs.getString("nombreOrigen"));
            }

            rs = bd.ejecutarQueryRs("select * from historico.vehiculo where idVehiculo=" + multa.getVeh().getId());

            if (rs.next()) {
                multa.getVeh().setMatricula(rs.getString("matricula"));
            }

            rs = bd.ejecutarQueryRs("select * from historico.sancionado where idSancionado=" + multa.getSan().getId());

            if (rs.next()) {
                multa.getSan().setNif(rs.getString("nif"));
                multa.getSan().setTipoJuridico(rs.getString("tipoJuridico"));
            }

            rs = bd.ejecutarQueryRs("select * from historico.sancion where idSancion=" + Varios.entrecomillar(multa.getSanc().getIdSancion()));

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
            Logger.getLogger(SqlIDBL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return multa;
    }

}
