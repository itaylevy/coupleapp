package app.itay.coupleapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import app.itay.coupleapp.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        TimerTask timerTask = new WelcomeActivity.WelcomeTimerTask();
        Timer timer = new Timer(true);
        timer.schedule(timerTask, 2000);
    }

    class WelcomeTimerTask extends TimerTask {

        @Override
        public void run() {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

    }
}
