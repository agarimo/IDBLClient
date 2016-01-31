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

/**
 *
 * @author Agarimo
 */
public enum Tipo {

    CIF(0),
    MATRICULA(1),
    EXPEDIENTE(2);

    private final int value;

    private Tipo(int aux) {
        this.value = aux;
    }

    public int getValue() {
        return this.value;
    }

    public String getTable() {
        switch (this) {
            case CIF:
                return Var.dbName + "sancionado";
            case MATRICULA:
                return Var.dbName + "vehiculo";
            case EXPEDIENTE:
                return Var.dbName + "sancion";
            default:
                return "";
        }
    }

    public String getColumn() {
        switch (this) {
            case CIF:
                return "cif";
            case MATRICULA:
                return "matricula";
            case EXPEDIENTE:
                return "expediente";
            default:
                return "";
        }
    }
}
