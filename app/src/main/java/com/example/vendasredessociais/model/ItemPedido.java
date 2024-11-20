package com.example.vendasredessociais.model;

public class ItemPedido extends Calculavel{
    private int codigoItem;
    private Produto produto;
    private Pedido pedido;
    private int quantidade;

    public int getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(int codigoItem) {
        this.codigoItem = codigoItem;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }


    @Override
    public String toString() {
        return codigoItem +
                "# " + produto.getNome() +
                ", " + quantidade + "unid.";
    }

    @Override
    public double calcularTotal() {
        return quantidade * getProduto().getPreco();
    }
}
