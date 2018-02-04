package br.com.cucha.myself2.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by eduardocucharro on 04/02/18.
 */
@Dao
public interface RespostaDAO {
    @Insert
    Long insert(Resposta resposta);

    @Delete
    int delete(Resposta resposta);

    @Query("DELETE FROM resposta")
    int delete();

    @Query("SELECT * FROM resposta ORDER BY data ASC")
    LiveData<List<Resposta>> getRespostas();

}
