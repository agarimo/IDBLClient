package enty;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Agárimo
 */
public final class Sancion {

    String idSancion;
    String expediente;
    Date fechaMulta;
    String articulo;
    String cuantia;
    String puntos;
    String nombre;
    String localidad;
    String linea;
    String link;

    public Sancion(){
        
    }
    
    public Sancion(String id){
        this.idSancion=id;
    }

    public Sancion(String idSancion, String expediente, Date fechaMulta, String articulo, String cuantia, String puntos, String nombre, String localidad, String linea, String link) {
        this.idSancion = idSancion;
        this.expediente = expediente;
        this.fechaMulta = fechaMulta;
        this.articulo = articulo;
        this.cuantia = cuantia;
        this.puntos = puntos;
        this.nombre = nombre;
        this.localidad = localidad;
        this.linea = linea;
        this.link = link;
    }

    public String getIdSancion() {
        return idSancion;
    }

    public void setIdSancion(String idSancion) {
        this.idSancion = idSancion;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public Date getFechaMulta() {
        return fechaMulta;
    }

    public void setFechaMulta(Date fechaMulta) {
        this.fechaMulta = fechaMulta;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getCuantia() {
        return cuantia;
    }

    public void setCuantia(String cuantia) {
        this.cuantia = cuantia;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    

    @Override
    public String toString() {
        return "Sancion{" + "expediente=" + expediente + ", fechaMulta=" + fechaMulta + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.idSancion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sancion other = (Sancion) obj;
        if (!Objects.equals(this.idSancion, other.idSancion)) {
            return false;
        }
        return true;
    }

    public String buscarSancion() {
        String query = "SELECT idSancion FROM idbl.sancion WHERE expediente=" + util.Varios.entrecomillar(this.expediente) + ";";

        return query;
    }
  }