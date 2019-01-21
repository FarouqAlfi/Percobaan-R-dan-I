package com.example.win10.percobaan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.win10.percobaan.MyApp.MyAppController;
import com.example.win10.percobaan.Table.DaoSession;
import com.example.win10.percobaan.Table.Padi;
import com.example.win10.percobaan.Table.PadiDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView listView;
    List<Padi> groceries = new ArrayList<>();
    MyAdapter adapter;

    DaoSession daoSession;
    ArrayAdapter<Padi> groceryArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (RecyclerView) findViewById(R.id.listUser);
        adapter=new MyAdapter(groceries,this);

        daoSession = ((MyAppController) getApplication()).getDaoSession();
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setHasFixedSize(true);
        listView.setItemAnimator(new DefaultItemAnimator());
        listView.setAdapter(adapter);

    }
    @Override
    protected void onResume() {
        super.onResume();

        fetchGroceryList();
    }




    private void showOptions(int position) {
        final Padi selectedGroceryItem = groceries.get(position);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        String[] options = new String[2];

        options[0] = "Edit " + selectedGroceryItem.getPemilik();
        options[1] = "Delete " + selectedGroceryItem.getPemilik();

        alertDialogBuilder.setItems(options,
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    proceedToUpdateItem(selectedGroceryItem);
                }else if(which == 1){
                    deleteGroceryItem(selectedGroceryItem.getId());
                }

                dialog.dismiss();
            }
        });
        alertDialogBuilder.create().show();
    }


    private void fetchGroceryList(){
        groceries.clear();
        // Get the entity dao we need to work with.
        PadiDao groceryDao = daoSession.getPadiDao();

        // Load all items
        groceries.addAll(groceryDao.loadAll());

        // Notify our adapter of changes
        adapter.notifyDataSetChanged();
    }


    private void deleteGroceryItem(long id){
        // Get the entity dao we need to work with.
        PadiDao groceryDao = daoSession.getPadiDao();
        // perform delete operation
        groceryDao.deleteByKey(id);

        fetchGroceryList();
    }


    private void proceedToUpdateItem(Padi grocery){
        // Pass grocery id to the next screen
        Intent intent = new Intent(this,Input_Activity.class);
        intent.putExtra("create",false);
        intent.putExtra("padi", (Serializable) grocery);
        startActivity(intent);
    }

    public void addNewItem(View view) {
        // Go to add item activity
        Intent intent = new Intent(this,Input_Activity.class);
        intent.putExtra("create",true);
        startActivity(intent);
    }

}
