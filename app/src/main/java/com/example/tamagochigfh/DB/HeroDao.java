package com.example.tamagochigfh.DB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tamagochigfh.mainActivity.Hero;

@Dao
public interface HeroDao {
    @Insert
    void insert(Hero hero);

    @Update
    void update(Hero hero);

    @Query("SELECT * FROM heroes LIMIT 1")
    Hero getHero();
}