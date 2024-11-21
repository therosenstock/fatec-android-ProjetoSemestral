package com.example.vendasredessociais;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vendasredessociais.controller.ItemPedidoController;
import com.example.vendasredessociais.controller.PedidoController;
import com.example.vendasredessociais.controller.ProdutoController;
import com.example.vendasredessociais.model.ItemPedido;
import com.example.vendasredessociais.model.Pedido;
import com.example.vendasredessociais.model.Produto;
import com.example.vendasredessociais.persistence.ItemPedidoDao;
import com.example.vendasredessociais.persistence.PedidoDao;
import com.example.vendasredessociais.persistence.ProdutoDao;

import java.util.List;

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
    private List<Produto> produtos;
    private ItemPedidoController itemController;
    private PedidoController pedidoController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_itens, container, false);

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

        itemController = new ItemPedidoController(new ItemPedidoDao(view.getContext()));
        pedidoController = new PedidoController(new PedidoDao(view.getContext()));
        ProdutoController produtoController = new ProdutoController(new ProdutoDao(view.getContext()));

        try {
            produtos = produtoController.listar();
            Produto produto = new Produto();
            produto.setCodigo(0);
            produto.setNome("Selecione um produto...");
            produtos.add(0, produto);

            ArrayAdapter ad = new ArrayAdapter(view.getContext(),
                    android.R.layout.simple_spinner_item,
                    produtos);
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spProdutoItem.setAdapter(ad);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error ao listar produtos", Toast.LENGTH_LONG).show();
        }

        btnInserirItem.setOnClickListener(op -> inserirItem());
        btnModificarItem.setOnClickListener(op -> modificarItem());
        btnExcluirItem.setOnClickListener(op -> excluirItem());
        btnBuscarItem.setOnClickListener(op -> buscarItem());
        btnListarItem.setOnClickListener(op -> listarItens());

        return view;
    }

    public void inserirItem() {
        String codigo, codigoPedido, quantidade;
        Produto produto = (Produto) spProdutoItem.getSelectedItem();
        codigo = etCodigoItem.getText().toString();
        codigoPedido = etCodigoPedidoItem.getText().toString();
        quantidade = etQuantidadeItem.getText().toString();

        try {
            Pedido pedido = new Pedido();
            pedido.setCodigo(Integer.parseInt(codigoPedido));
            pedidoController.buscar(pedido);

            ItemPedido item = new ItemPedido();
            item.setCodigoItem(Integer.parseInt(codigo));
            item.setProduto(produto);
            item.setQuantidade(Integer.parseInt(quantidade));
            item.setPedido(pedido);

            itemController.inserir(item);
            limpar();
            Toast.makeText(getContext(), "Item inserido", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error ao inserir item", Toast.LENGTH_LONG).show();
        }
    }

    public void modificarItem() {
        String codigo, codigoPedido, quantidade;
        Produto produto = (Produto) spProdutoItem.getSelectedItem();
        codigo = etCodigoItem.getText().toString();
        codigoPedido = etCodigoPedidoItem.getText().toString();
        quantidade = etQuantidadeItem.getText().toString();

        try {
            Pedido pedido = new Pedido();
            pedido.setCodigo(Integer.parseInt(codigoPedido));
            pedidoController.buscar(pedido);

            ItemPedido item = new ItemPedido();
            item.setCodigoItem(Integer.parseInt(codigo));
            item.setProduto(produto);
            item.setQuantidade(Integer.parseInt(quantidade));
            item.setPedido(pedido);

            itemController.modificar(item);
            limpar();
            Toast.makeText(getContext(), "Item modificado", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error ao modificar item", Toast.LENGTH_LONG).show();
        }
    }

    public void excluirItem() {
        String codigo = etCodigoItem.getText().toString();

        try {

            ItemPedido item = new ItemPedido();
            item.setCodigoItem(Integer.parseInt(codigo));

            itemController.deletar(item);
            limpar();
            Toast.makeText(getContext(), "Item excluido", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error ao excluir item", Toast.LENGTH_LONG).show();
        }
    }

    public void buscarItem() {
        String codigo = etCodigoItem.getText().toString();

        try {

            ItemPedido item = new ItemPedido();
            item.setCodigoItem(Integer.parseInt(codigo));

            itemController.buscar(item);

            System.out.println(item);

            int index;

            for (index = 0; index < produtos.size(); index++) {
                if (item.getProduto().getCodigo() == produtos.get(index).getCodigo()) break;
            }

            spProdutoItem.setSelection(index);
            etCodigoItem.setText(String.valueOf(item.getCodigoItem()));
            etCodigoPedidoItem.setText(String.valueOf(item.getPedido().getCodigo()));
            etQuantidadeItem.setText(String.valueOf(item.getQuantidade()));
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error ao buscar item", Toast.LENGTH_LONG).show();
        }
    }

    public void listarItens() {
        try {
            List<ItemPedido> items = itemController.listar();
            StringBuilder sb = new StringBuilder();

            for (ItemPedido item : items) {
                sb.append(item.toString());
                sb.append('\n');
            }

            tvListaItens.setText(sb.toString());
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error ao listar itens", Toast.LENGTH_LONG).show();
        }
    }

    private void limpar() {
        spProdutoItem.setSelection(0);
        etCodigoItem.setText("");
        etCodigoPedidoItem.setText("");
        etQuantidadeItem.setText("");
    }
}