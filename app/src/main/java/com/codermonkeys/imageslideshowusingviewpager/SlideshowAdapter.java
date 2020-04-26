package com.codermonkeys.imageslideshowusingviewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

public class SlideshowAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater mInflater;

    public int[] imagesRes = {

            R.drawable.fever,
            R.drawable.git,
            R.drawable.headache,
            R.drawable.sore_throat,
            R.drawable.wash_hands
    };

    public SlideshowAdapter(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return imagesRes.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        assert mInflater != null;
        View v = mInflater.inflate(R.layout.layout_slideshow, container, false);

        ImageView image = v.findViewById(R.id.image_view_id);
        //image.setImageResource(imagesRes[position]);

        Glide.with(mContext).load(imagesRes[position]).into(image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(v, "Image " +(position+1), Snackbar.LENGTH_SHORT).show();
            }
        });

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}
