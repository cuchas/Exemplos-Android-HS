package br.com.cucha.myself2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UnicaEscolhaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unica_escolha);
    }

    public void iniciaListaUnicaEscolha(View view) {
        Intent intent = new Intent(this, ListaUnicaEscolhaActivity.class);
        startActivity(intent);
    }
}
