package br.com.cucha.myself2.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

/**
 * Created by eduardocucharro on 04/02/18.
 */

@Database(entities = { Opcao.class, Resposta.class }, version = 1)
@TypeConverters(DateConverter.class)
public abstract class HSRDB extends RoomDatabase {

    public abstract OpcaoDAO opcaoDAO();
    public abstract RespostaDAO respostaDAO();

    static HSRDB db;

    public static HSRDB getInstance(Context context) {
        if(db == null) {
            db = Room.databaseBuilder(context, HSRDB.class, "HSRdb")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return db;
    }
}
