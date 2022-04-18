package br.com.alura.oobj;

import java.math.BigDecimal;
import java.util.List;
import java.util.TreeMap;

public class SubTotalPorClasseFiscal {
    TreeMap<String, BigDecimal> treemap = new TreeMap<>();

    public TreeMap<String, BigDecimal> retornaSubtotalPorClasseFinal(List<ItemPedido> itensPedido) {
        BigDecimal subTotal = BigDecimal.ZERO;
        for (ItemPedido itemPedido : itensPedido) {
            BigDecimal totalItemPedido = itemPedido.retornaValorDeItemPedidoTotal();
            String classeFiscal = itemPedido.getClasseFiscal();
            subTotal = treemap.get(classeFiscal);
            if (subTotal != null) {
                treemap.put(classeFiscal, subTotal.add(totalItemPedido));
            } else {
                treemap.put(classeFiscal, totalItemPedido);
            }
        }
        return treemap;
    }
}
