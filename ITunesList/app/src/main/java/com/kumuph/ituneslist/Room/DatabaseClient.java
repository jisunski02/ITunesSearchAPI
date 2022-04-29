package com.kumuph.ituneslist.Room;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    private Context context;
    private static DatabaseClient databaseInstance;

    //our app database object
    private AppDatabase appDatabase;

    private DatabaseClient(Context context) {
        this.context = context;

        //creating the app database with Room database builder
        //alldata is the name of the database
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "alldata").build();

    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (databaseInstance == null) {
            databaseInstance = new DatabaseClient(mCtx);
        }
        return databaseInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
