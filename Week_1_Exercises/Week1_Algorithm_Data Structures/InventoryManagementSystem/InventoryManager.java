package InventoryManagementSystem;
import java.util.HashMap;
import java.util.Map;
public class InventoryManager {
    private Map<Integer, Product> inventory = new HashMap<>();

    // Add product to inventory
    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
    }

    // Update product details
    public void updateProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            inventory.put(product.getProductId(), product);
        } else {
            System.out.println("Product not found.");
        }
    }

    // Delete product from inventory
    public void deleteProduct(int productId) {
        if (inventory.containsKey(productId)) {
            inventory.remove(productId);
        } else {
            System.out.println("Product not found.");
        }
    }

    // Display all products
    public void displayProducts() {
        for (Product product : inventory.values()) {
            System.out.println(product);
        }
    }
}
