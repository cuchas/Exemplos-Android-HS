package br.com.cucha.myself2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.BatteryManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ItemSelecionadoActivity extends AppCompatActivity {

    public static final String TEXTO = "TEXTO";
    private String texto;
    private TextView textView;
    private boolean semRede;
    private int level;
    private Repositorio repositorio;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
                level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                Toast.makeText(context, "battery changed " + level , Toast.LENGTH_SHORT).show();
            }

            if(intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                semRede = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);

                if(semRede) {
                    Toast.makeText(context, "Sem Rede", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Com Rede", Toast.LENGTH_SHORT).show();
                }
            }

            if(intent.getAction().equals(Repositorio.ACTION_SALVAR_CONCLUIDO)) {
                Toast.makeText(context, "O item foi salvo", Toast.LENGTH_SHORT).show();
            }
        }
    };
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_selecionado);

        repositorio = new Repositorio();
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        Intent intent = getIntent();
        texto = intent.getStringExtra(TEXTO);

        SharedPreferences preferences = getSharedPreferences("Respostas", MODE_PRIVATE);

        if(texto == null) {
            texto = preferences.getString("TEXTO", "");
        }

        textView = findViewById(R.id.textViewItemSelecionado);
        textView.setText(texto);

        findViewById(R.id.buttonFinalizar).setOnClickListener(v -> salvar());
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        localBroadcastManager.registerReceiver(receiver, new IntentFilter(Repositorio.ACTION_SALVAR_CONCLUIDO));
    }

    @Override
    protected void onPause() {
        unregisterReceiver(receiver);
        localBroadcastManager.unregisterReceiver(receiver);
        super.onPause();
    }

    private void salvar() {

        if(!semRede && level >= 10) {
            repositorio.salvar(texto, getApplicationContext());
            return;
        }

        Toast.makeText(this, "Ative a internet e ligue na tomada", Toast.LENGTH_SHORT).show();
    }
}
