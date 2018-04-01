package com.iki.android.riski_1202154118_modul6;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iki.android.riski_1202154118_modul6.model.Comment;
import com.iki.android.riski_1202154118_modul6.R;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    Context context;
    List<Comment> commentList;
    RoundedBitmapDrawable rounded;

    public CommentAdapter(Context context, ArrayList<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mUsername;
        TextView mComment;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img_avatar);
            mUsername=itemView.findViewById(R.id.tv_username);
            mComment=itemView.findViewById(R.id.tv_comment);
        }
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_comment,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Comment comment= commentList.get(position);

        holder.mUsername.setText(comment.getUsername());
        holder.mComment.setText(comment.getComment());
//        setupImageRounded();
        holder.imageView.setImageResource(R.drawable.ic_account_circle_black_24dp);

    }
//    private void setupImageRounded() {
//        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.test);
//        rounded = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
//        rounded.setCircular(true);
//
//    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }



}
