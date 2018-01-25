package br.com.cucha.myself2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListaUnicaEscolhaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_unica_escolha);

        ListView listView = findViewById(R.id.listView);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(ListaUnicaEscolhaActivity.this, RadioActivity.class);
            startActivity(intent);
        });
    }
}
