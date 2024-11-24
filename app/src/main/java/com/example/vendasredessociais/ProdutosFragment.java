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

import com.example.vendasredessociais.controller.ProdutoController;
import com.example.vendasredessociais.model.Produto;
import com.example.vendasredessociais.persistence.ProdutoDao;

import java.util.List;


public class ProdutosFragment extends Fragment {
    /*
     *@author:<Fabiola Rodrigues dos Santos / RA: 1110482313011>
     */
    View view;
    private TextView tvProdutos;
    private EditText edCodigoProduto;
    private EditText edQuantidadeProduto;
    private EditText edNomeProduto;
    private EditText edPrecoProduto;
    private Button btnBuscarProduto;
    private Button btnInserirProduto;
    private Button btnModificarProduto;
    private Button btnExcluirProduto;
    private Button btnListarProduto;
    private TextView tvListaProdutos;
    private ProdutoController controller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_produtos, container, false);

        tvProdutos = view.findViewById(R.id.tvProdutos);
        edCodigoProduto = view.findViewById(R.id.edCodigoProduto);
        edQuantidadeProduto = view.findViewById(R.id.edQuantidadeProduto);
        edNomeProduto = view.findViewById(R.id.edNomeProduto);
        edPrecoProduto = view.findViewById(R.id.edPrecoProduto);
        btnBuscarProduto = view.findViewById(R.id.btnBuscarProduto);
        btnInserirProduto = view.findViewById(R.id.btnInserirProduto);
        btnModificarProduto = view.findViewById(R.id.btnModificarProduto);
        btnExcluirProduto = view.findViewById(R.id.btnExcluirProduto);
        btnListarProduto = view.findViewById(R.id.btnListarProduto);
        tvListaProdutos = view.findViewById(R.id.tvListaProdutos);
        tvListaProdutos.setMovementMethod(new ScrollingMovementMethod());

        controller = new ProdutoController(new ProdutoDao(view.getContext()));

        btnInserirProduto.setOnClickListener(op -> inserirProduto());
        btnBuscarProduto.setOnClickListener(op -> buscarProduto());
        btnExcluirProduto.setOnClickListener(op -> excluirProduto());
        btnModificarProduto.setOnClickListener(op -> modificarProduto());
        btnListarProduto.setOnClickListener(op -> listarProdutos());

        return view;
    }

    public void inserirProduto() {
        String codigo, nome, quantidade, preco;

        codigo = edCodigoProduto.getText().toString();
        nome = edNomeProduto.getText().toString();
        preco = edPrecoProduto.getText().toString();
        quantidade = edQuantidadeProduto.getText().toString();

        try {
            Produto produto = new Produto();
            produto.setNome(nome);
            produto.setCodigo(Integer.parseInt(codigo));
            produto.setQuantidade(Integer.parseInt(quantidade));
            produto.setPreco(Double.parseDouble(preco));
            controller.inserir(produto);
            limpar();
            Toast.makeText(getContext(), "Produto inserido", Toast.LENGTH_LONG).show();
        } catch (Exception error) {
            Toast.makeText(getContext(), "Error ao inserir o produto", Toast.LENGTH_LONG).show();
        }
    }

    public void buscarProduto() {
        String codigo = edCodigoProduto.getText().toString();

        try {
            Produto produto = new Produto();
            produto.setCodigo(Integer.parseInt(codigo));
            controller.buscar(produto);
            edCodigoProduto.setText(String.valueOf(produto.getCodigo()));
            edQuantidadeProduto.setText(String.valueOf(produto.getQuantidade()));
            edNomeProduto.setText(produto.getNome());
            edPrecoProduto.setText(String.valueOf(produto.getPreco()));
        } catch (Exception error) {
            Toast.makeText(getContext(), "Error ao buscar o produto", Toast.LENGTH_LONG).show();
        }
    }

    public void excluirProduto() {
        String codigo = edCodigoProduto.getText().toString();

        try {
            Produto produto = new Produto();
            produto.setCodigo(Integer.parseInt(codigo));
            controller.deletar(produto);
            limpar();
            Toast.makeText(getContext(), "Produto excluido", Toast.LENGTH_LONG).show();
        } catch (Exception error) {
            Toast.makeText(getContext(), "Error ao excluir o produto", Toast.LENGTH_LONG).show();
        }
    }

    public void modificarProduto() {
        String codigo, nome, quantidade, preco;

        codigo = edCodigoProduto.getText().toString();
        nome = edNomeProduto.getText().toString();
        preco = edPrecoProduto.getText().toString();
        quantidade = edQuantidadeProduto.getText().toString();

        try {
            Produto produto = new Produto();
            produto.setNome(nome);
            produto.setCodigo(Integer.parseInt(codigo));
            produto.setQuantidade(Integer.parseInt(quantidade));
            produto.setPreco(Double.parseDouble(preco));
            controller.modificar(produto);
            limpar();
            Toast.makeText(getContext(), "Produto modificado", Toast.LENGTH_LONG).show();
        } catch (Exception error) {
            Toast.makeText(getContext(), "Error ao modificar o produto", Toast.LENGTH_LONG).show();
        }
    }

    public void listarProdutos() {
        try {
            List<Produto> produtos = controller.listar();
            StringBuilder sb = new StringBuilder();

            for (Produto produto : produtos) {
                sb.append(produto.toString());
                sb.append('\n');
            }

            tvListaProdutos.setText(sb.toString());
        } catch (Exception error) {
            Toast.makeText(getContext(), "Error ao listar produtos", Toast.LENGTH_LONG).show();
        }
    }

    private void limpar() {
        edCodigoProduto.setText("");
        edQuantidadeProduto.setText("");
        edNomeProduto.setText("");
        edPrecoProduto.setText("");
    }
}