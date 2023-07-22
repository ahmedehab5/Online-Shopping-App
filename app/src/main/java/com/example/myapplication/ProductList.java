package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductList extends Fragment {
    RecyclerView poprec;
    ArrayList<Products>list;
    ProductListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.product_list_activity,container,false);
        poprec = root.findViewById(R.id.poprec);

        //popular products
        poprec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        list = new ArrayList<>();
        adapter = new ProductListAdapter(getActivity(),list);
        poprec.setAdapter(adapter);


        //Get Data from SQLite:
        Cursor cursor = Seller.sqLiteHelper.getData("SELECT * FROM products");
        list.clear();
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            list.add(new Products(id, image, price, name));
        }
        adapter.notifyDataSetChanged();
        return root;
    }
}
