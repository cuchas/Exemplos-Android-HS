package br.com.cucha.myself2;

import android.app.Application;

/**
 * Created by eduardocucharro on 04/02/18.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        DataLoaderIntentService.startService(this);
    }
}
