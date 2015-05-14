package edu.ctl.pinjobs.User;

import java.util.List;

import edu.ctl.pinjobs.eventbus.EventBus;
import edu.ctl.pinjobs.profile.IProfile;

/**
 * Created by Albertsson on 15-05-05.
 */
public class LoginModel {

    private String eMail;
    private String password;
    private IProfile profile;

    public LoginModel(){}

        public void matchLoginWithDatabase(){

            EventBus.INSTANCE.publish(EventBus.Event.LOGIN_MATCH, this);
        }

    public void doesMailExistInUserDatabase(List<IProfile> profiles){

        boolean matchFound = false;

        for (IProfile profile : profiles){
            if (profile.getEmail().equals(eMail)){
                matchFound = true;
                matchPasswordWithUserDatabase(profile);
                break;
            }
        }

        if(!matchFound){
            EventBus.INSTANCE.publish(EventBus.Event.LOGIN_FAILED_WRONG_EMAIL, null);
        }
    }

    public void matchPasswordWithUserDatabase(IProfile profile){
        if(profile.getPassword() != null){
            if(profile.getPassword().equals(password)){
                this.profile = profile;
                //EventBus.INSTANCE.publish(EventBus.Event.LOGIN_SUCCESS_REDRAW_MAIN, null);
                EventBus.INSTANCE.publish(EventBus.Event.LOGIN_SUCCESS, this);
            }else{
                EventBus.INSTANCE.publish(EventBus.Event.LOGIN_FAILED_WRONG_PASSWORD, null);
            }

        }else{
            System.out.println("Databas null!!!!!");
        }
    }

    public String geteMail(){
        return this.eMail;
    }

    public String getPassword(){
        return this.password;
    }

    public IProfile getProfile(){
        return this.profile;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
