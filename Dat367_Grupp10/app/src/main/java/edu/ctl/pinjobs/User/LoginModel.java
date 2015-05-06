package edu.ctl.pinjobs.User;

import edu.ctl.pinjobs.Services.EventBus;

/**
 * Created by Albertsson on 15-05-05.
 */
public class LoginModel {

    public LoginModel(){}

        public void matchLoginWithDatabase(String eMail, String password){
            EventBus.INSTANCE.publish(EventBus.Event.LOGIN_MATCH, null);
        }

}
