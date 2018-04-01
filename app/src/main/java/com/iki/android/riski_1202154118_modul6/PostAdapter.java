package com.iki.android.riski_1202154118_modul6;

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

import com.bumptech.glide.Glide;
import com.iki.android.riski_1202154118_modul6.model.Post;
import com.iki.android.riski_1202154118_modul6.DetailPostActivity;
import com.iki.android.riski_1202154118_modul6.R;
import com.iki.android.riski_1202154118_modul6.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    Context context;
    List<Post> postList;

    public PostAdapter(Context context, ArrayList<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mUsername;
        public TextView mTitlePost;
        public TextView mPost;
        public ImageView mImagePost;
        public CardView cardViewPost;

        public ViewHolder(View itemView) {
            super(itemView);

            mUsername= itemView.findViewById(R.id.tv_username);
            mTitlePost = itemView.findViewById(R.id.tv_title_post);
            mPost = itemView.findViewById(R.id.tv_post);
            mImagePost=itemView.findViewById(R.id.img_post);
            cardViewPost= itemView.findViewById(R.id.cardViewPost);
        }
    }
    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_post,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Post post= postList.get(position);

        holder.mUsername.setText(post.getUsername());

        Glide.with(context)
                .load(post.getImagePost())
                .into(holder.mImagePost);

        holder.mTitlePost.setText(post.getTitlePost());

        holder.mPost.setText(post.getPost());

        holder.cardViewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailPostActivity.class);
                intent.putExtra("id",post.getId());
                intent.putExtra("Username",post.getUsername());
                intent.putExtra("image",post.getImagePost());
                intent.putExtra("Title",post.getTitlePost());
                intent.putExtra("Post",post.getPost());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

}
