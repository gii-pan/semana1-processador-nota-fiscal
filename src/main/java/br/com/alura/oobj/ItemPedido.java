package br.com.alura.oobj;

import com.opencsv.bean.CsvBindByName;

import java.math.BigDecimal;

public class ItemPedido {

  @CsvBindByName
  private Long codigo;

  @CsvBindByName
  private String descricao;

  @CsvBindByName
  private Integer quantidade;

  @CsvBindByName
  private BigDecimal valorUnitario;

  @CsvBindByName
  private String classeFiscal;

  public String getClasseFiscal() {
    return classeFiscal;
  }

  public BigDecimal retornaValorDeItemPedidoTotal() {
    BigDecimal valorTotal = BigDecimal.ZERO;
    valorTotal = this.valorUnitario.multiply(BigDecimal.valueOf(this.quantidade));
    return valorTotal;
  }
  @Override
  public String toString() {
    return "ItemPedido{" +
        "codigo=" + codigo +
        ", descricao='" + descricao + '\'' +
        ", quantidade=" + quantidade +
        ", valorUnitario=" + valorUnitario +
        ", classeFiscal='" + classeFiscal + '\'' +
        '}';
  }
}
