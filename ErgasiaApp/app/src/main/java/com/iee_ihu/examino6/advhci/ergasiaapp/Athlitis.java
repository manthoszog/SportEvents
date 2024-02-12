package com.iee_ihu.examino6.advhci.ergasiaapp;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"idAthlimatosF"}, foreignKeys = {@ForeignKey(entity = Athlima.class, parentColumns = "idAthlimatos", childColumns = "idAthlimatosF", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)})
public class Athlitis {


    private String onomaAthliti;
    private String eponimoAthliti;
    private String poliAthliti;
    private String xoraAthliti;
    private int etosAthliti;

    private int idAthlimatosF;



    public String getOnomaAthliti() {
        return onomaAthliti;
    }

    public void setOnomaAthliti(String onomaAthliti) {
        this.onomaAthliti = onomaAthliti;
    }

    public String getEponimoAthliti() {
        return eponimoAthliti;
    }

    public void setEponimoAthliti(String eponimoAthliti) {
        this.eponimoAthliti = eponimoAthliti;
    }

    public String getPoliAthliti() {
        return poliAthliti;
    }

    public void setPoliAthliti(String poliAthliti) {
        this.poliAthliti = poliAthliti;
    }

    public String getXoraAthliti() {
        return xoraAthliti;
    }

    public void setXoraAthliti(String xoraAthliti) {
        this.xoraAthliti = xoraAthliti;
    }

    public int getEtosAthliti() {
        return etosAthliti;
    }

    public void setEtosAthliti(int etosAthliti) {
        this.etosAthliti = etosAthliti;
    }

    public int getIdAthlimatosF() {
        return idAthlimatosF;
    }

    public void setIdAthlimatosF(int idAthlimatosF) {
        this.idAthlimatosF = idAthlimatosF;
    }
}
