package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ServiceImp1 implements Service {

    private static Connection conn;
    static {
        String url ="jdbc:mysql:/localhost:3307/productdb";
        String username="root";
        String password="root";

        try {
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Product>displayAllProduct()
    {
        String displayQuery = "SELECT * FROM PRODUCT_INFO";
        List<Product> productList = new ArrayList<>();
        try{

            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery(displayQuery);

            while (rs.next())
            {
                Product product = new Product();
                product.setProductId(rs.getInt(1));
                product.setProductName(rs.getString(2));
                product.setProductPrice(rs.getDouble(3));

                productList.add(product);
            }
        }
        catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
        return productList;
    }

    public  boolean placeOrder(Order newOrder)
    {
        boolean status = false;
        try {
            CallableStatement cstmt = conn.prepareCall("{call placeOrder(?,?,?,?)}");
            cstmt.setString(1, newOrder.getCustomerName());
            cstmt.setInt(2, newOrder.getProductId());
            cstmt.setInt(3, newOrder.getProductQty());

            cstmt.execute();

            status = cstmt.getBoolean(4);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return status;
    }

    public List<OrderInfo>displayAllOrders()
    {
        List<OrderInfo> orderList = new ArrayList<>();
        String  displayQuery= "select\n" +
                "\to.order_id,\n" +
                "    o.customer_name,\n" +
                "    o.product_qty * p.product_price AS Total,\n" +
                "    p.product_name,\n" +
                "    o.product_qty\n" +
                "from\n" +
                "\torder_info o\n" +
                "    INNER JOIN\n" +
                "    product_info p ON o.product_id = p.product_id;";

        try {
            Statement stmt = conn.createStatement();

            ResultSet rs =stmt.executeQuery(displayQuery);
            while(rs.next())
            {
                int oId = rs.getInt(1);
                String cName = rs.getString(2);
                double totalAmt = rs.getDouble(3);
                String pName = rs.getString(4);
                int pQty = rs.getInt(5);

                OrderInfo o1 = new OrderInfo(oId, cName, pName, pQty,totalAmt);
                orderList.add(o1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return orderList;


    }


}
