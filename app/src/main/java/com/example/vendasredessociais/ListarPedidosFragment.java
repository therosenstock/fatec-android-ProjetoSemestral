package com.example.vendasredessociais;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vendasredessociais.controller.PedidoController;
import com.example.vendasredessociais.model.Pedido;
import com.example.vendasredessociais.persistence.PedidoDao;


public class ListarPedidosFragment extends Fragment {
    View view;

    EditText edCodigoListaPedidos;
    Button btnBuscarLista;
    TextView tvListaPedidos;
    PedidoController controller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_listar_pedidos, container, false);

        edCodigoListaPedidos = view.findViewById(R.id.edCodigoListaPedidos);
        btnBuscarLista = view.findViewById(R.id.btnBuscarLista);
        tvListaPedidos = view.findViewById(R.id.tvListaPedidos);

        controller = new PedidoController(new PedidoDao(view.getContext()));

        btnBuscarLista.setOnClickListener(op -> buscarPedido());

        return view;
    }

    public void buscarPedido() {
        try {
            String codigo = edCodigoListaPedidos.getText().toString();
            Pedido pedido = new Pedido();
            pedido.setCodigo(Integer.parseInt(codigo));
            controller.buscar(pedido);

            String pedidos = pedido.toString() +
                    "\n" +
                    tvListaPedidos.getText().toString();
            tvListaPedidos.setText(pedidos);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Erro ao buscar pedido", Toast.LENGTH_LONG).show();
        }
    }
}