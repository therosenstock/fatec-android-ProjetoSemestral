package com.example.vendasredessociais.model;

import java.util.List;

public class Pedido extends Calculavel{
    /*
     *@author:<Fabiola Rodrigues dos Santos / RA: 1110482313011>
     */
    private int codigo;
    private double valorFrete;
    private String nomeCliente;
    private String status;
    private List<ItemPedido> itens;

    public Pedido() {}


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "Pedido: " + codigo +
                "#\n" + nomeCliente  +" - Frete: R$ " + valorFrete +
                "\nStatus: " + status + "\nItens: \n";
    }

    @Override
    public double calcularTotal() {
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.calcularTotal();
        }
        return total + valorFrete;
    }
}
