package cmpe235.smarttree;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.regex.Pattern;



public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    Button signupBtn;
    Button Scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        final Activity activity = this;
        signupBtn = (Button) findViewById(R.id.button_signup);
        signupBtn.setOnClickListener(this);

        Scan = (Button) findViewById(R.id.barcode);
        Scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Signup via barcode scan");
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.setCameraId(0);
                integrator.initiateScan();

            }
        });
        Button btnReset = (Button) findViewById(R.id.button3);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go back to home screen
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onDestroy(){
        super.onDestroy();
        final Activity activity = this;
        IntentIntegrator integrator = new IntentIntegrator(activity);

    }

    @Override
    public void onClick(View v) {


        if(v.getId() == R.id.button_signup){

            //get all the parameters that user enter
            EditText email = (EditText) findViewById(R.id.email_signup);
            String emailAddress = email.getText().toString();
            EditText pass = (EditText) findViewById(R.id.password_signup);
            String password = pass.getText().toString();
            EditText name = (EditText) findViewById(R.id.name_signup);
            String username = name.getText().toString();
            EditText phone = (EditText) findViewById(R.id.phone_signup);
            String userPhone = phone.getText().toString();

            if ((emailAddress.trim().equals(""))||(password.trim().equals(""))||(username.trim().equals(""))||(userPhone.trim().equals(""))) {
                Toast.makeText(this, "Please Enter credentials to Signup ", Toast.LENGTH_SHORT).show();
                return;
            }

            //put all the parameters into User object
            UserDetails user = new UserDetails();
            user.setEmail(emailAddress);
            user.setPassword(password);
            user.setUsername(username);
            user.setPhone(userPhone);

            //get DB connection
            DBConnector dbConnector = new DBConnector(this);

            dbConnector.insertUser(user);

            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
            finish();//finish this activity and the user cannot go back to sign up page

        }
    }




    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        EditText email = (EditText) findViewById(R.id.email_signup);
        EditText pass = (EditText) findViewById(R.id.password_signup);
        EditText name = (EditText) findViewById(R.id.name_signup);
        EditText phone = (EditText) findViewById(R.id.phone_signup);
        IntentResult result =IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result!=null){
            String ScannedData=result.getContents();
            String[] array = ScannedData.split(Pattern.quote("\n"));
            if(ScannedData!=null){

                for (int i = 0; i<array.length; i++){
                    Log.i("Member name: ",array[i]);
                    name.setText(array[0]);
                    pass.setText(array[1]);
                    email.setText(array[2]);
                    phone.setText(array[3]);

                }
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
