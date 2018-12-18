package com.example.riken.etic.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.riken.etic.R;
import com.example.riken.etic.activity.DetailFilmActivity;
import com.example.riken.etic.models.DataItem;
import com.example.riken.etic.models.Film;

import java.util.List;

public class FilmTabLayoutAdapter extends RecyclerView.Adapter<FilmTabLayoutAdapter.MyViewHolder> {

    private Context context;
    private List<Film> films ;
    private List<DataItem> dataItems;

    public FilmTabLayoutAdapter(Context context, List<DataItem> dataItems) {
        this.context = context;
        this.dataItems = dataItems;
    }

    @NonNull
    @Override
    public FilmTabLayoutAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.layout_tab_selengkapnya, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmTabLayoutAdapter.MyViewHolder myViewHolder, final int position) {
        myViewHolder.iv_film_bg.setImageResource(films.get(position).getThumbnails());
        myViewHolder.tv_film_genre.setText(films.get(position).getGenre());
        myViewHolder.tv_film.setText(films.get(position).getTitle());
        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, DetailFilmActivity.class);
                //parsing data to detail
                intent.putExtra("Title", films.get(position).getTitle());
                intent.putExtra("Genre", films.get(position).getGenre());
                intent.putExtra("Duration", films.get(position).getDuration());
//                intent.putExtra("Sutradara", films.get(position).getSutradara());
                intent.putExtra("Thumbnail", films.get(position).getThumbnails());
                intent.putExtra("Judul", films.get(position).getJudulTab());
                context.startActivity(intent);

            }
        });

//        Intent intent = new Intent(context, TestView.class);
//        intent.p


    }


    @Override
    public int getItemCount() {
        return films.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_film_bg;
        TextView tv_film;
        TextView tv_duration;
        TextView tv_film_genre;
        CardView cardView;


        public MyViewHolder(View itemView) {
            super(itemView);

            iv_film_bg = itemView.findViewById(R.id.iv_detail_film_gambar_tab);
            tv_film = itemView.findViewById(R.id.tv_detail_film_title_tab);
            tv_film_genre = itemView.findViewById(R.id.tv_detail_film_genre_tab);
            tv_duration = itemView.findViewById(R.id.tv_detail_film_duration_tab);
            cardView = itemView.findViewById(R.id.cv_layout_sedang);


        }
    }
}

