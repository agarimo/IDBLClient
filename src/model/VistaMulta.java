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

import main.Var;
import java.util.Date;
import util.Dates;
import util.Varios;

/**
 *
 * @author Agarimo
 */
public class VistaMulta {

    int id;
    String organismo;
    String cif;
    String matricula;
    String expediente;
    String fase;
    Date publicacion;
    Date vencimiento;
    boolean plazo;

    public VistaMulta() {

    }

    public VistaMulta(int id, String organismo, String cif, String matricula, String expediente, String fase, Date fP, Date fV) {
        this.id = id;
        this.organismo = organismo;
        this.cif = cif;
        this.matricula = matricula;
        this.expediente = expediente;
        this.fase = fase;
        this.publicacion = fP;
        this.vencimiento = fV;
        this.plazo = setIsPlazo();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrganismo() {
        return organismo;
    }

    public void setOrganismo(String nombre) {
        this.organismo = nombre;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Date publicacion) {
        this.publicacion = publicacion;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public boolean isPlazo() {
        return plazo;
    }

    public void setPlazo(boolean plazo) {
        this.plazo = plazo;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    private boolean setIsPlazo() {
        Date fecha = Dates.curdate();

        return !vencimiento.before(fecha);
    }

    public static String SQLBuscar(String aux, int typ, int opt) {
        String[] opciones = {"-", " AND fechaVencimiento >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR) order by fechaVencimiento desc;", " order by fechaVencimiento desc;"};
        System.out.println("SELECT * FROM idbl.multas_all WHERE " + Var.tipoBusqueda[typ] + "=" + Varios.entrecomillar(aux) + " " + opciones[opt]);
        return "SELECT * FROM idbl.multas_all WHERE " + Var.tipoBusqueda[typ] + "=" + Varios.entrecomillar(aux) + " " + opciones[opt];
    }

    public static String SQLBuscarA(String aux, int typ, int avg) {
        String[] avanzado = {"-",
            "=" + Varios.entrecomillar(aux),
            " like " + Varios.entrecomillar(aux + "%") + " order by " + Var.tipoBusqueda[typ] + " limit 100",
            " like " + Varios.entrecomillar("%" + aux + "%") + " order by " + Var.tipoBusqueda[typ] + " limit 100",
            " like " + Varios.entrecomillar("%" + aux) + " order by " + Var.tipoBusqueda[typ] + " limit 100"};
        String[] tipo = {"", "idbl.sancionado", "idbl.vehiculo", "idbl.sancion"};

        return "SELECT * FROM " + tipo[typ] + " WHERE " + Var.tipoBusqueda[typ] + "" + avanzado[avg];
    }
}
