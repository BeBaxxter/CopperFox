package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.ShoppingCart;
import main.ShoppingCartList;
import main.Store;
import main.helper.Brands;

/**
 * Class for doing the JUnit tests for the Observer.
 */
public class ShoppingCartListTest {

    ShoppingCartList shoppingCartList = new ShoppingCartList();

    Store edeka1 = new Store(Brands.Edeka);
    Store edeka2 = new Store(Brands.Edeka);
    Store lidl = new Store(Brands.Lidl);
    Store aldi = new Store(Brands.Aldi);
    Store kaufhof = new Store(Brands.Kaufhof);
    Store penny = new Store(Brands.Penny);
    Store bauhaus = new Store(Brands.Bauhaus);
    Store unknown = new Store(Brands.unknown);

    @BeforeEach
    void setUp() {
        ShoppingCart shoppingCart1 = new ShoppingCart(1, "HTW Berlin", Brands.Bauhaus);
        ShoppingCart shoppingCart2 = new ShoppingCart(2, "Artus Zuhause", Brands.Penny);
        ShoppingCart shoppingCart3 = new ShoppingCart(3, "Lucas Zuhause", Brands.Aldi);
        ShoppingCart shoppingCart4 = new ShoppingCart(4, "Max Zuhause", Brands.unknown);
        ShoppingCart shoppingCart5 = new ShoppingCart(5, "Firlstraße", Brands.unknown);
        ShoppingCart shoppingCart6 = new ShoppingCart(6, "Alexanderplatz", Brands.Edeka);
        ShoppingCart shoppingCart7 = new ShoppingCart(7, "Tierpark", Brands.Kaufhof);
        ShoppingCart shoppingCart8 = new ShoppingCart(8, "HU Berlin", Brands.Edeka);
        ShoppingCart shoppingCart9 = new ShoppingCart(9, "Wönichstraße", Brands.Aldi);


        ShoppingCartList shoppingCartList = new ShoppingCartList();
        shoppingCartList.addShoppingCarts(shoppingCart1);
        shoppingCartList.addShoppingCarts(shoppingCart3);
        shoppingCartList.addShoppingCarts(shoppingCart4);
        shoppingCartList.addShoppingCarts(shoppingCart6);
        shoppingCartList.addShoppingCarts(shoppingCart7);
        shoppingCartList.addShoppingCarts(shoppingCart9);

        // Register the store to list
        shoppingCartList.registerObserver(penny);
        shoppingCartList.registerObserver(kaufhof);
        shoppingCartList.registerObserver(lidl);
        shoppingCartList.registerObserver(unknown);
        shoppingCartList.registerObserver(aldi);
        shoppingCartList.registerObserver(edeka1);
        shoppingCartList.registerObserver(edeka2);
        shoppingCartList.registerObserver(bauhaus);

        // Change something a hope that the observer notices it
        shoppingCartList.addShoppingCarts(shoppingCart2);
        shoppingCartList.addShoppingCarts(shoppingCart5);
        shoppingCartList.addShoppingCarts(shoppingCart8);

        shoppingCart3.setLocation("Antonplatz");
        shoppingCart6.setLocation("Alexa");
        shoppingCart9.setLocation("Nöldnerplatz");
    }

    @Test
    void testEdeka1() {
        assertEquals(2, edeka1.getShoppingCarts().size());
    }

    @Test
    void testEdeka2() {
        assertEquals(2, edeka2.getShoppingCarts().size());
    }

    @Test
    void testPenny() {
        assertEquals(2, penny.getShoppingCarts().size());
    }

    @Test
    void testAldi() {
        ShoppingCart shoppingCart10 = new ShoppingCart(10, "Sewanstraße", Brands.Aldi);
        shoppingCartList.addShoppingCarts(shoppingCart10);
        assertEquals(1, aldi.getShoppingCarts().size());
    }

    @Test
    void testBauhaus() {
        assertEquals(1, bauhaus.getShoppingCarts().size());
    }

    @Test
    void testKaufhof() {
        assertEquals(1, kaufhof.getShoppingCarts().size());
    }

    @Test
    void testLidl() {
        assertEquals(1, lidl.getShoppingCarts().size());
    }

    @Test
    void testUnknown() {
        assertEquals(1, unknown.getShoppingCarts().size());
    }

}