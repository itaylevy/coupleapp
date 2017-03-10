package app.itay.coupleapp.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import app.itay.coupleapp.Constants;
import app.itay.coupleapp.R;
import app.itay.coupleapp.controllers.ChoresController;
import app.itay.coupleapp.models.Chore;
import app.itay.coupleapp.models.Goal;
import app.itay.coupleapp.models.WebPicture;

import static android.R.attr.path;

public class WebSearchActivity extends AppCompatActivity implements ChoresController {

    private List<WebPicture> mWebPictures = new ArrayList<>();
    private JSONObject jobj = null;
    private List<Bitmap> bmps = new ArrayList<>();
    private Chore mChore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_search);
        mChore = (Chore) getIntent().getSerializableExtra(Constants.CHORE);


        ((EditText) findViewById(R.id.edit_search)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final CharSequence sequence = s;
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            InputStream is = new URL("http://pixabay.com/api/?key=4778783-e1c80c2b9f0ac618dbbc71a9a&q=" + sequence).openStream();
                            String json = "";
                            mWebPictures.clear();
                            bmps.clear();
                            try {
                                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                                StringBuilder sb = new StringBuilder();
                                String line = null;
                                try {
                                    while ((line = reader.readLine()) != null) {
                                        sb.append(line + "\n");
//
                                    }
                                    is.close();
                                    json = sb.toString();
//
//                                    try {
                                    jobj = new JSONObject(json);
                                    JSONArray array = jobj.getJSONArray("hits");
                                    for (int i = 0; i < array.length(); i++) {
                                        JSONObject o = array.optJSONObject(i);
                                        mWebPictures.add(new WebPicture(o.getString("webformatURL"), o.getString("previewURL")));
                                    }

                                    try {
                                        for (int i = 0; i < mWebPictures.size(); i++) {
                                            bmps.add(BitmapFactory.decodeStream(new URL(mWebPictures.get(i).getPreviewURL()).openStream()));
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                } catch (JSONException e) {
//                                        // TODO Auto-generated catch block
//                                        e.printStackTrace();
//                                    }
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
//
                            } catch (UnsupportedEncodingException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        GridLayout gridLayout = (GridLayout) findViewById(R.id.layout_search);

                        gridLayout.removeAllViews();

                        for (int i = 0; i < bmps.size(); i++ ) {
                            final int index = i;
                            ImageView view = new ImageView(getApplicationContext());
                            view.setImageBitmap(bmps.get(i));
                            view.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    try {
                                        FileOutputStream fOut=new FileOutputStream(getFilesDir() + "/temp.png");
                                        // Here path is either sdcard or internal storage
                                        bmps.get(index).compress(Bitmap.CompressFormat.JPEG,100,fOut);
                                        fOut.flush();
                                        fOut.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    mChore.setImgPath(getFilesDir() + "/temp.png");
                                    bmps.get(index).recycle();
                                    mChore.setImgSrc(0);
                                    startTaskActivityChoreEdit(mChore);
                                }
                            });

                            gridLayout.addView(view);
                        }

                    }

                }.execute();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void startTaskActivityChoreCreate() {

    }

    @Override
    public void startTaskActivityChoreEdit(Chore chore) {
        Intent intent = new Intent(this, TaskActivity.class);
        intent.putExtra(Constants.TAG, Constants.TAG_EDIT_CHORE);
        intent.putExtra(Constants.CHORE, chore);
        intent.putExtra("menu", R.menu.menu_edit_task);
        startActivity(intent);
    }

    @Override
    public void startTaskActivityCreateReward() {

    }

    @Override
    public void startTaskActivityEditReward(String taskName) {

    }

    @Override
    public void startTaskActivityCreateGoal() {

    }

    @Override
    public void startTaskActivityEditGoal(Goal goal) {

    }

    @Override
    public Chore getNewChore() {
        return null;
    }

    @Override
    public void updateCoinsStatus(String coins) {

    }
}
