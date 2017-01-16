package darknight98.com.danusif;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Switch;

/**
 * Created by DarKnight060198 on 1/14/2017.
 */

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MyProduct.db";
    public static final String TABLE_PRODUCT = "products";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTNAME = "productName";
    public static final String COLUMN_PRODUCTQUANTITY = "productQuantity";
    public static final String COLUMN_PRODUCTTOTALPRICE = "productTotalPrice";
    public static final String COLUMN_PRODUCTSTATUS = "productStatus";
    public static final String COLUMN_PRODUCTNPM = "productNPM";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_PRODUCT + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_PRODUCTNAME + " TEXT ," +
                COLUMN_PRODUCTQUANTITY + " INTEGER ," +
                COLUMN_PRODUCTTOTALPRICE + " FLOAT ," +
                COLUMN_PRODUCTSTATUS + " TEXT ," +
                COLUMN_PRODUCTNPM + " TEXT " +
                ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        onCreate(sqLiteDatabase);
    }

    public long addProduct(Product product, int quantity, String npm) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.getName());
        values.put(COLUMN_PRODUCTQUANTITY, quantity);
        values.put(COLUMN_PRODUCTTOTALPRICE, product.hitungHarga(quantity));
        values.put(COLUMN_PRODUCTSTATUS, "BELUM LUNAS");
        values.put(COLUMN_PRODUCTNPM, npm);
        SQLiteDatabase db = getReadableDatabase();
        long newRowID = db.insert(TABLE_PRODUCT, null, values);
        db.close();
        return newRowID;
    }

    public void updateProductStatus(int _id, String value) {
        String query = "UPDATE " + TABLE_PRODUCT + " SET " + COLUMN_PRODUCTSTATUS + "=" + value +
                " WHERE _id=" + _id;
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL(query);
        db.close();
    }

    public void read (int id) {
        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                MyDBHandler.COLUMN_ID,
                MyDBHandler.COLUMN_PRODUCTNAME,
                MyDBHandler.COLUMN_PRODUCTQUANTITY,
                MyDBHandler.COLUMN_PRODUCTTOTALPRICE,
                MyDBHandler.COLUMN_PRODUCTSTATUS,
                MyDBHandler.COLUMN_PRODUCTNPM
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = MyDBHandler.COLUMN_PRODUCTNPM + " = ?";
        String[] selectionArgs = { Integer.toString(id) };

        // How you want the results sorted in the resulting Cursor
        //String sortOrder =
                //FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(
                MyDBHandler.TABLE_PRODUCT,                // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // The sort order
        );
    }
    /**public void updateProductQuantity(int _id, int value) {
        String query = "UPDATE " + TABLE_PRODUCT + " SET " + COLUMN_PRODUCTQUANTITY + "=" + value +
                " WHERE _id=" + _id;
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL(query);
        db.close();
    }

    public void updateProductStatus(int _id, String value) {
        String query = "UPDATE " + TABLE_PRODUCT + " SET " + COLUMN_PRODUCTNAME + "=" + value +
                " WHERE _id=" + _id;
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL(query);
        db.close();
    }*/

    public String getStringValue (int id) {
        String dbString = "";
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCT + " WHERE _id=" + id;
        Cursor c = db.rawQuery(query, null);
        //c.moveToFirst();

        if (c == null) dbString = "null";
        else {
            while (c.moveToNext()) {
                dbString = c.getString(c.getColumnIndex(COLUMN_PRODUCTNAME)) + "";
            }
        }

        db.close();
        return dbString;
    }
}