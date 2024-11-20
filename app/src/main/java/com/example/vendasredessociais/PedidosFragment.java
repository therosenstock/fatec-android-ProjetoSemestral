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


public class PedidosFragment extends Fragment {

    View view;
    private TextView tvPedidos;
    private EditText etCodigoPedido;
    private EditText etNomeClientePedido;
    private Spinner spStatusPedido;
    private EditText etPrecoFretePedido;
    private Button btnBuscarPedido;
    private Button btnAvancarPedido;
    private Button btnExcluirPedido;
    private Button btnModificarPedidos;
    private Button btnInserirPedido;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        tvPedidos = view.findViewById(R.id.tvPedidos);
        etCodigoPedido = view.findViewById(R.id.etCodigoPedido);
        etNomeClientePedido = view.findViewById(R.id.etNomeClientePedido);
        spStatusPedido = view.findViewById(R.id.spStatusPedido);
        etPrecoFretePedido = view.findViewById(R.id.etPrecoFretePedido);
        btnBuscarPedido = view.findViewById(R.id.btnBuscarPedido);
        btnAvancarPedido = view.findViewById(R.id.btnAvancarPedido);
        btnExcluirPedido = view.findViewById(R.id.btnExcluirPedido);
        btnModificarPedidos = view.findViewById(R.id.btnModificarPedidos);
        btnInserirPedido = view.findViewById(R.id.btnInserirPedido);

        return inflater.inflate(R.layout.fragment_pedidos, container, false);
    }
}