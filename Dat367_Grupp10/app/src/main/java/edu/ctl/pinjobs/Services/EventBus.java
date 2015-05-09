package edu.ctl.pinjobs.Services;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filips on 4/27/2015.
 */
public enum EventBus {

    INSTANCE;

    public interface IEventHandler {

        public void onEvent(Event evt, Object o);
    };

    public enum Event {  // All possible events

        ADLIST_UPDATED,
        LOGIN_MATCH,
        LOGIN_SUCCESS,
        LOGIN_FAILED,
        SAVE_PROFILE

    };
    private boolean trace = true;

    private final List<IEventHandler> handlers = new ArrayList<IEventHandler>();

    public void addListener(IEventHandler handler) {
        handlers.add(handler);
    }

    public void removeListener(IEventHandler handler) {
        handlers.remove(handler);
    }

    public void publish(Event evt, Object data) {

        for (IEventHandler evh : handlers) {
            evh.onEvent(evt, data);
        }
    }
}
