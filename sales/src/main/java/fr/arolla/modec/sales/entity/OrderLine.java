package fr.arolla.modec.sales.entity;

import javax.persistence.*;

@Entity
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    Sku productSku;
    String productName;

    @Embedded
    Quantity quantity;

    public OrderLine() { //for JPA
    }

    public OrderLine(Sku productSku, String productName, Quantity quantity) {
        this.productSku = productSku;
        this.productName = productName;
        this.quantity = quantity;
    }

    public Sku getProductSku() {
        return productSku;
    }

    public String getProductName() {
        return productName;
    }

    public Quantity getQuantity() {
        return quantity;
    }
}
