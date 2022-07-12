package main;

import main.interfaces.iObserver;
import main.interfaces.iSubject;
import java.util.ArrayList;

public class ShoppingCartList implements iSubject, iObserver {

    /** Declaration **/
    private ArrayList<iObserver> observers = new ArrayList<>();
    private ArrayList<ShoppingCart> shoppingCarts = new ArrayList<>();
    private ShoppingCart shoppingCart;

    /**
     * Getter / Setter
     */
    public void addShoppingCarts(ShoppingCart newShoppingCart) {
        newShoppingCart.registerObserver(this);
        this.shoppingCarts.add(newShoppingCart);
        this.shoppingCart = newShoppingCart;
        notifyObservers();
    }

    /**
     * Registered the given Observer
     * @param observer
     */
    @Override
    public void registerObserver(iObserver observer) {
        observers.add(observer);
    }

    /**
     * Remove the given Observer
     * @param observer
     */
    @Override
    public void removeObserver(iObserver observer) {
        observers.remove(observer);

    }

    /**
     * Notify the Observer
     */
    @Override
    public void notifyObservers() {

        for (iObserver ob : observers) {
            System.out.println("[Notify: ] Observers change in ShoppingCartList");
            ob.update(this.shoppingCart);
        }
    }

    /**
     * Update with the new shopping cart
     * @param newShoppingCart
     */
    @Override
    public void update(ShoppingCart newShoppingCart) {
        System.out.println("[Update: ] Observers change in ShoppingCartList");
        this.shoppingCart = newShoppingCart;
        notifyObservers();
    }
}
