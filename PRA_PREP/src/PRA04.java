/*
2
101
order1
2
orange
32
mango
11
201
order2
1
papaya
12
101
orange
 */


import java.util.*;

// Order class
class Order {
    int orderId;
    String orderName;
    ArrayList<String> products;
    ArrayList<Integer> quantities;

    public Order(int orderId, String orderName, ArrayList<String> products, ArrayList<Integer> quantities) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.products = products;
        this.quantities = quantities;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public ArrayList<String> getProducts() {
        return products;
    }

    public ArrayList<Integer> getQuantities() {
        return quantities;
    }
}

// Custom Exception
class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }
}

// Service class with 2 required methods
class OrdersService {
    // Task 1: Find product with max quantity for given orderId
    public String getMaxQuantityProduct(int orderId, ArrayList<Order> orders) {
        for (Order o : orders) {
            if (o.getOrderId() == orderId) {
                if (o.getProducts().isEmpty()) break;

                int maxIndex = 0;
                for (int i = 1; i < o.getQuantities().size(); i++) {
                    if (o.getQuantities().get(i) > o.getQuantities().get(maxIndex)) {
                        maxIndex = i;
                    }
                }
                return o.getProducts().get(maxIndex);
            }
        }
        System.out.println("Order Id Not Found");
        return null;
    }

    // Task 2: Find orders that contain the given product name
    public ArrayList<Order> getOrdersByProductName(String productName, ArrayList<Order> orders) throws ProductNotFoundException {
        ArrayList<Order> result = new ArrayList<>();
        for (Order o : orders) {
            for (String p : o.getProducts()) {
                if (p.equalsIgnoreCase(productName)) {
                    result.add(o);
                    break;
                }
            }
        }

        if (result.isEmpty()) {
            throw new ProductNotFoundException("Product Not Found");
        }

        return result;
    }
}

// Main class
public class PRA04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        ArrayList<Order> orders = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int id = Integer.parseInt(sc.nextLine());
            String name = sc.nextLine();
            int count = Integer.parseInt(sc.nextLine());

            ArrayList<String> products = new ArrayList<>();
            ArrayList<Integer> quantities = new ArrayList<>();

            for (int j = 0; j < count; j++) {
                String product = sc.nextLine();
                int quantity = Integer.parseInt(sc.nextLine());

                products.add(product);
                quantities.add(quantity);
            }

            orders.add(new Order(id, name, products, quantities));
        }

        int searchOrderId = Integer.parseInt(sc.nextLine());
        String searchProduct = sc.nextLine();

        OrdersService service = new OrdersService();

        // Task 1 Output
        String maxProduct = service.getMaxQuantityProduct(searchOrderId, orders);
        if (maxProduct != null) {
            System.out.println(maxProduct);
        }

        // Task 2 Output
        try {
            ArrayList<Order> foundOrders = service.getOrdersByProductName(searchProduct, orders);
            for (Order o : foundOrders) {
                System.out.println(o.getOrderId());
                System.out.println(o.getOrderName());
            }
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}