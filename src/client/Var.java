package client;

import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexion;

/**
 *
 * @author Agarimo
 */
public class Var {

    public static Conexion con;
    public static boolean modoAdmin = false;
    public static String passwordAdmin = "admin01";
    public static String[] tipoBusqueda = {"-", "nif", "matricula", "expediente"};
    public static String cadenaCif = "ABCDEFGHJKLMNPQRSUVW";
    public static String cadenaNie = "XYZ";

    public static void iniciaVariables() {
        driver();
        con = new Conexion();
        setConexion();
    }

    private static void driver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Var.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void setConexion() {
        con.setDireccion("localhost");
        con.setUsuario("client");
        con.setPass("user01");
        con.setPuerto("3306");
        modoAdmin = false;
    }
}
