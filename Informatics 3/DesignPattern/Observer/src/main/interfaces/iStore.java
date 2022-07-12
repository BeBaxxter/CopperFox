package main.interfaces;

public interface iStore extends iObserver {

    /**
     * Print a list of all Shopping Carts in the current Store
     * @return a list of all Shopping Carts as a String
     */
    void printOwnCarts();
}
