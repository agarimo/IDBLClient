package model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Agarimo
 */
public class ModeloMulta {
    
    public int id;
    public SimpleStringProperty organismo = new SimpleStringProperty();
    public SimpleStringProperty cif = new SimpleStringProperty();
    public SimpleStringProperty matricula = new SimpleStringProperty();
    public SimpleStringProperty expediente = new SimpleStringProperty();
    public SimpleStringProperty fase = new SimpleStringProperty();
    public SimpleStringProperty fecha_publicacion = new SimpleStringProperty();
    public SimpleStringProperty fecha_vencimiento = new SimpleStringProperty();
    public SimpleStringProperty nombre = new SimpleStringProperty();
    public SimpleStringProperty puntos = new SimpleStringProperty();
    public SimpleStringProperty cuantia = new SimpleStringProperty();
    public SimpleStringProperty linea = new SimpleStringProperty();

    public int getId(){
        return id;
    }
    
    public String getOrganismo() {
        return organismo.get();
    }

    public String getCif() {
        return cif.get();
    }

    public String getMatricula() {
        return matricula.get();
    }

    public String getFecha_publicacion() {
        return fecha_publicacion.get();
    }

    public String getFecha_vencimiento() {
        return fecha_vencimiento.get();
    }

    public String getExpediente() {
        return expediente.get();
    }

    public String getFase() {
        return fase.get();
    }
    
    public String getNombre(){
        return nombre.get();
    }
    
    public String getPuntos(){
        return puntos.get();
    }
    
    public String getCuantia(){
        return cuantia.get();
    }
    
    public String getLinea(){
        return linea.get();
    }
}
