package com.iee_ihu.examino6.advhci.ergasiaapp;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"idAthlimatosF2"}, foreignKeys = {@ForeignKey(entity = Athlima.class, parentColumns = "idAthlimatos", childColumns = "idAthlimatosF2", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)})
public class Omada {


    private String onomaOmadas;
    private String onomaGipedou;
    private String poliOmadas;
    private String xoraOmadas;
    private int etosOmadas;

    private int idAthlimatosF2;


    public String getOnomaOmadas() {
        return onomaOmadas;
    }

    public void setOnomaOmadas(String onomaOmadas) {
        this.onomaOmadas = onomaOmadas;
    }

    public String getOnomaGipedou() {
        return onomaGipedou;
    }

    public void setOnomaGipedou(String onomaGipedou) {
        this.onomaGipedou = onomaGipedou;
    }

    public String getPoliOmadas() {
        return poliOmadas;
    }

    public void setPoliOmadas(String poliOmadas) {
        this.poliOmadas = poliOmadas;
    }

    public String getXoraOmadas() {
        return xoraOmadas;
    }

    public void setXoraOmadas(String xoraOmadas) {
        this.xoraOmadas = xoraOmadas;
    }

    public int getEtosOmadas() {
        return etosOmadas;
    }

    public void setEtosOmadas(int etosOmadas) {
        this.etosOmadas = etosOmadas;
    }

    public int getIdAthlimatosF2() {
        return idAthlimatosF2;
    }

    public void setIdAthlimatosF2(int idAthlimatosF2) {
        this.idAthlimatosF2 = idAthlimatosF2;
    }
}
