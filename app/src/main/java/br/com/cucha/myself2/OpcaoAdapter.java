package br.com.cucha.myself2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.cucha.myself2.data.Opcao;

/**
 * Created by eduardocucharro on 17/01/18.
 */

public class OpcaoAdapter extends RecyclerView.Adapter<OpcaoAdapter.OpcaoViewHolder> {

    private final OpcaoListener listener;

    public interface OpcaoListener {
        void onOpcaoSelecionado(Opcao opcao);
    }

    private final ArrayList<Opcao> opcaoList;

    public OpcaoAdapter(ArrayList<Opcao> opcaoList, OpcaoListener listener) {
        this.opcaoList = opcaoList;
        this.listener = listener;
    }

    void add(Opcao opcao) {
        opcaoList.add(opcao);
        notifyItemInserted(opcaoList.size() - 1);
    }

    void add(List<Opcao> opcaoList) {
        opcaoList.addAll(opcaoList);
        notifyDataSetChanged();
    }

    @Override
    public OpcaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_opcao_item, parent, false);

        OpcaoViewHolder opcaoViewHolder = new OpcaoViewHolder(view);

        return opcaoViewHolder;
    }

    @Override
    public void onBindViewHolder(OpcaoViewHolder holder, int position) {
        holder.bind(opcaoList.get(position));
    }

    @Override
    public int getItemCount() {
        return opcaoList.size();
    }

    class OpcaoViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public OpcaoViewHolder(View itemView) {

            super(itemView);

            textView = itemView.findViewById(R.id.textViewOpcao);
        }

        void bind(Opcao opcao) {
            textView.setText(opcao.getName());
            textView.setOnClickListener(v -> listener.onOpcaoSelecionado(opcao));
        }
    }
}
