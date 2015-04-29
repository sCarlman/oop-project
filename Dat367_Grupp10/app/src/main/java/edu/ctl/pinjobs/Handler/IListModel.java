package edu.ctl.pinjobs.Handler;

import java.util.List;

import edu.ctl.pinjobs.model.Advertisement;
import edu.ctl.pinjobs.model.IAd;

/**
 * Created by Filips on 4/28/2015.
 */
public interface IListModel {

    public List<IAd> getList();
    public void addToList(IAd add);
    public void replaceList(List<IAd> addList);
    public void removeFromList(IAd add);


}
