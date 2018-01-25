package br.com.cucha.myself2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by eduardocucharro on 19/01/18.
 */

public class Repositorio {

    public static String ACTION_SALVAR_CONCLUIDO = "ACTION_SALVAR_CONCLUIDO";

    public void salvar(String texto, Context context) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> {
            SharedPreferences preferences = context.getSharedPreferences("Respostas", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("TEXTO", texto);
            editor.apply();

            try {
                Thread.sleep(2000);
                Intent intent = new Intent(ACTION_SALVAR_CONCLUIDO);

                LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
                localBroadcastManager.sendBroadcast(intent);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
