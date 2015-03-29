package entidades;

import client.Variables;
import java.util.Date;
import util.Dates;
import util.Varios;

/**
 *
 * @author Agarimo
 */
public class VistaMulta {

    int id;
    String nombre;
    String cif;
    String matricula;
    Date publicacion;
    Date vencimiento;
    boolean plazo;

    public VistaMulta() {

    }

    public VistaMulta(int id, String nombre, String cif, String matricula, Date fP, Date fV) {
        this.id = id;
        this.nombre = nombre;
        this.cif = cif;
        this.matricula = matricula;
        this.publicacion = fP;
        this.vencimiento = fV;
        this.plazo = setIsPlazo();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Date publicacion) {
        this.publicacion = publicacion;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public boolean isPlazo() {
        return plazo;
    }

    public void setPlazo(boolean plazo) {
        this.plazo = plazo;
    }

    private boolean setIsPlazo() {
        Date fecha = Dates.curdate();

        return !vencimiento.before(fecha);
    }

    public static String SQLBuscar(String aux, int typ, int opt) {
        String[] opciones = {"-", " AND fechaVencimiento >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR) order by fechaVencimiento desc;", " order by fechaVencimiento desc;"};
    
        return "SELECT * FROM historico.multas_all WHERE " + Variables.tipoBusqueda[typ] + "="+Varios.entrecomillar(aux)+" "+opciones[opt];
    }

    public static String SQLBuscarA(String aux, int typ, int avg) {
        String[] avanzado = {"-",
            "=" + Varios.entrecomillar(aux),
            " like " + Varios.entrecomillar(aux + "%") + " order by "+Variables.tipoBusqueda[typ]+" limit 100",
            " like " + Varios.entrecomillar("%" + aux + "%") + " order by "+Variables.tipoBusqueda[typ]+" limit 100",
            " like " + Varios.entrecomillar("%" + aux) + " order by "+Variables.tipoBusqueda[typ]+" limit 100"};
        String[] tipo={"","historico.sancionado","historico.vehiculo","historico.sancion"};
        
        return "SELECT * FROM "+tipo[typ]+" WHERE "+Variables.tipoBusqueda[typ]+""+avanzado[avg];
    }
}
