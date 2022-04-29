package com.kumuph.ituneslist.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ITunesListDao {

    //This is where you load all the data when network if offline
    @Query("SELECT * FROM ITunesListRoom")
    List<ITunesListRoom> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ITunesListRoom iTunesListRoom);
}
