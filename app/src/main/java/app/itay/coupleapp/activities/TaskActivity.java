package app.itay.coupleapp.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import app.itay.coupleapp.Constants;
import app.itay.coupleapp.R;
import app.itay.coupleapp.controllers.MainMenuController;
import app.itay.coupleapp.models.Chore;

public class TaskActivity extends AppCompatActivity implements MainMenuController {

    private static final int SELECT_PICTURE = 1;
    private Uri mSelectedImagePath;
    private ImageView mTaskImage;
    private Calendar mCalendar = Calendar.getInstance();

    private Button mDeadlineButton;

    private Bitmap bmp;

    @Override
    protected void onStart() {
        super.onStart();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    InputStream in = null;
                    switch (getIntent().getStringExtra(Constants.TAG)) {
                        case Constants.TAG_CREATE_CHORE:
                            in = new URL("http://www.clipartbest.com/cliparts/4T9/zBp/4T9zBpp8c.png").openStream();
                            break;
                        case Constants.TAG_CREATE_GOAL:
                            in = new URL("https://cdn1.iconfinder.com/data/icons/multimedia-marketing/512/8-512.png").openStream();
                            break;
                        case Constants.TAG_CREATE_REWARD:
                            in = new URL("https://cdn1.iconfinder.com/data/icons/the-competition/450/trophy-512.png").openStream();
                            break;
                    }
                    bmp = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                if (bmp != null)
                    mTaskImage.setImageBitmap(bmp);
                mTaskImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }

        }.execute();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ((EditText) findViewById(R.id.edit_title)).setHint(getIntent().getStringExtra(Constants.TITLE));

        mTaskImage = (ImageView) findViewById(R.id.img_task_picture);
        findViewById(R.id.button_open_gallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);
            }
        });

        findViewById(R.id.button_web_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWebSearchActivity((Chore) getIntent().getSerializableExtra(Constants.CHORE));
            }
        });

        switch (getIntent().getStringExtra(Constants.TAG)) {
            case Constants.TAG_CREATE_CHORE:
                findViewById(R.id.layout_reward_chore).setVisibility(View.VISIBLE);
                findViewById(R.id.layout_deadline).setVisibility(View.VISIBLE);
                findViewById(R.id.layout_reward_goal).setVisibility(View.GONE);
                break;
            case Constants.TAG_CREATE_GOAL:
                findViewById(R.id.layout_reward_chore).setVisibility(View.GONE);
                findViewById(R.id.layout_deadline).setVisibility(View.GONE);
                findViewById(R.id.layout_reward_goal).setVisibility(View.VISIBLE);
                break;
            case Constants.TAG_CREATE_REWARD:
                findViewById(R.id.layout_reward_chore).setVisibility(View.GONE);
                findViewById(R.id.layout_deadline).setVisibility(View.GONE);
                findViewById(R.id.layout_reward_goal).setVisibility(View.VISIBLE);
                break;
            case Constants.TAG_EDIT_CHORE:
                Chore chore = (Chore) getIntent().getSerializableExtra(Constants.CHORE);
                ((EditText) findViewById(R.id.edit_title)).setHint(chore.getTitle());
                findViewById(R.id.text_spinner_reward).setVisibility(View.VISIBLE);
                findViewById(R.id.spinner_reward).setVisibility(View.GONE);
                ((TextView) findViewById(R.id.text_spinner_reward)).setText(chore.getCoins());
                findViewById(R.id.text_spinner_reward).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        findViewById(R.id.text_spinner_reward).setVisibility(View.GONE);
                        findViewById(R.id.spinner_reward).setVisibility(View.VISIBLE);
                    }
                });
                if (chore.getDeadline() != null && !chore.getDeadline().equals("")) {
                    ((TextView) findViewById(R.id.button_deadline)).setText(chore.getDeadline());
                } else {
                    ((TextView) findViewById(R.id.button_deadline)).setText(getString(R.string.set_deadline));
                }
                if (chore.getImgSrc() != 0) {
                    mTaskImage.setImageResource(chore.getImgSrc());
                } else if (chore.getImgPath() != null) {
                    mTaskImage.setImageURI(Uri.parse(chore.getImgPath()));
                }
                findViewById(R.id.layout_reward_chore).setVisibility(View.VISIBLE);
                findViewById(R.id.layout_deadline).setVisibility(View.VISIBLE);
                findViewById(R.id.layout_reward_goal).setVisibility(View.GONE);
                break;
            case Constants.TAG_EDIT_GOAL:
                findViewById(R.id.layout_reward_chore).setVisibility(View.GONE);
                findViewById(R.id.layout_deadline).setVisibility(View.GONE);
                findViewById(R.id.layout_reward_goal).setVisibility(View.VISIBLE);
                break;
            case Constants.TAG_EDIT_REWARD:
                findViewById(R.id.layout_reward_chore).setVisibility(View.GONE);
                findViewById(R.id.layout_deadline).setVisibility(View.GONE);
                findViewById(R.id.layout_reward_goal).setVisibility(View.VISIBLE);
                break;
        }

        mDeadlineButton = (Button) findViewById(R.id.button_deadline);
        if (mDeadlineButton.getVisibility() == View.VISIBLE) {

            mDeadlineButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    new DatePickerDialog(TaskActivity.this, dateListenr, mCalendar
                            .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                            mCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(getIntent().getIntExtra("menu", R.menu.menu_task_card), menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.save:
                String coins = "0";
                if (Constants.TAG.equals(Constants.TAG_CREATE_CHORE)) {
                    coins = ((Spinner) findViewById(R.id.spinner_reward)).getSelectedItem().toString();
                }
                if (Constants.TAG.equals(Constants.TAG_CREATE_GOAL) || Constants.TAG.equals(Constants.TAG_CREATE_REWARD)) {
                    coins = ((EditText) findViewById(R.id.edit_cost)).getText().toString();
                }
                if (Constants.TAG.equals(Constants.TAG_EDIT_CHORE)) {
                    Toast.makeText(this, "Task updated", Toast.LENGTH_SHORT).show();
                }
                String user = getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE).getString(Constants.CURRENT_USER, "");
                String title = ((EditText) findViewById(R.id.edit_title)).getText().toString();
                String deadline = ((Button) findViewById(R.id.button_deadline)).getText().toString();
                if (title.equals("")) {
                    ((EditText) findViewById(R.id.edit_title)).setError("This field is required");
                } else if (mSelectedImagePath != null) {
                    startMainActivityChoreAdded(new Chore(title, coins, user, deadline, mSelectedImagePath.toString()));
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                if (resultCode == RESULT_CANCELED) {
                    // action cancelled
                } else if (resultCode == RESULT_OK) {
                    mSelectedImagePath = data.getData();
                    ((ImageView) findViewById(R.id.img_task_picture)).setImageURI(mSelectedImagePath);
                }
            }
        }
    }

    DatePickerDialog.OnDateSetListener dateListenr = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            mCalendar.set(Calendar.YEAR, year);
            mCalendar.set(Calendar.MONTH, monthOfYear);
            mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            new TimePickerDialog(TaskActivity.this, timeListenr, mCalendar
                    .get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE), true).show();
        }
    };

    TimePickerDialog.OnTimeSetListener timeListenr = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            mCalendar.set(Calendar.MINUTE, minute);
            updateLabel();
        }
    };

    private void updateLabel() {

        String myFormat = "MM/dd/yy hh:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

        mDeadlineButton.setText(sdf.format(mCalendar.getTime()));
    }

    @Override
    public void startMainActivityChoreAdded(Chore chore) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Constants.NEW_CHORE, chore);
        startActivity(intent);
    }

    @Override
    public void startWebSearchActivity(Chore chore) {
        Intent intent = new Intent(this, WebSearchActivity.class);
        intent.putExtra(Constants.CHORE, chore);
        startActivity(intent);
    }

}
