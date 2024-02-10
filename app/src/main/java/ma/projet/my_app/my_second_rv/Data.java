package ma.projet.my_app.my_second_rv;

public class Data {
    int img;
    String name_of_product;
    String price;
    public Data (int img, String name_of_product, String price){
        this.img = img;
        this.name_of_product = name_of_product;
        this.price = price;
    }
    public int getImg(){ return img;}
    public String  getPrice(){ return price;}
    public String getName_of_product(){ return name_of_product;}
}
