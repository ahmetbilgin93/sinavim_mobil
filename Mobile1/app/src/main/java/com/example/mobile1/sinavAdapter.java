package com.example.mobile1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class sinavAdapter extends RecyclerView.Adapter<sinavAdapter.ViewHolder> {

    ArrayList<sorular> soruListesi;
    Context context;
    private onItemClickListener listener;

    public sinavAdapter(ArrayList<sorular> soruListesi, Context context) {
        this.soruListesi = soruListesi;
        this.context = context;
    }

    @NonNull
    @Override
    public sinavAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.sinavrows,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.soruView.setText(soruListesi.get(position).getSoru() );
        holder.cevapView.setText(soruListesi.get(position).getCevap() );
    }

    @Override
    public int getItemCount() {
        return soruListesi.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView soruView, cevapView, idView;
        Button ekBtn;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            soruView = itemView.findViewById(R.id.sorugeldi2);
            cevapView = itemView.findViewById(R.id.dogrucevap2);
            idView = itemView.findViewById(R.id.soruid2);
            ekBtn = itemView.findViewById(R.id.ekle);

            ekBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener!=null && position !=RecyclerView.NO_POSITION)
                        listener.onItemClick(soruListesi.get(position));


                }

            });

        }
    }

    public interface onItemClickListener{
        void onItemClick(sorular soru);

    }




    public void setOnItemClickListener(onItemClickListener listener){
        this.listener=listener;

    }


}
