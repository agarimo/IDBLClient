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

/**
 *
 * @author Agarimo
 */
public class ModeloMultaFull {
    int id;
    
    String nBoe;
    String organismo;
    String fase;
    String fechaPublicacion;
    String fechaVencimiento;
    
    String cif;
    String nombre;
    String localidad;
    
    String matricula;
    
    String codigo;
    String expediente;
    String fechaMulta;
    String articulo;
    String cuantia;
    String puntos;
    String linea;
    
    boolean documento;
    
    public ModeloMultaFull(){
        
    }

    public boolean isDocumento() {
        return documento;
    }

    public void setDocumento(boolean documento) {
        this.documento = documento;
    }

    public String getArticulo() {
        return articulo;
    }

    public String getCif() {
        return cif;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCuantia() {
        return cuantia;
    }

    public String getExpediente() {
        return expediente;
    }

    public String getFase() {
        return fase;
    }

    public String getFechaMulta() {
        return fechaMulta;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public int getId() {
        return id;
    }

    public String getLinea() {
        return linea;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getOrganismo() {
        return organismo;
    }

    public String getPuntos() {
        return puntos;
    }

    public String getnBoe() {
        return nBoe;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setCuantia(String cuantia) {
        this.cuantia = cuantia;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public void setFechaMulta(String fechaMulta) {
        this.fechaMulta = fechaMulta;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setOrganismo(String organismo) {
        this.organismo = organismo;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }

    public void setnBoe(String nBoe) {
        this.nBoe = nBoe;
    }
}
