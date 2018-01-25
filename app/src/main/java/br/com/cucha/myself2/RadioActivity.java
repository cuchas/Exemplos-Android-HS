package br.com.cucha.myself2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RadioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);

        findViewById(R.id.buttonMultiplaEscolha).setOnClickListener(this::iniciaMultiplaEscolha);
    }

    private void iniciaMultiplaEscolha(View view) {
        Intent intent = new Intent(this, MultiplaEscolhaActivity.class);
        startActivity(intent);
    }
}
