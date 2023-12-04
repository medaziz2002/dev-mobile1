package iset.dsi.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION=1;

    public static final String DATABASE_NAME="products_DB";

  public static final String TABLE_PRODUCT="PRODUCT";



      public static final String KEY_ID="ID";
      public static final String KEY_NAME="Name";
      public static  final String KEY_QT="QT";



    public DBHelper(Context context) {
//Constructor
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCT_TABLE=
                "CREATE TABLE "+TABLE_PRODUCT+"("
                +KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +KEY_NAME+" TEXT, "
                +KEY_QT+" INTEGER "+")";
        db.execSQL(CREATE_PRODUCT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PRODUCT);
        onCreate(db);


    }
}
