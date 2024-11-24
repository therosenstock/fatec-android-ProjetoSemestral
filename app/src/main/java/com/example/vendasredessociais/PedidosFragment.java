package com.example.vendasredessociais;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vendasredessociais.controller.PedidoController;
import com.example.vendasredessociais.model.Pedido;
import com.example.vendasredessociais.persistence.PedidoDao;

import java.util.Arrays;
import java.util.List;


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
    private PedidoController controller;
    private List<String> itensStatus = Arrays.asList("Aberto", "Em transporte", "Concluído");;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pedidos, container, false);

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

        ArrayAdapter ad = new ArrayAdapter(view.getContext(),
                android.R.layout.simple_spinner_item,
                itensStatus);

        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spStatusPedido.setAdapter(ad);

        controller = new PedidoController(new PedidoDao(view.getContext()));

        btnInserirPedido.setOnClickListener(op -> inserirPedido());
        btnModificarPedidos.setOnClickListener(op -> modificarPedido());
        btnExcluirPedido.setOnClickListener(op -> excluirPedido());
        btnBuscarPedido.setOnClickListener(op -> buscarPedido());
        btnAvancarPedido.setOnClickListener(op -> avancar());

        return view;
    }

    public void inserirPedido() {
        String codigo, nome, preco, status;
        codigo = etCodigoPedido.getText().toString();
        nome = etNomeClientePedido.getText().toString();
        preco = etPrecoFretePedido.getText().toString();
        status = spStatusPedido.getSelectedItem().toString();

        try {
            Pedido pedido = new Pedido();
            pedido.setCodigo(Integer.parseInt(codigo));
            pedido.setNomeCliente(nome);
            pedido.setValorFrete(Double.parseDouble(preco));
            pedido.setStatus(status);

            controller.inserir(pedido);
            limpar();
            Toast.makeText(getContext(), "Produto inserido", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), "Erro ao inserir pedido", Toast.LENGTH_LONG).show();
        }
    }

    public void modificarPedido() {
        String codigo, nome, preco, status;
        codigo = etCodigoPedido.getText().toString();
        nome = etNomeClientePedido.getText().toString();
        preco = etPrecoFretePedido.getText().toString();
        status = spStatusPedido.getSelectedItem().toString();

        try {
            Pedido pedido = new Pedido();
            pedido.setCodigo(Integer.parseInt(codigo));
            pedido.setNomeCliente(nome);
            pedido.setValorFrete(Double.parseDouble(preco));
            pedido.setStatus(status);

            controller.modificar(pedido);
            limpar();
            Toast.makeText(getContext(), "Produto modificado", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), "Erro ao modificar pedido", Toast.LENGTH_LONG).show();
        }
    }

    public void excluirPedido() {
        String codigo = etCodigoPedido.getText().toString();

        try {
            Pedido pedido = new Pedido();
            pedido.setCodigo(Integer.parseInt(codigo));
            controller.deletar(pedido);
            limpar();
            Toast.makeText(getContext(), "Produto excluido", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), "Erro ao excluir pedido", Toast.LENGTH_LONG).show();
        }
    }

    public void buscarPedido() {
        String codigo = etCodigoPedido.getText().toString();

        try {
            Pedido pedido = new Pedido();
            pedido.setCodigo(Integer.parseInt(codigo));
            controller.buscar(pedido);
            etCodigoPedido.setText(String.valueOf(pedido.getCodigo()));
            etNomeClientePedido.setText(pedido.getNomeCliente());
            etPrecoFretePedido.setText(String.valueOf(pedido.getValorFrete()));
            spStatusPedido.setSelection(itensStatus.indexOf(pedido.getStatus()));
            Toast.makeText(getContext(), "Produto excluido", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), "Erro ao excluir pedido", Toast.LENGTH_LONG).show();
        }
    }

    public void avancar() {
        Fragment fragment = new ItensFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }

    private void limpar() {
        etCodigoPedido.setText("");
        etNomeClientePedido.setText("");
        etPrecoFretePedido.setText("");
        spStatusPedido.setSelection(0);
    }
}