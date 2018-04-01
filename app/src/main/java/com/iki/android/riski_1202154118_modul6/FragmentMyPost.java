package com.iki.android.riski_1202154118_modul6;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iki.android.riski_1202154118_modul6.MainActivity;
import com.iki.android.riski_1202154118_modul6.R;
import com.iki.android.riski_1202154118_modul6.PostAdapter;
import com.iki.android.riski_1202154118_modul6.model.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FragmentMyPost extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private ArrayList<Post> listPosts;

    Query databaseFood;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_post, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        databaseFood = FirebaseDatabase.getInstance().getReference(MainActivity.table1).orderByChild("userID").equalTo(mAuth.getUid());

        listPosts = new ArrayList<>();

        return view;
    }

    public void onStart() {
        super.onStart();
        databaseFood.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listPosts.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Post post = postSnapshot.getValue(Post.class);
                    listPosts.add(post);
                }

                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

                PostAdapter postList = new PostAdapter(getContext(), listPosts);

                recyclerView.setAdapter(postList);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
