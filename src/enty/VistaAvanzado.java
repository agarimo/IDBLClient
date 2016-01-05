package enty;

import client.Var;
import util.Varios;

/**
 *
 * @author Agarimo
 */
public class VistaAvanzado {
    int id;
    String nif;
    String nombre;
    
    
    public VistaAvanzado(){
        
    }
    
    public VistaAvanzado(int id,String nif,String nombre){
        this.id=id;
        this.nif=nif;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
     public static String SQLBuscarA(String aux, int typ, int avg) {
        String[] avanzado = {"-",
            "=" + Varios.entrecomillar(aux),
            " like " + Varios.entrecomillar(aux + "%") + " order by " + Var.tipoBusqueda[typ] + " limit 100",
            " like " + Varios.entrecomillar("%" + aux + "%") + " order by " + Var.tipoBusqueda[typ] + " limit 100",
            " like " + Varios.entrecomillar("%" + aux) + " order by " + Var.tipoBusqueda[typ] + " limit 100"};
        String[] tipo = {"", "idbl.sancionado", "idbl.vehiculo", "idbl.sancion"};

        return "SELECT * FROM " + tipo[typ] + " WHERE " + Var.tipoBusqueda[typ] + "" + avanzado[avg];
    }
}
