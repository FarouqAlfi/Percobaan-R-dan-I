package com.example.win10.percobaan;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView pemilik,tgl_tanam;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        pemilik=itemView.findViewById(R.id.item_name);
        tgl_tanam = itemView.findViewById(R.id.item_address);
    }

    public TextView getPemilik() {
        return pemilik;
    }

    public TextView getTgl_tanam(){
        return tgl_tanam;
    }
}

