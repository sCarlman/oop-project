package edu.ctl.pinjobs.user.model;

import java.util.List;

import edu.ctl.pinjobs.eventbus.EventBus;
import edu.ctl.pinjobs.profile.model.IProfile;

/**
 * Created by Albertsson on 15-05-05.
 */
public class LoginModel {

    private String eMail;
    private String password;
    private IProfile profile;

    public boolean doesMailAndPasswordExistInList(List<IProfile> profiles){
        IProfile profile = doesMailExistInList(profiles);
        if(profile!=null){
            if(doesPasswordMatchMail(profile)){
                return true;
            }
        }return false;
    }

    public IProfile doesMailExistInList(List<IProfile> profiles){
        for (IProfile profile : profiles){
            if (profile.getEmail().equals(eMail)){
                return profile;
            }
        }
        return null;
    }

    public boolean doesPasswordMatchMail(IProfile profile){
        if(profile.getPassword() != null){
            if(profile.getPassword().equals(password)){
                this.profile = profile;
                return true;
            }else{
                return false;
            }
        }return false;
    }

    public IProfile getProfile(){
        return this.profile;
    }

    public void setEmail(String eMail) {
        this.eMail = eMail;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
