package app.itay.coupleapp.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import app.itay.coupleapp.Constants;
import app.itay.coupleapp.R;

public class TaskActivity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 1;
    private String mSelectedImagePath;
    private ImageView mTaskImage;
    private Calendar mCalendar = Calendar.getInstance();

    private Button mDeadlineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((EditText)findViewById(R.id.edit_title)).setHint(getIntent().getStringExtra(Constants.TITLE));

        ((EditText)findViewById(R.id.edit_title)).setHint(getString(R.string.new_chore_title));

        mTaskImage = (ImageView) findViewById(R.id.img_task_picture);

        mTaskImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);
            }
        });

        switch (getIntent().getStringExtra(Constants.TAG)) {
            case Constants.TAG_CREATE_CHORE:
                findViewById(R.id.spinner_reward).setVisibility(View.VISIBLE);
                findViewById(R.id.button_deadline).setVisibility(View.VISIBLE);
                findViewById(R.id.edit_cost).setVisibility(View.GONE);
                findViewById(R.id.text_cost).setVisibility(View.GONE);
                break;
            case Constants.TAG_CREATE_GOAL:
                findViewById(R.id.spinner_reward).setVisibility(View.GONE);
                findViewById(R.id.button_deadline).setVisibility(View.GONE);
                findViewById(R.id.edit_cost).setVisibility(View.GONE);
                findViewById(R.id.text_cost).setVisibility(View.GONE);
                break;
            case  Constants.TAG_CREATE_REWARD:
                findViewById(R.id.spinner_reward).setVisibility(View.GONE);
                findViewById(R.id.button_deadline).setVisibility(View.GONE);
                findViewById(R.id.edit_cost).setVisibility(View.VISIBLE);
                findViewById(R.id.text_cost).setVisibility(View.VISIBLE);
                break;
            case Constants.TAG_EDIT_CHORE:
                findViewById(R.id.spinner_reward).setVisibility(View.VISIBLE);
                findViewById(R.id.button_deadline).setVisibility(View.VISIBLE);
                findViewById(R.id.edit_cost).setVisibility(View.GONE);
                findViewById(R.id.text_cost).setVisibility(View.GONE);
                break;
            case Constants.TAG_EDIT_GOAL:
                findViewById(R.id.spinner_reward).setVisibility(View.GONE);
                findViewById(R.id.button_deadline).setVisibility(View.GONE);
                findViewById(R.id.edit_cost).setVisibility(View.GONE);
                break;
            case Constants.TAG_EDIT_REWARD:
                findViewById(R.id.spinner_reward).setVisibility(View.VISIBLE);
                findViewById(R.id.button_deadline).setVisibility(View.VISIBLE);
                findViewById(R.id.edit_cost).setVisibility(View.GONE);
                findViewById(R.id.text_cost).setVisibility(View.GONE);
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
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                mSelectedImagePath = getPath(selectedImageUri);
                mTaskImage.setImageDrawable(Drawable.createFromPath(mSelectedImagePath));
            }
        }
    }

    /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        // this is our fallback here
        return uri.getPath();
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
}
