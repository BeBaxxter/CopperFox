package main;

import main.helper.Brands;
import main.interfaces.iStore;
import java.util.ArrayList;

public class Store implements iStore {

    /** Declaration **/
    Brands brand;
    private ArrayList<ShoppingCart> shoppingCarts = new ArrayList<>();

    /** Constructor **/
    public Store(Brands brand) {
        this.brand = brand;
    }


    /**
     * Getter / Setter
     */
    public ArrayList<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }


    /**
     * Print a list with the own carts of the Store
     */
    @Override
    public void printOwnCarts() {

        System.out.println("Current carts in the list");
        System.out.println("-------------------------");

        for(ShoppingCart cart : shoppingCarts) {
            System.out.println("Cart ID: " + cart.getId());
            System.out.println("Cart Location: " + cart.getLocation());
            System.out.println("Cart Brand: " + cart.getBrand());
        }
    }

    /**
     * Updates the global shopping carts list
     * Updates the own    shopping carts list
     * @param shoppingCart the single shopping cart
     */
    @Override
    public void update(ShoppingCart shoppingCart) {

        // When brand of cart is unknown or the same as the shop
        if(shoppingCart.getBrand().equals(brand) || shoppingCart.getBrand().equals(Brands.unknown))  {

            if(!shoppingCarts.contains(shoppingCart)) {
                // add to own shopping cart list
                shoppingCarts.add(shoppingCart);
            }
            else {
                System.out.println("[Change: ] Location of " + shoppingCart.getId() + " to " + shoppingCart.getLocation());
            }
        }
    }
}

