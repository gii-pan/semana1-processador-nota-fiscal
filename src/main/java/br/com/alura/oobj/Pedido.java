package br.com.alura.oobj;

import java.math.BigDecimal;
import java.util.List;

public class Pedido {

  private List<ItemPedido> itens;

  public List<ItemPedido> getItens() {
    return itens;
  }

  public void setItens(List<ItemPedido> itens) {
    this.itens = itens;
  }

  public BigDecimal retornaValorTotalPedido(){
    BigDecimal totalPedido = BigDecimal.ZERO;
    for (ItemPedido itemPedido : itens) {
      BigDecimal totalItemPedido = itemPedido.retornaValorDeItemPedidoTotal();
      totalPedido = totalPedido.add(totalItemPedido);
    }
    return totalPedido;
  }

  @Override
  public String toString() {
    return "Pedido{" +
        "itens=" + itens +
        '}';
  }
}
