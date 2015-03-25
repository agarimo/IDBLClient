package entidades;


/**
 *
 * @author Agárimo
 */
public class Sancionado {

    int id;
    String nif;
    String tipoJuridico;
    
    public Sancionado(int id){
        this.id=id;
    }

    public Sancionado(String nif, String tipoJuridico) {
        this.nif = nif;
        this.tipoJuridico = tipoJuridico;
    }

    public Sancionado(int id, String nif, String tipoJuridico) {
        this.id = id;
        this.nif = nif;
        this.tipoJuridico = tipoJuridico;
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

    public String crearSancionado() {
        String query = "INSERT into historico.sancionado (nif,nombre,tipoJuridico,localidad) values("
                + util.Varios.entrecomillar(nif) + ","
                + util.Varios.entrecomillar(tipoJuridico)
                + ");";

        return query;
    }
    
    public String buscarSancionado(){
        String query="SELECT idSancionado FROM historico.sancionado WHERE nif="+util.Varios.entrecomillar(nif)+";";
        
        return query;
    }

    @Override
    public String toString() {
        return "Sancionado{" + "id=" + id + ", nif=" + nif + ", tipoJuridico=" + tipoJuridico + '}';
    }
}
