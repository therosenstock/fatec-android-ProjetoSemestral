package com.example.vendasredessociais;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vendasredessociais.controller.ItemPedidoController;
import com.example.vendasredessociais.controller.PedidoController;
import com.example.vendasredessociais.model.ItemPedido;
import com.example.vendasredessociais.model.Pedido;
import com.example.vendasredessociais.persistence.ItemPedidoDao;
import com.example.vendasredessociais.persistence.PedidoDao;

import java.sql.SQLException;
import java.util.List;


public class ListarPedidosFragment extends Fragment {
    /*
     *@author:<Fabiola Rodrigues dos Santos / RA: 1110482313011>
     */
    View view;

    EditText edCodigoListaPedidos;
    Button btnBuscarLista;
    Button btnListarPedidos;
    TextView tvListaPedidos;
    PedidoController controller;
    ItemPedidoController itemController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_listar_pedidos, container, false);

        edCodigoListaPedidos = view.findViewById(R.id.edCodigoListaPedidos);
        btnBuscarLista = view.findViewById(R.id.btnBuscarLista);
        btnListarPedidos = view.findViewById(R.id.btnListarPedidos);
        tvListaPedidos = view.findViewById(R.id.tvListaPedidos);
        tvListaPedidos.setMovementMethod(new ScrollingMovementMethod());

        controller = new PedidoController(new PedidoDao(view.getContext()));
        itemController = new ItemPedidoController(new ItemPedidoDao(view.getContext()));

        btnBuscarLista.setOnClickListener(op -> buscarPedido());
        btnListarPedidos.setOnClickListener(op -> listarPedidos());

        return view;
    }

    public void buscarPedido() {
        tvListaPedidos.setText("");
        try {
            StringBuilder sb = new StringBuilder();
            String codigo = edCodigoListaPedidos.getText().toString();
            Pedido pedido = new Pedido();
            pedido.setCodigo(Integer.parseInt(codigo));
            controller.buscar(pedido);

            String pedidos = pedido.toString();
            sb.append(pedidos);
            String itens = listarItens(pedido.getCodigo());
            sb.append(itens);
            tvListaPedidos.setText(sb.toString());
            edCodigoListaPedidos.setText("");
        } catch (Exception e) {
            Toast.makeText(getContext(), "Erro ao buscar pedido", Toast.LENGTH_LONG).show();
        }
    }

    public void listarPedidos(){
        tvListaPedidos.setText("");
        try {
            List<Pedido> pedidos = controller.listar();
            StringBuilder sb = new StringBuilder();

            for (Pedido pedido : pedidos) {
                sb.append(pedido.toString());

                String itens = this.listarItens(pedido.getCodigo());
                sb.append(itens);
                sb.append("----------");
                sb.append('\n');
            }

            tvListaPedidos.setText(sb.toString());
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error ao listar pedidos", Toast.LENGTH_LONG).show();
        }
    }
    public String listarItens(int codigoPedido){
        StringBuilder sb = new StringBuilder();
        try {
            List<ItemPedido> items = itemController.findItemsPedido(codigoPedido);
            double total = 0;
            for (ItemPedido item : items) {
                sb.append(item.toString());
                sb.append('\n');
                total += item.getQuantidade() * item.getProduto().getPreco();
            }
            sb.append('\n');
            sb.append("Total: R$ " + total);
            sb.append('\n');
            tvListaPedidos.setText(sb.toString());


        } catch (Exception e) {
            Toast.makeText(getContext(), "Error ao listar itens", Toast.LENGTH_LONG).show();
        }
        return sb.toString();

    }
}