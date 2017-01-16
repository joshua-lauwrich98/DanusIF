package darknight98.com.danusif;

/**
 * Created by DarKnight060198 on 1/14/2017.
 * this class is a special product
 */

public class ProdukSpesial extends Product implements Discount {
    private double diskon;

    public ProdukSpesial () {
        super();
    }

    public ProdukSpesial (String name, double harga, double diskon) {
        super(name, harga);
        this.diskon = diskon;
    }

    @Override
    public double hitungHargaFinal() {
        return getHarga()-this.diskon;
    }

    @Override
    public double hitungHarga(int quantity) {
        return hitungHargaFinal()*quantity;
    }
}
