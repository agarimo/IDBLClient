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

import java.util.Date;
import main.Var;

/**
 *
 * @author Agárimo
 */
public class Boletin {

    private int id;
    private int idOrigen;
    private String nBoe;
    private String SOrigen;
    private Date fechaPublicacion;

    public Boletin() {

    }

    public Boletin(int id) {
        this.id = id;
    }

    public Boletin(int idOrigen, String nBoe, Date fechaPublicacion) {
        this.nBoe = nBoe;
        this.idOrigen = idOrigen;
        this.fechaPublicacion = fechaPublicacion;
    }

    public Boletin(int id, String nBoe, String SOrigen, Date fecha) {
        this.id = id;
        this.nBoe = nBoe;
        this.SOrigen = SOrigen;
        this.fechaPublicacion = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(int idOrigen) {
        this.idOrigen = idOrigen;
    }

    public String getnBoe() {
        return nBoe;
    }

    public void setnBoe(String nBoe) {
        this.nBoe = nBoe;
    }

    public String getSOrigen() {
        return SOrigen;
    }

    public void setSOrigen(String SOrigen) {
        this.SOrigen = SOrigen;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    
    @Override
    public String toString() {
        return this.nBoe;
    }
    
    
    public static String SQLBuscarId(int id) {
        return "SELECT * FROM " + Var.dbName + ".boletin WHERE id="+id;
    }
    
     public String SQLBuscarId() {
        return "SELECT * FROM " + Var.dbName + ".boletin WHERE id=" + this.id;
    }

    public String SQLBuscar() {
        return "SELECT id FROM " + Var.dbName + ".boletin WHERE n_boe=" + util.Varios.entrecomillar(this.nBoe);
    }

//    public String buscarBoletinCount() {
//        return "SELECT count(*) FROM " + Var.dbName + ".boletin WHERE n_boe=" + util.Varios.entrecomillar(nBoe);
//    }

    
}
