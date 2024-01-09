package application;

import java.util.List;
import java.util.Scanner;

public class mainApp {
    private static Scanner sc = new Scanner(System.in);
    private static Service service=new ServiceImpl();

    public static void main(String[] args) {
        System.out.println("\n\n\n SELECT OPERATION ");
        System.out.println("1. DISPLAY ALL PRODUCTS ");
        System.out.println("2. REMOVE PRODUCT ");
        System.out.println("3. UPDATE PRODUCT ");
        System.out.println("4. PLACE ORDER ");
        System.out.println("5. DISPLAY  ORDERS ");
        System.out.println("6. EXIT");

        int ch = sc.nextInt();

        switch (ch)
        {
            case 1:
                displayAllProducts();
                break;
            case 2:
                //removeProduct();
                break;
            case 3:
                //updateProduct();
                break;
            case 4 :
                placeOrder();
                break;
            case 5:
                displayAllOrder();
                break;
            case 6:
                System.exit(0);
                break;
            default:
                System.err.println("INVALID CHOICE");

        }
        main(args);

    }
    private static void displayAllProducts()
    {
        List<Product> productList = service.displayAllProduct();

        for (Product p: productList)
            System.out.println(p.getProductId() + "   "+ p.getProductName() + "   "+ p.getProductPrice() );

    }
    private static void removeProduct()
    {

    }

    private static void updateProduct()
    {

    }

    private static void placeOrder()
    {
        System.out.println("ENTER NAME ");
        String customerName = sc.next();
        System.out.println("ENTER PRODUCT ID");
        int productId = sc.nextInt();
        System.out.println("ENTER PRODUCT QUANTITY");
        int productQty= sc.nextInt();

        Order newOrder = new Order(customerName, productId , productQty);
        boolean status = service.placeOrder(newOrder);

        if(status)
            System.out.println("ORDER PLACED !!");
        else
            System.out.println("ORDER NOT PLACED !!");
    }
    private static void displayAllOrder()
    {
        for (OrderInfo o1 : service.displayAllOrders())
        {
            System.out.println(o1.getOrderId() + "   "+ o1.getCustomerName() + "   "+ o1.getTotalAmount()+ "   "+ o1.getProductName()+"   "+ o1.getProductQty());
        }
    }

}
