package com.miftakhularzak.moviecatalogue.data.source.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.miftakhularzak.moviecatalogue.data.source.local.entity.MovieEntity;
import com.miftakhularzak.moviecatalogue.data.source.local.entity.TvShowEntity;

@Database(entities = {MovieEntity.class, TvShowEntity.class}, version = 1, exportSchema = false)
public abstract class FavoriteDatabase extends RoomDatabase {
    private static FavoriteDatabase INSTANCE;

    public abstract FavoriteDao favoriteDao();

    private static final Object sLock = new Object();

    public static FavoriteDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        FavoriteDatabase.class, "favorite_database")
                        .build();
            }
        }

        return INSTANCE;
    }
}
