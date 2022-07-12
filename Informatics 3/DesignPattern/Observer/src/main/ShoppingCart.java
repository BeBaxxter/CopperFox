package main;

import main.helper.Brands;
import main.interfaces.iObserver;
import main.interfaces.iSubject;

import java.util.ArrayList;

public class ShoppingCart implements iSubject {

    /** Declaration **/
    int id;
    String location;
    Brands brand;

    private ArrayList<iObserver> observers = new ArrayList<>();

    /** Constructor **/
    public ShoppingCart(int id, String location, Brands brand) {
        this.id = id;
        this.location = location;
        this.brand = brand;
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
            System.out.println("[Notify: ] Observers change in ShoppingCart");
            ob.update(this);
        }
    }

    /** Getter / Setter **/
    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public Brands getBrand() {
        return brand;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
