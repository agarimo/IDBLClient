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

import util.Varios;

/**
 *
 * @author Agarimo
 */
public enum Modo {
    
    NORMAL(0),
    COMIENZA(1),
    CONTIENE(2);
    
    private final int value;
    
    private Modo(int value){
        this.value=value;
    }
    
    public int getValue(){
        return this.value;
    }
    
    public String getQuery(String aux, Tipo tipo){
        switch (this) {
            case NORMAL:
                return "=" + Varios.entrecomillar(aux);
            case COMIENZA:
                return " like " + Varios.entrecomillar(aux + "%") + " order by " + tipo.getColumn() + " limit 50";
            case CONTIENE:
                return " like " + Varios.entrecomillar("%" + aux + "%") + " order by " + tipo.getColumn() + " limit 50";
            default:
                return "";
        }
    }
}
