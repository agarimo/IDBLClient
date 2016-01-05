package enty;

import java.util.Date;

/**
 *
 * @author Agárimo
 */
public class Boletin {

    private int id;
    private String nBoe;
    private int origen;
    private String origenS;
    private Date fechaPublicacion;

    public Boletin(int id) {
        this.id = id;
    }

    public Boletin(String nBoe, int origen, Date fechaPublicacion) {
        this.nBoe = nBoe;
        this.origen = origen;
        this.fechaPublicacion = fechaPublicacion;
    }
    
    public Boletin(int id,String nBoe, String origen, Date fecha){
        this.id=id;
        this.nBoe=nBoe;
        this.origenS=origen;
        this.fechaPublicacion=fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getnBoe() {
        return nBoe;
    }

    public void setnBoe(String nBoe) {
        this.nBoe = nBoe;
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getOrigenS() {
        return origenS;
    }

    public void setOrigenS(String origenS) {
        this.origenS = origenS;
    }

    public String buscarBoletin() {
        String query;
        query = "SELECT idBoletin FROM idbl.boletin WHERE nBoe=" + util.Varios.entrecomillar(nBoe);
        return query;
    }

    public String buscarBoletinCount() {
        String query;
        query = "SELECT count(*) FROM idbl.boletin WHERE nBoe=" + util.Varios.entrecomillar(nBoe);
        return query;
    }

    @Override
    public String toString() {
        return "Boletin{" + "id=" + id + ", nBoe=" + nBoe + ", origen=" + origen + '}';
    }
}
