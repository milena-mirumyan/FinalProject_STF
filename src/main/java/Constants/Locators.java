package Constants;

public class Locators {
    //Locators of Bookstore search
    public static final String bookStore = "#main-menu-link-content7da99b2c-6359-46f3-af85-67482f9250d5 > a";
    public static final String bookStoreSearch = "#search_query";
    public static final String errorBookSearch = "#SearchTabProducts > div:nth-child(3) > p";
    public static final String searchSuccess = "#frmCompare > div.SearchContainer";


    //Locators for Sorting
    public static final String sortingOptionsList = "#SearchSortingProductList";
    public static final String searchResultPrices = ".ProductPrice";


    //Locators for Add to Card
    public static final String loading = "#AjaxLoading";
    public static final String proceedToCheckout = "[class=\"CheckoutButton\"]";
    public static final String totalPrice = "tr[class=\"SubTotal Last\"] > td > em.ProductPrice";
    public static final String shoppingCartProducts = "tbody > tr";
    public static final String shoppingCartProductName = "td[class*=\"ProductName\"] > a";
    public static final String shoppingCartProductPrice = "td[class*=\"CartItemIndividualPrice\"]";
    public static final String addToCartButton = "div.BulkDiscount > input";

    public static final String itemImage = "div[class=\"ProductImage QuickView\"] > a";
    public static final String itemPrice = "[itemprop=\"price\"]";
    public static final String itemName = "[itemprop=\"name\"]";

    //Locators for changing item quantity
    public static final String quantityDropdown = "[class*=\"quantityInput\"]";
    public static final String noProductsInCart = "#CartContent > div > div:nth-child(3) > p";
    public static final String editCart = "#cart-edit-link";

}
