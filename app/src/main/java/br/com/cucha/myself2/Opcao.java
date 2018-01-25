package br.com.cucha.myself2;

import java.io.Serializable;

/**
 * Created by eduardocucharro on 17/01/18.
 */

public class Opcao implements Serializable {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
