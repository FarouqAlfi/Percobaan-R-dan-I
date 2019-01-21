package com.example.win10.percobaan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.win10.percobaan.MyApp.MyAppController;
import com.example.win10.percobaan.Table.DaoSession;
import com.example.win10.percobaan.Table.Padi;
import com.example.win10.percobaan.Table.PadiDao;


public class Input_Activity extends AppCompatActivity {

    Padi padi;
    DaoSession daoSession;

    Button kedatabase;
    EditText
            luaslahanpadi,tgl_tanam,tgl_siappanen,hasilpanen,pemilikpadi,nik,j_pekerja;

    boolean createNew = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_);

        kedatabase	= (Button) 	findViewById(R.id.koktolol);

        luaslahanpadi		=	findViewById	(R.id.luaslahan);

        tgl_tanam		=	findViewById	(R.id.tgl_tanama);

        tgl_siappanen	=	findViewById	(R.id.tgl_siap);

        hasilpanen		=	findViewById	(R.id.hasil_panen);

        pemilikpadi		=	findViewById	(R.id.nama_pemilik);

        nik				=	findViewById	(R.id.nik);

        j_pekerja		=	findViewById	(R.id.jml_tenaga);

        daoSession = ((MyAppController) getApplication()).getDaoSession();

        handleIntent(getIntent());

        setClickEventListener();
    }


    private void handleIntent(Intent intent) {
        createNew = intent.getBooleanExtra("create",false);

        //// This means we are editing a grocery item
        if(!createNew){
            padi = (Padi) intent.getSerializableExtra("padi");
            luaslahanpadi.setText(padi.getLuaslahan());
            tgl_tanam.setText(padi.getTgl_tanam());
            tgl_siappanen.setText(padi.getTgl_siappanen());
            hasilpanen.setText(padi.getHasilpanen());
            pemilikpadi.setText(padi.getPemilik());
            nik.setText(padi.getNik());
            j_pekerja.setText(padi.getJ_pekerja());
        }
    }


    private void setClickEventListener() {
        kedatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(createNew){
                    insetItem();
                }else{
                    updateItem(padi.getId());
                }
            }
        });
    }


    private void updateItem(long id){
        PadiDao padi = daoSession.getPadiDao();
        Padi grocery = new Padi();
        grocery.setId(id);
        grocery.setPemilik(pemilikpadi.getText().toString());
        //grocery.setQuantity(Integer.parseInt(quantity.getText().toString()));
        grocery.setLuaslahan(Integer.parseInt(luaslahanpadi.getText().toString()));
        padi.saveInTx(grocery);
        Toast.makeText(this, "Item updated", Toast.LENGTH_SHORT).show();
        finish();
    }


    private void insetItem(){
        PadiDao padi2 = daoSession.getPadiDao();
        Padi padi = new Padi();
        padi.setId();
        padi.setLuaslahan((Integer.parseInt(luaslahanpadi.getText().toString())));
        padi.setTgl_tanam(tgl_tanam.getText().toString());
        padi.setTgl_siappanen(tgl_siappanen.getText().toString());
        padi.setHasilpanen(hasilpanen.getText().toString());
        padi.setPemilik(pemilikpadi.getText().toString());
        padi.setNik(Integer.parseInt(nik.getText().toString()));
        padi.setJ_pekerja(Integer.parseInt(j_pekerja.getText().toString()));
        padi2.insert(padi);
        Toast.makeText(this, "Item inserted", Toast.LENGTH_SHORT).show();
        balik();
    }
    public void balik(){
        Intent back = new Intent(Input_Activity.this, MainActivity.class);
        startActivity(back);
    }

}
