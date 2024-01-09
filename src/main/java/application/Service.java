package application;

import java.util.List;

public interface Service {
    List<Product> displayAllProduct();

    boolean placeOrder(Order newOrder);

    boolean removeProduct();

    boolean updateProduct();
    List<OrderInfo>displayAllOrders();
}
