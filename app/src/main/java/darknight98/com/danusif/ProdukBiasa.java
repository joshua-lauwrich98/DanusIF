package darknight98.com.danusif;

/**
 * Created by DarKnight060198 on 1/14/2017.
 * this class is a regular product.
 */

public class ProdukBiasa extends Product {

    public ProdukBiasa () {
        super();
    }

    public ProdukBiasa (String name, double harga) {
        super(name, harga);
    }

    @Override
    public double hitungHarga(int quantity) {
        return getHarga()*quantity;
    }
}
