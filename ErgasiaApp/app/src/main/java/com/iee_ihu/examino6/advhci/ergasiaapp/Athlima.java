package com.iee_ihu.examino6.advhci.ergasiaapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Athlima {
    @PrimaryKey
    private int idAthlimatos;
    private String onomaAthlimatos;
    private String eidosAthlimatos;
    private String fylloAthlimatos;

    public int getIdAthlimatos() {
        return idAthlimatos;
    }

    public void setIdAthlimatos(int idAthlimatos) {
        this.idAthlimatos = idAthlimatos;
    }

    public String getOnomaAthlimatos() {
        return onomaAthlimatos;
    }

    public void setOnomaAthlimatos(String onomaAthlimatos) {
        this.onomaAthlimatos = onomaAthlimatos;
    }

    public String getEidosAthlimatos() {
        return eidosAthlimatos;
    }

    public void setEidosAthlimatos(String eidosAthlimatos) {
        this.eidosAthlimatos = eidosAthlimatos;
    }

    public String getFylloAthlimatos() {
        return fylloAthlimatos;
    }

    public void setFylloAthlimatos(String fylloAthlimatos) {
        this.fylloAthlimatos = fylloAthlimatos;
    }
}
