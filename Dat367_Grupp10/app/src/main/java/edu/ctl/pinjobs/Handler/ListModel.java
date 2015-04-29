package edu.ctl.pinjobs.Handler;

import java.util.List;

import edu.ctl.pinjobs.model.Advertisement;
import edu.ctl.pinjobs.model.IAd;

/**
 * Created by Filips on 4/28/2015.
 */
public class ListModel implements IListModel {

    private List<IAd> adList;

    public ListModel(List<IAd> adList) {
        this.adList = adList;
    }

    @Override
    public List<IAd> getList() {
        return adList;
    }

    @Override
    public void addToList(IAd add) {
        this.adList.add(add);
    }

    @Override
    public void replaceList(List<IAd> addList) {
        this.adList.clear();
        adList.addAll(addList);
    }

    @Override
    public void removeFromList(IAd add) {
        this.adList.remove(add);
    }
}
