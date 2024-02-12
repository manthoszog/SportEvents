package com.iee_ihu.examino6.advhci.ergasiaapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Athlima.class,Athlitis.class,Omada.class}, version = 1)
public abstract class ergasiaDatabase extends RoomDatabase {
    public abstract mydao mydaotemp();
}
