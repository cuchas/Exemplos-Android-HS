package br.com.cucha.myself2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

/**
 * Created by eduardocucharro on 04/02/18.
 */

public class NotificationHelper {

    public static final int NOTIFICACAO_BACKGROUND_JOB = 1;
    public static final String CANAL_BACKGROUND_JOB_ID = "BACKGROUND_JOBS";

    public static Notification criaNotificacao(Context context) {

        criaCanal(context);

        Notification notification = new NotificationCompat.Builder(context, CANAL_BACKGROUND_JOB_ID)
                .setContentTitle(context.getString(R.string.sincronizando))
                .setProgress(0, 100, true)
                .setSmallIcon(R.drawable.ic_stat_notificacao)
                .build();

        return notification;
    }

    private static void criaCanal(Context context) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel =
                    new NotificationChannel(CANAL_BACKGROUND_JOB_ID,
                            context.getString(R.string.background_notification_channel),
                            NotificationManager.IMPORTANCE_LOW);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.createNotificationChannel(notificationChannel);

        }
    }
}
