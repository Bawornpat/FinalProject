package com.ssru.afinal.ldlearning.adapter;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ssru.afinal.ldlearning.R;
import com.ssru.afinal.ldlearning.model.Alphabet;
import java.util.ArrayList;

/**
 * Created by namevermine on 14/12/2560.
 */

public class AlphabetAdapter extends RecyclerView.Adapter<AlphabetAdapter.MyViewHolder> {
    protected static final int RESULT_SPEECH = 1;
    private ArrayList<Alphabet> arrayList;
    private Context context;


    public AlphabetAdapter(Context context, ArrayList<Alphabet> arrayList) {
        this.context = context;
        this.arrayList = arrayList;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_character,parent,false);


        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final Alphabet alpha = arrayList.get(position);


        holder.textView.setText(alpha.getName());
        Glide.with(context).load(alpha.getPic()).into(holder.imageView);



        holder.btn_simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer playAudio = MediaPlayer.create(context,alpha.getSound());
                playAudio.start();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private ImageView checkTF;
        private TextView textView;
        private Button btn_simple;
        private MediaPlayer playAudio;
        private Dialog dialog;




        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.alphabet_pic);
            textView = itemView.findViewById(R.id.alphabet_name);
            btn_simple = itemView.findViewById(R.id.btn_simple);



        }
    }


}
