package edu.ctl.pinjobs.Handler;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.filips.dat367_grupp10.R;

import java.util.Timer;
import java.util.TimerTask;

import edu.ctl.pinjobs.eventbus.EventBus;

public class LoadingScreen extends Activity implements EventBus.IEventHandler {

    private Timer timer= new Timer();
    Activity activity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        EventBus.INSTANCE.addListener(this);
        ProgressBar spinner;
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);
        timer.schedule(task,5000);
    }
    TimerTask task = new TimerTask() {

        @Override
        public void run() {
            activity.setResult(2);
            activity.finish();
        }
    };


    @Override
    public void onEvent(EventBus.Event evt, Object o) {
        if(evt == EventBus.Event.ADLIST_NOT_EMPTY);
            this.finish();
    }
}
