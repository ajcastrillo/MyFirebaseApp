package thejeshgn.com.myfirebaseapp.model;

import java.util.List;

public class Servicio  {

    private String MAT;
    private String SERV;
    private String SN;

    private List<Prueba> listaPrueba;

    private String UID;

    public Servicio() {
    }

    @Override
    public String toString() {
        return UID ;
    }

    public String getMAT() {
        return MAT;
    }

    public void setMAT(String MAT) {
        this.MAT = MAT;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getSERV() {
        return SERV;
    }

    public void setSERV(String SERV) {
        this.SERV = SERV;
    }

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }

    public List<Prueba> getListaPrueba() {
        return listaPrueba;
    }

    public void setListaPrueba(List<Prueba> listaPrueba) {
        this.listaPrueba = listaPrueba;
    }
}
