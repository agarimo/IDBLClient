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
import util.Varios;

/**
 *
 * @author Agárimo
 */
public class Sancionado {

    int id;
    String cif;
    String tipoJuridico;
    String nombre;

    public Sancionado(int id) {
        this.id = id;
    }

    public Sancionado(String cif, String tipoJuridico, String nombre) {
        this.cif = cif;
        this.tipoJuridico = tipoJuridico;
        this.nombre = nombre;
    }

    public Sancionado(int id, String cif, String tipoJuridico, String nombre) {
        this.id = id;
        this.cif = cif;
        this.tipoJuridico = tipoJuridico;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String nif) {
        this.cif = nif;
    }

    public String getTipoJuridico() {
        return tipoJuridico;
    }

    public void setTipoJuridico(String tipoJuridico) {
        this.tipoJuridico = tipoJuridico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.cif;
    }

    public String SQLBuscar() {
        return "SELECT id FROM " + Var.dbName + ".sancionado WHERE cif=" + Varios.entrecomillar(this.cif) + ";";
    }

    public static String SQLBuscar(String cif) {
        return "SELECT * FROM " + Var.dbName + ".sancionado WHERE cif=" + Varios.entrecomillar(cif) + ";";
    }

}
