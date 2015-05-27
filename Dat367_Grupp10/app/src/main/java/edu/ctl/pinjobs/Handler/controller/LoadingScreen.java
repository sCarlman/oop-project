package edu.ctl.pinjobs.handler.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.filips.dat367_grupp10.R;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import edu.ctl.pinjobs.handler.model.AdvertisementListHolder;

public class LoadingScreen extends Activity implements Observer {

    private Timer timer= new Timer();
    Activity thisActivity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        AdvertisementListHolder.getInstance().addObserver(this);
        ProgressBar spinner;
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);
        timer.schedule(task,5000);

    }
    TimerTask task = new TimerTask() {

        @Override
        public void run() {
            thisActivity.setResult(2);
            thisActivity.finish();
        }
    };

    @Override
    public void update(Observable observable, Object data) {
        System.out.println("I UPDATE!! ***************");
        //task.cancel();
        thisActivity.finish();
    }
}
