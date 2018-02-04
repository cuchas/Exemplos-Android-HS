package br.com.cucha.myself2.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by eduardocucharro on 04/02/18.
 */
@Dao
public interface OpcaoDAO {
    @Query("SELECT * FROM opcao")
    List<Opcao> getOpcoesSync();

    @Query("SELECT * FROM opcao")
    LiveData<List<Opcao>> getOpcoes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(Opcao opcao);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insert(Opcao... opcao);

    @Delete
    int delete(Opcao opcao);

    @Query("DELETE FROM opcao WHERE opcao_id = :id")
    int delete(int id);
}
