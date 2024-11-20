package com.example.vendasredessociais;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ItensFragment extends Fragment {

    View view;
    private TextView tvItens;
    private EditText etCodigoPedidoItem;
    private EditText etCodigoItem;
    private Spinner spProdutoItem;
    private EditText etQuantidadeItem;
    private Button btnBuscarItem;
    private Button btnListarItem;
    private Button btnExcluirItem;
    private Button btnModificarItem;
    private Button btnInserirItem;
    private TextView tvListaItens;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        tvItens = view.findViewById(R.id.tvItens);
        etCodigoPedidoItem = view.findViewById(R.id.etCodigoPedidoItem);
        etCodigoItem = view.findViewById(R.id.etCodigoItem);
        spProdutoItem = view.findViewById(R.id.spProdutoItem);
        etQuantidadeItem = view.findViewById(R.id.etQuantidadeItem);
        btnBuscarItem = view.findViewById(R.id.btnBuscarItem);
        btnListarItem = view.findViewById(R.id.btnListarItem);
        btnExcluirItem = view.findViewById(R.id.btnExcluirItem);
        btnModificarItem = view.findViewById(R.id.btnModificarItem);
        btnInserirItem = view.findViewById(R.id.btnInserirItem);
        tvListaItens = view.findViewById(R.id.tvListaItens);

        return inflater.inflate(R.layout.fragment_itens, container, false);
    }
}