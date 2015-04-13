package model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Agarimo
 */
public class ModeloMulta {
    
    public int id;
    public SimpleStringProperty nombre = new SimpleStringProperty();
    public SimpleStringProperty cif = new SimpleStringProperty();
    public SimpleStringProperty matricula = new SimpleStringProperty();
    public SimpleStringProperty expediente = new SimpleStringProperty();
    public SimpleStringProperty fase = new SimpleStringProperty();
    public SimpleStringProperty fecha = new SimpleStringProperty();
    public SimpleStringProperty fechaV = new SimpleStringProperty();

    public int getId(){
        return id;
    }
    
    public String getNombre() {
        return nombre.get();
    }

    public String getCif() {
        return cif.get();
    }

    public String getMatricula() {
        return matricula.get();
    }

    public String getFecha() {
        return fecha.get();
    }

    public String getFechaV() {
        return fechaV.get();
    }

    public String getExpediente() {
        return expediente.get();
    }

    public String getFase() {
        return fase.get();
    }
    
    
}
