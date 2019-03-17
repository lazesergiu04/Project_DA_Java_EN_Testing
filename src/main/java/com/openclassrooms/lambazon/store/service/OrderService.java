package com.openclassrooms.lambazon.store.service;

import com.openclassrooms.lambazon.store.domain.model.Cart;
import com.openclassrooms.lambazon.store.domain.model.Order;
import com.openclassrooms.lambazon.store.domain.OrderRepository;
import com.openclassrooms.lambazon.store.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

    private OrderRepository orderRepository;
    private ProductService productService;

    // FIXME: Using a single cart to prove the concept. Remember to persist this in the session.
    private Cart cart = new Cart();


    @Autowired
    public OrderService(OrderRepository orderRepository, ProductService productService)
    {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public boolean addToCart(Long productId, Cart cart){
        // FIXME: Use session cart
        Product product = productService.getByProductId(productId);
        if (product !=null){
            this.cart.addItem(product , 1);
            return true;
        }
        return false;
    }

    /**
     *
     * @param order Order to be saved
     */
    public void saveOrder(Order order)
    {
        orderRepository.save(order);
        productService.updateProductQuantities(this.cart);
    }

    /**
     * @return Returns the single instance of cart in the application
     */
    public Cart getCart(){
        return this.cart;
    }

    public void removeFromCart(Long productId) {
        Product product = productService.getByProductId(productId);
        if (product !=null) {
            getCart().removeLine(product);
        }
    }

    public boolean isCartEmpty() {
        return getCart().getCartLineList().isEmpty();
    }

    public void createOrder(Order order) {

        order.setLines(getCart().getCartLineList());
        saveOrder(order);
        this.cart.clear();
    }
}
