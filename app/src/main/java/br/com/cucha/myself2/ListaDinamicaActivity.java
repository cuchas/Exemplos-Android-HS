package br.com.cucha.myself2;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;

public class ListaDinamicaActivity extends AppCompatActivity implements OpcaoAdapter.OpcaoListener {

    private static final String LISTA_OPCAO = "LISTA_OPCAO";
    ArrayList<Opcao> opcaoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_dinamica);


        if (savedInstanceState != null) {
            opcaoList = (ArrayList<Opcao>) savedInstanceState.getSerializable(LISTA_OPCAO);
        } else {
            carregaOpcoes();
        }

        OpcaoAdapter adapter = new OpcaoAdapter(opcaoList, this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.notifyDataSetChanged();

        findViewById(R.id.button).setOnClickListener(v -> {
            Opcao novaOpcao = new Opcao();
            novaOpcao.setName("Novo item número");
            adapter.add(novaOpcao);

            iniciaLigacao();
        });
    }

    private void iniciaLigacao() {

        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        if(PackageManager.PERMISSION_GRANTED == permission) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            Uri uri = Uri.parse("tel:11976396169");
            intent.setData(uri);

            startActivity(intent);
            return;

        }

        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {

            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.libere_a_permissao))
                    .setMessage(getString(R.string.permissao_muito_importante))
                    .setPositiveButton(getString(R.string.sim), (dialogInterface, i) -> {
                        pedePermissao();
                        dialogInterface.dismiss();
                    })
                    .setNegativeButton(getString(R.string.fechar), (d, i) -> {
                        d.dismiss();
                    })
                    .create();

            dialog.show();

            return;
        }

        pedePermissao();
    }

    private void pedePermissao() {
        String[] permissaoList = new String[] { Manifest.permission.CALL_PHONE };

        ActivityCompat.requestPermissions(this, permissaoList, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(permissions[0] == Manifest.permission.CALL_PHONE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            iniciaLigacao();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(LISTA_OPCAO, opcaoList);
        super.onSaveInstanceState(outState);
    }

    private void carregaOpcoes() {
        opcaoList = new ArrayList<>();

        for (int i = 0; i < 2; i++) {

            Opcao opcao = new Opcao();
            opcao.setId(i);
            opcao.setName("Item numero " + i);

            opcaoList.add(opcao);
        }
    }

    @Override
    public void onOpcaoSelecionado(Opcao opcao) {
        Intent intent = new Intent(this, ItemSelecionadoActivity.class);
        intent.putExtra(ItemSelecionadoActivity.TEXTO, opcao.getName());
        startActivity(intent);
    }
}
