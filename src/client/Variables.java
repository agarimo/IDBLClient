package client;

import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexion;

/**
 *
 * @author Agarimo
 */
public class Variables {

    public static Conexion con;
    public static boolean modoAdmin=false;
    public static String passwordAdmin="admin01";
    public static String[] tipoBusqueda = {"-", "nif", "matricula", "expediente"};

    public static void iniciaVariables() {
        driver();
        con = new Conexion();
        setConexion();
//        ficheros();
//        cargaXML();
    }

    private static void driver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Variables.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void setConexion(){
        con.setDireccion("192.168.6.20");
        con.setUsuario("client");
        con.setPass("user01");
        con.setPuerto("3306");
        modoAdmin=false;
    }
}
