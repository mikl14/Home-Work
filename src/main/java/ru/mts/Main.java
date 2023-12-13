package ru.mts;
import shop.Product;

public class Main {

    public static void printSumOfPurchase(Product product)
    {
        System.out.println("Сумма покупки без скидки: " + product.sum * product.quantity + " Со скидкой: " + String.format("%.2f", product.getDiscountSum()));
    }
    public static void main(String[] args) {

        Product Bread = new Product(10,25,0.75),
                Water = new Product(5,30,42.575),
                Butter = new Product(25,100,59.1);

        printSumOfPurchase(Bread);
        printSumOfPurchase(Water);
        printSumOfPurchase(Butter);
    }
}