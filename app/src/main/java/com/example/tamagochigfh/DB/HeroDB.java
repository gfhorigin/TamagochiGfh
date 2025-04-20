package com.example.tamagochigfh.DB;

import android.app.Application;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tamagochigfh.mainActivity.Hero;

@Database(entities = {Hero.class}, version = 1)
public abstract class HeroDB extends RoomDatabase {
    private static HeroDB instance = null;
    private static final String DATABASE_NAME = "tamogochi_database.db";

    public static HeroDB getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    application,
                    HeroDB.class,
                    DATABASE_NAME
            ).build();
        }
        return instance;
    }
    public static void closeDatabase() {
        if (instance != null && instance.isOpen()) {
            instance.close();
            instance = null;
        }
    }

    public abstract HeroDao heroDao();
}