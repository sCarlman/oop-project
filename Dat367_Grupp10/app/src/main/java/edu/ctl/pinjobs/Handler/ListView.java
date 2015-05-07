package edu.ctl.pinjobs.Handler;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.filips.dat367_grupp10.R;

import java.util.ArrayList;
import java.util.List;

import edu.ctl.pinjobs.Services.EventBus;
import edu.ctl.pinjobs.Advertisement.IAdvertisement;

/**
 * Created by Filips on 4/28/2015.
 */
public class ListView extends LinearLayout implements EventBus.IEventHandler {

    private android.widget.ListView listView;

    public ListView(Context context,AttributeSet atr){
        super(context,atr);
    }

    @Override
    public void onEvent(EventBus.Event evt, Object o) {

    }
    @Override
    protected void onFinishInflate(){
        this.listView = (android.widget.ListView)findViewById(R.id.adListView);
    }

}
