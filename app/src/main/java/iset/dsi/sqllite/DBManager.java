package iset.dsi.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DBManager {


    protected SQLiteDatabase db ;
    protected DBHelper dbHelper;

    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
    }


    public SQLiteDatabase openWrite() {

         db= dbHelper.getWritableDatabase();
         return db;
    }

    public SQLiteDatabase openRead() {
        return dbHelper.getReadableDatabase();
    }

    public void close() {

            db.close();

    }


    public void addProduct(Product c) {

        openWrite();
        ContentValues values = new ContentValues();

        //values.put(DBHelper.KEY_ID,c.getId());
        values.put(DBHelper.KEY_NAME,c.getName());
        values.put(DBHelper.KEY_QT,c.getQuantity());
try {
    long retour = db.insert(DBHelper.TABLE_PRODUCT, null, values);
    Log.i("coderetour", retour + "");
}catch (SQLException e )
{
    e.printStackTrace();
}
        close();
    }


    public List<Product> allProducts() {

        List<Product> productList=new ArrayList<>();

        String querySelect="SELECT * FROM "+DBHelper.TABLE_PRODUCT;
        db=openRead();
        Cursor cursor=db.rawQuery(querySelect,null);
//Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do{
                Product product = new Product(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
//Adding a contact to list


                productList.add(product);
            }while (cursor.moveToNext());
        }
// return contactList
        return productList;
    }




    public int update(Product product)
    {
      db=openWrite();
      ContentValues values=new ContentValues();
      values.put(DBHelper.KEY_NAME, product.getName());
      values.put(DBHelper.KEY_QT,product.getQuantity());
      values.put(DBHelper.KEY_ID,product.getId());

      return db.update(DBHelper.TABLE_PRODUCT,values,DBHelper.KEY_ID+"=?",new String[]{String.valueOf(product.getId())});
    }




    public void delete(Product p)
    {
        db=openWrite();
        db.delete(DBHelper.TABLE_PRODUCT,DBHelper.KEY_ID+"=?"
        ,new String[]{String.valueOf(p.getId())});
    }



    public Product searchProductById(int id){
        db=openRead();

        Cursor cursor=db.query(DBHelper.TABLE_PRODUCT,new String[] {
                        DBHelper.KEY_ID, DBHelper.KEY_NAME, DBHelper.KEY_QT},
                DBHelper.KEY_ID+"=?", new String[] {String.valueOf(id)}, null,null,null,null);

        if (cursor != null)
            cursor.moveToFirst();

        Product product = new Product(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));

// return contact
        return product;
    }


}