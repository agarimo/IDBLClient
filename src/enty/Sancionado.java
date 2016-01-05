package enty;

import client.Var;
import util.Varios;


/**
 *
 * @author Agárimo
 */
public class Sancionado {

    int id;
    String nif;
    String tipoJuridico;
    String nombre;
    
    public Sancionado(int id){
        this.id=id;
    }

    public Sancionado(String nif, String tipoJuridico,String nombre) {
        this.nif = nif;
        this.tipoJuridico = tipoJuridico;
        this.nombre=nombre;
    }

    public Sancionado(int id, String nif, String tipoJuridico,String nombre) {
        this.id = id;
        this.nif = nif;
        this.tipoJuridico = tipoJuridico;
        this.nombre=nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
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
    
    public String SQLBuscar(){
        String query="SELECT idSancionado FROM idbl.sancionado WHERE nif="+util.Varios.entrecomillar(nif)+";";
        
        return query;
    }

    @Override
    public String toString() {
        return "Sancionado{" + "id=" + id + ", nif=" + nif + ", tipoJuridico=" + tipoJuridico + '}';
    }
}
