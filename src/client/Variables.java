package client;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.Conexion;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Agarimo
 */
public class Variables {

    public static Conexion con;
    public static boolean modoAdmin=false;
    public static String[] tipoBusqueda = {"-", "nif", "matricula", "expediente"};
    

    public static void iniciaVariables() {
        driver();
//        ficheros();
        con = new Conexion();
        setConexion();
//        cargaXML();
    }
    
    private static void driver(){
         try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Variables.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void ficheros(){
        File aux;
        aux= new File("data");
        aux.mkdirs();
        aux = new File("dsc");
        aux.mkdirs();
    }
    
    private static void setConexion(){
        con.setDireccion("localhost");
        con.setUsuario("client");
        con.setPass("user01");
        con.setPuerto("3306");
    }

    private static void cargaXML() {
        String aux;
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("config.xml");
        try {
            Document document = (Document) builder.build(xmlFile);
            Element config = document.getRootElement();

            Element conexion = config.getChild("conexion");
            con.setDireccion(conexion.getChildText("db-host"));
            con.setPuerto(conexion.getChildText("db-port"));
            con.setUsuario(conexion.getChildText("db-username"));
            con.setPass(conexion.getChildText("db-password"));
            
            conexion=config.getChild("general");
            aux=conexion.getChildText("modo");
            
            if(aux.equals("admin")){
                modoAdmin=true;
            }

        } catch (IOException | JDOMException io) {
            System.out.println(io.getMessage());
        }
    }
}
