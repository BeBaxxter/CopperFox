package main.interfaces;

import main.ShoppingCart;

public interface iObserver {

    /**
     * Updates the observer
     * @param shoppingCart the single shopping cart
     */
    void update(ShoppingCart shoppingCart);


}
