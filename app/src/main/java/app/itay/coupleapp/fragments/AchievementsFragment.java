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
    private TextView romanticText;
    private ImageView spontaneousImage;
    private TextView spontaneousText;
    private ImageView supportiveImage;
    private TextView supportiveText;
    private ImageView attentiveImage;
    private TextView attentiveText;
    private ImageView sexyImage;
    private TextView sexyText;
    private ImageView flatteringImage;
    private TextView flatteringText;
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
        romanticText=(TextView) v.findViewById(R.id.romantic_text);
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        romanticImage.setColorFilter(filter);
        romanticImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                romanticImage.clearColorFilter();
                romanticText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }
        });
        spontaneousImage=(ImageView) v.findViewById(R.id.img_spontaneous_gray);



        return v;
    }

}
