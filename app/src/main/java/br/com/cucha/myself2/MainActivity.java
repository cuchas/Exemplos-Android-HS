package br.com.cucha.myself2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TEXTO = "TEXTO";
    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);

        editText = findViewById(R.id.editText);

        textView = findViewById(R.id.textResposta);

        button.setOnClickListener(view -> mudaText());

        if(savedInstanceState != null) {
            String texto = savedInstanceState.getString(TEXTO);

            editText.setText(texto);
        }

//        Toast.makeText(MainActivity.this, getString(R.string.item_foi_salvo), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LIFECYCLE", "onstart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LIFECYCLE", "onresume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LIFECYCLE", "onpause");
    }

    @Override
    protected void onStop() {
        Log.d("LIFECYCLE", "onstop");
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        String texto = editText.getText().toString();
        outState.putString(TEXTO, texto);

        super.onSaveInstanceState(outState);
    }

    private void mudaText() {

        if(editText.getText() == null || editText.getText().toString().isEmpty()) {
            editText.setError(getString(R.string.campo_obrigatorio));
            return;
        }
        textView.setText(editText.getText());

        Intent intent = new Intent(this, UnicaEscolhaActivity.class);
        startActivity(intent);
    }
}
