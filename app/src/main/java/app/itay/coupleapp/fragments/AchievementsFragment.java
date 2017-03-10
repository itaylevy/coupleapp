package app.itay.coupleapp.fragments;


import android.graphics.Bitmap;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.itay.coupleapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AchievementsFragment extends Fragment {

    private ImageView romanticImage;
    private ImageView spontaneousImage;
    private ImageView supportiveImage;
    private ImageView attentiveImage;
    private ImageView sexyImage;
    private ImageView flatteringImage;
    private View v;
    public AchievementsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_achievements, container, false);
        romanticImage=(ImageView) v.findViewById(R.id.img_romantic_gray);
        spontaneousImage=(ImageView) v.findViewById(R.id.img_spontaneous_gray);
        sexyImage=(ImageView) v.findViewById(R.id.img_sexy_gray);
        supportiveImage=(ImageView) v.findViewById(R.id.img_supportive_gray);
        flatteringImage=(ImageView) v.findViewById(R.id.img_flattering_gray);
        attentiveImage=(ImageView)v.findViewById(R.id.img_attentive_gray);

        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        romanticImage.setColorFilter(filter);
        spontaneousImage.setColorFilter(filter);
        attentiveImage.setColorFilter(filter);
        sexyImage.setColorFilter(filter);
        flatteringImage.setColorFilter(filter);
        supportiveImage.setColorFilter(filter);

        romanticImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                romanticImage.clearColorFilter();
            }
        });
        spontaneousImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spontaneousImage.clearColorFilter();
            }
        });
        supportiveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supportiveImage.clearColorFilter();
            }
        });
        sexyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sexyImage.clearColorFilter();
            }
        });
        flatteringImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flatteringImage.clearColorFilter();
            }
        });
        attentiveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attentiveImage.clearColorFilter();
            }
        });


        return v;
    }

}
