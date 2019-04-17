package com.maple.paginateexample.like;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.maple.paginateexample.R;
import com.maple.paginateexample.utils.LogUtils;
import com.maple.paginateexample.widget.like.LikeButton;
import com.maple.paginateexample.widget.like.OnAnimationEndListener;
import com.maple.paginateexample.widget.like.OnLikeListener;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

public class LikeActivity extends AppCompatActivity implements OnLikeListener,
        OnAnimationEndListener {

    private LikeButton starButton,likeButton,thumbButton,smileButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);


        findViewById(R.id.button).setOnClickListener(v -> startActivity(new Intent(LikeActivity.this,ListActivity.class)));
        starButton = findViewById(R.id.star_button);
        starButton.setOnAnimationEndListener(this);
        starButton.setOnLikeListener(this);

        likeButton = findViewById(R.id.heart_button);
        likeButton.setOnAnimationEndListener(this);
        likeButton.setOnLikeListener(this);

        thumbButton = findViewById(R.id.thumb_button);
        thumbButton.setOnAnimationEndListener(this);
        thumbButton.setOnLikeListener(this);

        smileButton = findViewById(R.id.smile_button);
        smileButton.setOnAnimationEndListener(this);
        smileButton.setOnLikeListener(this);

        thumbButton.setLiked(true);

        usingCustomIcons();

    }
    public void usingCustomIcons() {

        //shown when the button is in its default state or when unLiked.
        smileButton.setUnlikeDrawable(new BitmapDrawable(getResources(), new IconicsDrawable(this, CommunityMaterial.Icon.cmd_emoticon).colorRes(android.R.color.darker_gray).sizeDp(25).toBitmap()));

        //shown when the button is liked!
        smileButton.setLikeDrawable(new BitmapDrawable(getResources(), new IconicsDrawable(this, CommunityMaterial.Icon.cmd_emoticon).colorRes(android.R.color.holo_purple).sizeDp(25).toBitmap()));
    }


    @Override
    public void onAnimationEnd(LikeButton likeButton) {
        LogUtils.logGGQ("Animation End for %s" + likeButton);
    }

    @Override
    public void liked(LikeButton likeButton) {
        Toast.makeText(this, "Liked!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void unLiked(LikeButton likeButton) {
        Toast.makeText(this, "Disliked!", Toast.LENGTH_SHORT).show();
    }
}
