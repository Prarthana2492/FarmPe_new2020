package com.SevenNine.essentialscode.Adapter;

import android.app.Activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.SevenNine.essentialscode.Bean.Image;
import com.SevenNine.essentialscode.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by user on 21-02-2018.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    private List<Image> moviesList;
    Activity activity;



    public class MyViewHolder extends RecyclerView.ViewHolder {
       // public TextView title, year, genre;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageView);

        }
    }


    public ImageAdapter(Activity activity, List<Image> moviesList) {
        this.moviesList = moviesList;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Image movie = moviesList.get(position);

       // System.out.println("lllllllllllllllllllllkkk"+movie.getTitle());
        //holder.imageView.setImageResource(R.drawable.ic_photo_camera);
        Glide.with(activity).load("file://" + movie.getTitle())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE)
                .skipMemoryCache(true))
                .into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



               /* Context c = view.getContext();


                Bundle bundle = new Bundle();
                bundle.putString("name", movie.getTitle() );
                Fragment fragment = new UploadFragment();
                fragment.setArguments(bundle);
                FragmentManager fm = ((AppCompatActivity)c).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frame_layout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();*/
               // System.out.println("abc1111hhhhhhhhh"+destination.getPath());


            }
        });

      /*  Glide.with(activity).load("file://" + movie.getTitle())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                System.out.println("Renewin");
                Context c = view.getContext();
                Intent intent=new Intent(c,Main2Activity.class);
                Bundle bundle = new Bundle();

                bundle.putString("name",movie.getTitle());
                intent.putExtras(bundle);
                c.startActivity(intent);


            }
        });
*/



      /*  holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context c = v.getContext();
                Intent intent = new Intent(c, BodyPartExcercise.class);
                c.startActivity(intent);
            }
        });*/
       /* holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("renewin");
                Context c = v.getContext();
                Intent intent = new Intent(c, BodyPartExcercise.class);
                c.startActivity(intent);
               *//* Intent intent=new Intent(ImageAdapter.this,Main2Activity.class);

                //bundle.putString("des",GetDataAdapter1.get(position).des);
                Bundle bundle = new Bundle();

                bundle.putString("name",);
                System.out.println("abc"+bundle);
                intent.putExtras(bundle);
                System.out.println("abc1111"+intent);

                startActivity(intent);*//*
            }
        });*/


      /*  holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
        holder.year.setText(movie.getYear());*/
    }
  /*  @Override
    public void onClick(View view) {
       System.out.println("abcddd");
        Context c = view.getContext();

      *//*  Intent intent=new Intent(c,Main2Activity.class);
        Bundle bundle=new Bundle();
        bundle.putString("name");
        startActivity(intent);
*//*
        //
        // intent.putExtra("message", al_images);
      *//*  Bundle bundle = new Bundle();
        //bundle.putString("des",GetDataAdapter1.get(position).des);
        bundle.putString("name",);
        System.out.println("abc"+bundle);
        intent.putExtras(bundle);
        System.out.println("abc1111"+intent);

        *//*
    }*/


    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
