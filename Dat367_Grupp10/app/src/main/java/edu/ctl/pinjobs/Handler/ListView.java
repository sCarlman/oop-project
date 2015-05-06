package edu.ctl.pinjobs.Handler;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.filips.dat367_grupp10.R;

import java.util.ArrayList;
import java.util.List;

import edu.ctl.pinjobs.Services.EventBus;
import edu.ctl.pinjobs.model.IAdvertisement;

/**
 * Created by Filips on 4/28/2015.
 */
public class ListView extends RelativeLayout implements EventBus.IEventHandler {

    private android.widget.ListView listView;
    private Context context;

    public ListView(Context context,AttributeSet atr){
        super(context,atr);
        this.context = context;
    }

    @Override
    public void onEvent(EventBus.Event evt, Object o) {
        setupView((IAdvertisement)o);
    }
    @Override
    protected void onFinishInflate(){
        this.listView = (android.widget.ListView)findViewById(R.id.adListView);
    }

    public void setupView(IAdvertisement ad){

    }

}
