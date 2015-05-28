package edu.ctl.pinjobs.profile.controller;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.filips.dat367_grupp10.R;

import edu.ctl.pinjobs.profile.model.IProfile;
import edu.ctl.pinjobs.profile.model.WrongInputException;
import edu.ctl.pinjobs.profile.service.IProfileService;
import edu.ctl.pinjobs.profile.view.EditMyProfileView;

public class EditMyProfileActivity extends ActionBarActivity {

    private IProfile myProfile;
    private EditMyProfileView editMyProfileView;
    private IProfileService iProfileService;

    private boolean canceledByError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_profile);

        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null) {
            IProfile profile = (IProfile) bundle.getSerializable("sendProfile");
            this.myProfile = profile;
            editMyProfileView = new EditMyProfileView(profile, this);

            iProfileService = (IProfileService)bundle.getSerializable("PROFILE_SERVICE");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_my_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveNewInputToProfile(View view){
        setCanceledByError(false);
        try{
            myProfile.setFirstName(editMyProfileView.getTextFromEditFirstName());
        }catch (WrongInputException e){
            editMyProfileView.setInputError("firstname");
            setCanceledByError(true);
        }
        try{
            myProfile.setLastName(editMyProfileView.getTextFromEditLastName());
        }catch (WrongInputException e){
            editMyProfileView.setInputError("lastname");
            setCanceledByError(true);
        }
        try{
            myProfile.setPhone(editMyProfileView.getTextFromEditPhone());
        }catch (WrongInputException e){
            editMyProfileView.setInputError("phone");
            setCanceledByError(true);
        }
        try{
            myProfile.setAddress(editMyProfileView.getTextFromEditAddress() + "," + editMyProfileView.getTextFromEditCity());
        }catch (WrongInputException e){
            if(e.getError().equals("City")){
                editMyProfileView.setInputError("city");
                setCanceledByError(true);
            }
            if(e.getError().equals("Location")){
                editMyProfileView.setInputError("address");
                setCanceledByError(true);
            }
        }

        if(!canceledByError){
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("SEND_BACK_PROFILE", myProfile);
            intent.putExtras(bundle);
            iProfileService.updateProfile(myProfile);
            setResult(2, intent);
            finish();
        }
    }

    public void setCanceledByError(boolean cancel){
        this.canceledByError = cancel;
    }
}
