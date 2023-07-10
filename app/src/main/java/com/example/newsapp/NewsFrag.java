package com.example.newsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsFrag extends Fragment {

    public NewsFrag() {
        // Required empty public constructor
    }

    String title,desc,content,imageURL,url;
    TextView heading,description,maindesc;
    ImageView fragImage;
    ImageView frag;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_news,container,false);
        heading= rootView.findViewById(R.id.tvhead);
        description= rootView.findViewById(R.id.tvtitle);
        maindesc= rootView.findViewById(R.id.tvsub);
        frag= rootView.findViewById(R.id.fragIV);

        Bundle bundle = this.getArguments();
        title = bundle.getString("title");
        desc = bundle.getString("desc");
        content = bundle.getString("content");
        imageURL = bundle.getString("image");
        url = bundle.getString("url");
        Picasso.get().load(url).into(frag);
        heading.setText(title);
        description.setText(desc);
        maindesc.setText(content);
        return rootView;

    }
}