package application;

public class OrderInfo {
        private int orderId;
        private String customerName;
        private String productName;
        private int productQty;

    private double totalAmount;

        public OrderInfo(int orderId, String customerName,  String productName, int productQty,double totalAmount) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.productName = productName;
            this.productQty = productQty;
            this.totalAmount = totalAmount;
        }

        public int getOrderId() {
            return orderId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public String getProductName() {
            return productName;
        }

        public int getProductQty() {
            return productQty;
        }
         public double getTotalAmount() {
             return totalAmount;
         }
    }

}
