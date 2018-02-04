package br.com.cucha.myself2;

import android.app.IntentService;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.cucha.myself2.data.HSRDB;
import br.com.cucha.myself2.data.Opcao;

/**
 * Created by eduardocucharro on 04/02/18.
 */

public class DataLoaderIntentService extends IntentService {

    public DataLoaderIntentService() {
        super(DataLoaderIntentService.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Notification notification = NotificationHelper.criaNotificacao(this);

        startForeground(NotificationHelper.NOTIFICACAO_BACKGROUND_JOB, notification);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        HSRDB hsrdb = HSRDB.getInstance(getApplicationContext());

        for (int i = 0; i < 2; i++) {

            Opcao opcao = new Opcao();
            opcao.setId(i);
            opcao.setName("Item numero " + i);

            hsrdb.opcaoDAO().insert(opcao);
        }
    }

    public static void startService(Context context) {
        Intent intent = new Intent(context, DataLoaderIntentService.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent);
        }

        context.startService(intent);
    }
}
