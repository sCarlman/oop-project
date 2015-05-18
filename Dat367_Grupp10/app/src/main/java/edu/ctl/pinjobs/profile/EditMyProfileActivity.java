package edu.ctl.pinjobs.profile;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.filips.dat367_grupp10.R;

public class EditMyProfileActivity extends ActionBarActivity {

    private IProfile myProfile;

    private EditMyProfileView editMyProfileView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_profile);

        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();

        IProfile profile = (IProfile)bundle.getSerializable("sendProfile");
        this.myProfile = profile;

         editMyProfileView = new EditMyProfileView( profile, (EditText)findViewById(R.id.myProfileFirstNameEditText),
                (EditText)findViewById(R.id.myProfileLastNameEditText), (EditText)findViewById(R.id.myProfilePhoneEditText),
                (EditText)findViewById(R.id.myProfileAddressEditText), (EditText)findViewById(R.id.myProfileCityEditText));

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
        editMyProfileView.saveTextFieldsToProfile(myProfile);

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("sendProfile", myProfile);
        intent.putExtras(bundle);
        setResult(2, intent);
        finish();
    }
}
