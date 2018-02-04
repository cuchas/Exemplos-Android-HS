package br.com.cucha.myself2.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.Nullable;

import java.util.Date;

/**
 * Created by eduardocucharro on 04/02/18.
 */
@Entity(tableName = "resposta", primaryKeys = { "id_pesquisa", "id_usuario"})
public class Resposta {
    @ColumnInfo(name = "data")
    private Date data;

    @ColumnInfo(name = "id_pesquisa")
    private int idPesquisa;

    @ColumnInfo(name = "id_usuario")
    private int idUsuario;

    @ColumnInfo(name = "resposta")
    private String resposta;

    public Date getData() {
        return data;
    }

    public int getIdPesquisa() {
        return idPesquisa;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setIdPesquisa(int idPesquisa) {
        this.idPesquisa = idPesquisa;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
