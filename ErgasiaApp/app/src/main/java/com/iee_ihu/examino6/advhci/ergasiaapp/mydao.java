package com.iee_ihu.examino6.advhci.ergasiaapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface mydao {
    @Insert
    public void addAthlima(Athlima athlima);
    @Update
    public void updateAthlima(Athlima athlima);
    @Delete
    public void deleteAthlima(Athlima athlima);

    @Insert
    public void addAthlitis(Athlitis athlitis);
    @Update
    public void updateAthlitis(Athlitis athlitis);
    @Delete
    public void deleteAthlitis(Athlitis athlitis);

    @Insert
    public void addOmada(Omada omada);
    @Update
    public void updateOmada(Omada omada);
    @Delete
    public void deleteOmada(Omada omada);

    @Query("select a.idAthlimatos from Athlima a where a.eidosAthlimatos = 'Ατομικό' ")
    public List<Integer> idAtomikouAthlimatosQuery();
    @Query("select a.idAthlimatos from Athlima a where a.eidosAthlimatos = 'Ομαδικό' ")
    public List<Integer> idOmadikouAthlimatosQuery();

    @Query("select a.idAthlimatos from Athlima a")
    public List<Integer> idAthlimatosQuery();

    @Query("select a.idAthlimatos,a.eidosAthlimatos,a.onomaAthlimatos,a.fylloAthlimatos,o.onomaOmadas,o.poliOmadas,o.xoraOmadas,o.etosOmadas,o.onomaGipedou from athlima a inner join omada o on (a.idAthlimatos = o.idAthlimatosF2)")
    public List<Query1Type> query1();

    @Query("select a.idAthlimatos,a.eidosAthlimatos,a.onomaAthlimatos,a.fylloAthlimatos,a2.eponimoAthliti,a2.onomaAthliti,a2.poliAthliti,a2.xoraAthliti,a2.etosAthliti from Athlima a inner join Athlitis a2 on (a.idAthlimatos = a2.idAthlimatosF)")
    public List<Query1Type2> query1type2();

    @Query("select a.eidosAthlimatos from athlima a")
    public List<String> listEidos();

    @Query("select a.eponimoAthliti, a.onomaAthliti from athlitis a inner join athlima a2 on (a.idAthlimatosF = a2.idAthlimatos) where a.etosAthliti < 1996 and a2.fylloAthlimatos = 'Γυναικείο'")
    public List<Query2Type> query2();

    @Query("select o.onomaOmadas from omada o where o.xoraOmadas = 'Ελλάδα' and o.etosOmadas < 1930")
    public List<String> query3();
}
