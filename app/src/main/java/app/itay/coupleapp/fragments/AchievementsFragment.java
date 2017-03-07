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

    private ImageView gray;
    private TextView grayText;
    private View v;
    public AchievementsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_achievements, container, false);
        gray=(ImageView) v.findViewById(R.id.img_gray);

grayText=(TextView) v.findViewById(R.id.gray_text);
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        gray.setColorFilter(filter);
        gray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gray.clearColorFilter();
                grayText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }
        });


        return v;
    }

}
