package edu.ctl.pinjobs.User;

import java.util.List;

import edu.ctl.pinjobs.Services.EventBus;
import edu.ctl.pinjobs.profile.IProfile;

/**
 * Created by Albertsson on 15-05-05.
 */
public class LoginModel {

    private String eMail;
    private String password;

    public LoginModel(){}

        public void matchLoginWithDatabase(String eMail, String password){

            this.eMail = eMail;
            this.password = password;

            EventBus.INSTANCE.publish(EventBus.Event.LOGIN_MATCH, null);
        }

    public void doesMailExistInUserDatabase(List<IProfile> profiles){
        for (IProfile profile : profiles){
            if (profile.getEmail().equals(eMail)){
                matchPasswordlWithUserDatabase(profile);
            }else{
                System.out.println("*>*>*>*>*>Finns ingen profil med denna mail!!!");
            }
        }
    }

    public void matchPasswordlWithUserDatabase(IProfile profile){
        if(profile.getPassword() != null){
            EventBus.INSTANCE.publish(EventBus.Event.LOGIN_SUCCESS, null);
        }else{
            System.out.println("Databas null!!!!!");
        }
    }

}
