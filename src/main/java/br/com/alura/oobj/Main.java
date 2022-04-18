package br.com.alura.oobj;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    if (args.length <= 0) {
      throw new IllegalArgumentException("Forneça um nome de arquivo.");
    }

    String arquivo = args[0];

    List<ItemPedido> itensPedido;
    Pedido pedidoTotal = new Pedido();
    SubTotalPorClasseFiscal subTotal = new SubTotalPorClasseFiscal();

    if (arquivo.endsWith(".csv")) {
      try {
        Reader reader = new FileReader(arquivo);
        CsvToBean<ItemPedido> csvToBean = new CsvToBeanBuilder<ItemPedido>(reader)
            .withSeparator(';')
            .withType(ItemPedido.class)
            .build();
        itensPedido = csvToBean.parse();
      } catch (IOException ex) {
        throw new IllegalStateException(ex);
      }

    } else if (arquivo.endsWith(".xml")) {
      try {
        Reader reader = new FileReader(arquivo);
        XmlMapper mapper = new XmlMapper();

        Pedido pedido = mapper.readValue(reader, Pedido.class);
        itensPedido = pedido.getItens();
      } catch (IOException ex) {
        throw new IllegalStateException(ex);
      }

    } else {
      throw new IllegalArgumentException("Formato de arquivo inválido: " + arquivo);
    }

    pedidoTotal.setItens(itensPedido);
    subTotal.retornaSubtotalPorClasseFinal(itensPedido);

    System.out.println("## Total do pedido: " + pedidoTotal.retornaValorTotalPedido());
    System.out.println("\n## Subtotal por classe fiscal");
    for (String classeFiscal : subTotal.treemap.keySet()) {
      System.out.println("\n\tClasse fiscal: " + classeFiscal);
      BigDecimal subtotal = subTotal.treemap.get(classeFiscal);
      System.out.println("\tSubtotal: " + subtotal);
    }


  }

}
