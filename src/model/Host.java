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
package model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Var;
import sql.Sql;
import tools.Util;

/**
 *
 * @author Agarimo
 */
public class Host {

    private int id;
    private String ip;
    private String name;
    private String os;
    private String user;

    public Host() {
        this.id = 0;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
            name = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            ip = "exception";
            name = "exception";
        }
        user = System.getProperty("user.name");
        os = System.getProperty("os.name");
        registerHost();
    }

    public int getId() {
        return id;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getIp() {
        return ip;
    }

    public String getName() {
        return name;
    }

    public String getOs() {
        return os;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return ip + " " + name + "/" + user + " on " + os;
    }

    private void registerHost() {
        int aux = 0;

        try {
            Sql bd = new Sql(Var.con);
            aux = bd.buscar(SQLBuscar());
            
            if (aux == -1) {
                bd.ejecutar(SQLCrear());
                aux = bd.ultimoRegistro();
            }

            bd.close();
        } catch (SQLException ex) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (aux > 0) {
            this.id = aux;
        }else{
            setDefault();
        }
    }
    
    private void setDefault(){
        this.id=1;
        this.ip="default";
        this.name="default";
        this.user="default";
        this.os="default";
    }

    private String SQLBuscar() {
        return "SELECT id FROM " + Var.dbNameStats + ".host "
                + "WHERE host_ip=" + Util.comillas(ip) + " "
                + "AND host_name=" + Util.comillas(name) + " "
                + "AND host_user=" + Util.comillas(user) + ";";
    }

    private String SQLCrear() {
        return "INSERT INTO " + Var.dbNameStats + ".host (host_ip,host_name,host_user,host_os) values("
                + Util.comillas(this.ip) + ","
                + Util.comillas(this.name) + ","
                + Util.comillas(this.user) + ","
                + Util.comillas(this.os)
                + ");";
    }
}
