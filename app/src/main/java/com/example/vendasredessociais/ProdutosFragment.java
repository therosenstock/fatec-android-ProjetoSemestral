package com.example.vendasredessociais;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ProdutosFragment extends Fragment {

    View view;
    private TextView tvProdutos;
    private EditText edCodigoProduto;
    private EditText etQuantidadeProduto;
    private EditText edNomeProduto;
    private EditText edPrecoProduto;
    private Button btnBuscarProduto;
    private Button btnInserirProduto;
    private Button btnModificarProduto;
    private Button btnExcluirProduto;
    private Button btnListarProduto;
    private TextView tvListaProdutos;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        tvProdutos = view.findViewById(R.id.tvProdutos);
        edCodigoProduto = view.findViewById(R.id.edCodigoProduto);
        etQuantidadeProduto = view.findViewById(R.id.etQuantidadeProduto);
        edNomeProduto = view.findViewById(R.id.edNomeProduto);
        edPrecoProduto = view.findViewById(R.id.edPrecoProduto);
        btnBuscarProduto = view.findViewById(R.id.btnBuscarProduto);
        btnInserirProduto = view.findViewById(R.id.btnInserirProduto);
        btnModificarProduto = view.findViewById(R.id.btnModificarProduto);
        btnExcluirProduto = view.findViewById(R.id.btnExcluirProduto);
        btnListarProduto = view.findViewById(R.id.btnListarProduto);
        tvListaProdutos = view.findViewById(R.id.tvListaProdutos);

        return inflater.inflate(R.layout.fragment_produtos, container, false);
    }
}