package com.kumuph.ituneslist.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

//Establishing Room Database connection
@Database(entities = {ITunesListRoom.class}, version = 2)

public abstract class AppDatabase extends RoomDatabase {
    public abstract ITunesListDao iTunesListDao();
}
