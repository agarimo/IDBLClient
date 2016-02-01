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

import com.sun.javafx.application.HostServicesDelegate;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
import util.Conexion;
import util.Files;

/**
 *
 * @author Agarimo
 */
public class Var {
    
    public static Stage stage;
    public static Stage popup;
    public static HostServicesDelegate hostServices;

    public static Conexion con;
    public static String dbName = "idbl";
    public static int limiteQuery=50;

    public static boolean modoAdmin = false;
    public static String passwordAdmin = "admin01";

    public static File runtimeData;
    
    public static boolean isRunning;

//    public static String[] tipoBusqueda = {"-", "nif", "matricula", "expediente"};
//    public static String cadenaCif = "ABCDEFGHJKLMNPQRSUVW";
//    public static String cadenaNie = "XYZ";

    public static void iniciaVariables() {
        driver();
        setConexion();
        setFiles();
    }

    private static void driver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Var.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void setConexion() {
        con = new Conexion();
        con.setDireccion("192.168.1.40");
        con.setDireccion("oficina.redcedeco.net");
        con.setUsuario("client");
        con.setPass("user01");
        con.setPuerto("3306");
        modoAdmin = false;
    }

    private static void setFiles() {
        runtimeData = new File("tempData");
        runtimeData.mkdirs();
    }

    public static void clearFiles() {
        Files.borraDirectorio(runtimeData);
    }
}
