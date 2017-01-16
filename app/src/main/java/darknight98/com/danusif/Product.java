package darknight98.com.danusif;

/**
 * Created by DarKnight060198 on 1/14/2017.
 * this class is the parent of all product.
 */

public abstract class Product {
    private String name;
    private double harga;

    public Product () {
        this.name = this.getClass().getCanonicalName();
        this.harga = 0;
    }

    public Product (String name, double harga) {
        this.name = name;
        this.harga = harga;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public abstract double hitungHarga (int quantity);
}
