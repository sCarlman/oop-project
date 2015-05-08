package edu.ctl.pinjobs.Handler;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import edu.ctl.pinjobs.Advertisement.IAdvertisement;

/**
 * Created by Filips on 4/28/2015.
 */
public class ListView  {

    private android.widget.ListView listView;
    private Context context;

    public ListView(Context context,android.widget.ListView listView){
        this.listView=listView;
        this.context = context;
    }

    public void setupView(List<IAdvertisement> adList, int id){
        System.out.println("asdasd");
        List<String> titleList = new ArrayList<String>();
        for(IAdvertisement ad : adList){
            titleList.add(ad.getTitle());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                id, titleList);
        listView.setAdapter(adapter);
        listView.invalidate();

    }

}
