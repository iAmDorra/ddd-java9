package fr.arolla.modec.service;

import fr.arolla.modec.entity.*;
import fr.arolla.modec.repository.CartLineRepository;
import fr.arolla.modec.repository.CartRepository;
import fr.arolla.modec.repository.ProductRepository;
import fr.arolla.modec.repository.ShippingServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartLineRepository cartLineRepository;
    private final ShippingServiceRepository shippingServiceRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository, CartLineRepository cartLineRepository, ShippingServiceRepository shippingServiceRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartLineRepository = cartLineRepository;
        this.shippingServiceRepository = shippingServiceRepository;
    }

    public CartId createCart() {
        return cartRepository.save(new Cart()).getId();
    }

    public void addToCart(CartId cartId, Sku sku, Quantity quantity) {
        Product product = productRepository.findOneBySku(sku);
        CartLine line = new CartLine(sku, product.getName(), quantity);
        cartLineRepository.save(line);
        cartRepository.findById(cartId).get().getLines().add(line);
    }

    public List<CartLine> getLines(CartId cartId) {
        return cartRepository.findById(cartId).get().getLines();
    }

    public void setShippingAddress(CartId cartId, String fullName, String line1, String city, String zipCode, String isoCountryCode) {
        ShippingAddress address = new ShippingAddress(fullName, line1, city, zipCode, isoCountryCode);
        cartRepository.findById(cartId).get().setShippingAddress(address);
    }

    public List<ShippingService> getShippingServices(CartId cartId) {
        List<ShippingService> servicesFound = new ArrayList<>();
        Cart cart = cartRepository.findById(cartId).get();
        if (cart.getShippingAddress() != null && !cart.getLines().isEmpty()) {
            servicesFound.add(shippingServiceRepository.findOneByCode("Chrono10"));
        }
        return servicesFound;
    }

    public void setRecipient(CartId cartId, String fullName, String eMail) {
        Recipient recipient = new Recipient(fullName, eMail);
        cartRepository.findById(cartId).get().setRecipient(recipient);
    }
}
