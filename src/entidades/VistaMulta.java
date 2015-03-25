package entidades;

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
    
    
    public VistaMulta(){
        
    }

    public VistaMulta(int id,String nombre, String cif, String matricula, Date fP, Date fV) {
        this.id=id;
        this.nombre = nombre;
        this.cif = cif;
        this.matricula=matricula;
        this.publicacion = fP;
        this.vencimiento = fV;
        this.plazo=setIsPlazo();
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
    
    private boolean setIsPlazo(){
        Date fecha=Dates.curdate();
        
        return !vencimiento.before(fecha);
    }
    
    public static String SQLBuscar(String aux, int typ,int opt,int avg){
        String[] tipo={"-","nif","matricula","expediente"};
        String[] opciones={"-","historico.multas_lastyear","historico.multas_all"};
        String[] avanzado={"-",
            "="+Varios.entrecomillar(aux),
            " like"+Varios.entrecomillar(aux+"%")+" limit 100",
            " like"+Varios.entrecomillar("%"+aux+"%")+" limit 100",
            " like"+Varios.entrecomillar("%"+aux)+" limit 100"};
        
        return "SELECT * FROM historico.multas_all WHERE "+tipo[typ]+""+avanzado[avg]+";";
    }
    
//    public static String SQLBuscarNif(String aux){
//        return "SELECT a.idSancion,a.fase,b.fechaPublicacion,a.fechaVencimiento FROM historico.multa as a left join historico.boletin as b on a.idBoletin=b.idBoletin "
//                + "WHERE a.idSancionado in (select idSancionado from historico.sancionado where nif="+Varios.entrecomillar(aux)+");";
//    }
//    
//    public static String SQLBuscarMatricula(String aux){
//        return "SELECT a.idSancion,a.fase,b.fechaPublicacion,a.fechaVencimiento FROM historico.multa as a left join historico.boletin as b on a.idBoletin=b.idBoletin "
//                + "WHERE a.idMatricula in (select idVehiculo from historico.vehiculo where matricula="+Varios.entrecomillar(aux)+");";
//    }
//    
//    public static String SQLBuscarExpediente(String aux){
//        return "SELECT a.idSancion,a.fase,b.fechaPublicacion,a.fechaVencimiento FROM historico.multa as a left join historico.boletin as b on a.idBoletin=b.idBoletin "
//                + "WHERE a.idSancion in (select idSancion from historico.sancion where expediente="+Varios.entrecomillar(aux)+");";
//    }
//    
//    public static String SQLBuscarNifA(String aux){
//        return "SELECT nif from historico.sancionado where nif like"+Varios.entrecomillar("%"+aux+"%")+" limit 100;";
//    }
//    
//    public static String SQLBuscarMatriculaA(String aux){
//        return "SELECT matricula from historico.vehiculo where matricula like "+Varios.entrecomillar("%"+aux+"%")+" limit 100;";
//    }
//    
//    public static String SQLBuscarExpedienteA(String aux){
//        return "SELECT expediente from historico.sancion where expediente like "+Varios.entrecomillar("%"+aux+"%")+" limit 100;";
//    }
    
}
