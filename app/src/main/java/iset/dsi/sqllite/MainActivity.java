package iset.dsi.sqllite;

import android.icu.text.SymbolTable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    EditText editText_id,editText_nameP,editText_quantityP;
    private ListView listView;
    private ArrayList<Product> productList;
    private ArrayAdapter<Product> listAdapter;

    DBManager dbProducts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_id=(EditText) findViewById(R.id.editText_id);
        editText_nameP=(EditText) findViewById(R.id.editText_nameP);
        editText_quantityP=(EditText) findViewById(R.id.editText_quantityP);
        listView = findViewById(R.id.listView);
        dbProducts=new DBManager(this);
        productList = new ArrayList<>();
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productList);
        listView.setAdapter(listAdapter);
    }




    public void add_products(View view)
    {
        Product p=new Product(editText_nameP.getText().toString(), Integer.parseInt(editText_quantityP.getText().toString()));
             dbProducts.addProduct(p);

        Toast.makeText(getApplicationContext(),"product added",Toast.LENGTH_SHORT).show();
       allProducts(view);
        editText_id.getText().clear();
        editText_nameP.getText().clear();
        editText_quantityP.getText().clear();

    }


    public void allProducts(View v)
    {
        productList.clear();
System.out.println("le data est "+dbProducts.allProducts().toString());
        productList.addAll(dbProducts.allProducts());

        listAdapter.notifyDataSetChanged();

    }


    public void updateProduct(View view) {


        String id = editText_id.getText().toString();
        String name = editText_nameP.getText().toString();
        String qt = editText_quantityP.getText().toString();

            Product updatedProduct = new Product( Integer.parseInt(id),name, Integer.parseInt(qt));
            int rowsAffected = dbProducts.update(updatedProduct);

            if (rowsAffected > 0) {


                editText_id.getText().clear();
                editText_nameP.getText().clear();
                editText_quantityP.getText().clear();
                productList.clear();
                productList.addAll(dbProducts.allProducts());

                listAdapter.notifyDataSetChanged();

            }

    }



    public void deleteProduct(View view) {
        String productIdStr = editText_id.getText().toString();

        if (!productIdStr.isEmpty()) {
            Integer productId = Integer.parseInt(productIdStr);
           Product p= dbProducts.searchProductById(productId);
            System.out.println("le product qui on va supprimer est "+p.toString());
            dbProducts.delete(p);
            editText_id.getText().clear();
            allProducts(view);


        }
    }




    public void rechercherProduit(View view) {
        String productIdStr = editText_id.getText().toString();

        if (!productIdStr.isEmpty()) {
            Integer productId = Integer.parseInt(productIdStr);
            Product p= dbProducts.searchProductById(productId);

            editText_id.getText().clear();
            productList.clear();
            productList.add(p);
            listAdapter.notifyDataSetChanged();


        }
    }






}
