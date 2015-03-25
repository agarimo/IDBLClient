package model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Agarimo
 */
public class ModeloMulta {

    public SimpleStringProperty nombre = new SimpleStringProperty();
    public SimpleStringProperty cif = new SimpleStringProperty();
    public SimpleStringProperty matricula = new SimpleStringProperty();
    public SimpleStringProperty fecha = new SimpleStringProperty();
    public SimpleStringProperty fechaV = new SimpleStringProperty();

    public SimpleStringProperty getNombre() {
        return nombre;
    }

    public SimpleStringProperty getCif() {
        return cif;
    }

    public SimpleStringProperty getMatricula() {
        return matricula;
    }

    public SimpleStringProperty getFecha() {
        return fecha;
    }

    public SimpleStringProperty getFechaV() {
        return fechaV;
    }
}
