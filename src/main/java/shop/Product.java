package shop;

public class Product
{
    /*
        Содержит необходимые данные о каждом товаре,
        и методы для работы с ними

    * */
    public int quantity; // количество товара
    public double sum,discount; // сумма товара(цена одной единицы) и скидка(в процентах)

    public Product(int quantity, double sum, double discount) {
        this.quantity = quantity;
        this.sum = sum;
        this.discount = discount;
    }

    public Double getDiscountSum() //Возвращает посчитанную цену товара со скидкой округленную до 2х знаков после запятой
    {
        return (sum * quantity) - ((sum * quantity)/100) * discount;
    }



}
