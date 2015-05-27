package model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Agarimo
 */
public class ModeloSancionado {
    
    public int id;
    public SimpleStringProperty nif = new SimpleStringProperty();
    public SimpleStringProperty nombre = new SimpleStringProperty();
    

    public int getId(){
        return id;
    }
    
    public String getNif() {
        return nif.get();
    }

    public String getNombre() {
        return nombre.get();
    }
    
    @Override
    public String toString(){
        return getNif();
    }
}
