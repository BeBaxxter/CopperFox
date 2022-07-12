package main.interfaces;

public interface iSubject {

    /**
     * Registered the given Observer
     * @param observer
     */
    void registerObserver(iObserver observer);

    /**
     * Remove the given Observer
     * @param observer
     */
    void removeObserver(iObserver observer);

    /**
     * Notify the Observer
     */
    void notifyObservers();
}
