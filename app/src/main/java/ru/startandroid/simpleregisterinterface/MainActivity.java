package ru.startandroid.simpleregisterinterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    // qisqa "logt" yozuvi orqali chiqarsa bo'ladi
    private static final String TAG = "myLogs";

    private TextView tvWarnName,tvWarnEmail,tvWarnPassword,tvWarnPassRepeat;
    private Button btnPickImage,btnRegister;
    private EditText etName,etEmail,etPassword,etPassRepeat;
    private Spinner countriesSpinner;
    private RadioGroup rgGender;
    private CheckBox checkAgreement;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Yet to talk about", Toast.LENGTH_SHORT).show();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRegister();
            }
        });
    }

    private void initRegister() {
        Log.d(TAG,"InitRegister: Started");

        if (validateData()){
            if (checkAgreement.isChecked()){
                showSnackBar();
            }
            else
                Toast.makeText(this, "You need to agree to the licence agreement", Toast.LENGTH_SHORT).show();
        }
    }

    private void showSnackBar() {
        Log.d(TAG,"showSnackBar: Started");

        tvWarnName.setVisibility(View.GONE);
        tvWarnEmail.setVisibility(View.GONE);
        tvWarnPassword.setVisibility(View.GONE);
        tvWarnPassRepeat.setVisibility(View.GONE);

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String country = countriesSpinner.getSelectedItem().toString();
        String gender = "";

        switch (rgGender.getCheckedRadioButtonId()){
            case R.id.rbMale:
                gender = "Male";
                break;
            case R.id.rbFemale:
                gender = "Female";
                break;
            case R.id.rbOther:
                gender = "Other";
                break;
            default:
                gender = "Unknown";
                break;
        }

        String snackText = "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Gender: " + gender + "\n" +
                "country: " + country ;

        Log.d(TAG,"showSnackBar: SnackBarText " + snackText);

        Snackbar.make(constraintLayout,snackText,Snackbar.LENGTH_LONG)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        etName.setText("");
                        etEmail.setText("");
                        etPassword.setText("");
                        etPassRepeat.setText("");
                    }
                }).show();
    }

    private boolean validateData() {
        Log.d(TAG,"ValidateData Started");

        if (etName.getText().toString().equals("")){
            tvWarnName.setVisibility(View.VISIBLE);
            tvWarnName.setText("Enter your name");
            return false;
        }
        if (etEmail.getText().toString().equals("")){
            tvWarnEmail.setVisibility(View.VISIBLE);
            tvWarnEmail.setText("Enter your Email");
            return false;
        }
        else {
            tvWarnEmail.setVisibility(View.GONE);
        }
        if (etPassword.getText().toString().equals("")){
            tvWarnPassword.setVisibility(View.VISIBLE);
            tvWarnPassword.setText("Enter your Password");
            return false;
        }
        if (etPassRepeat.getText().toString().equals("")){
            tvWarnPassRepeat.setVisibility(View.VISIBLE);
            tvWarnPassRepeat.setText("Repeat your password");
            return false;
        }

        if (!etPassRepeat.getText().toString().equals(etPassword.getText().toString())){
            tvWarnPassRepeat.setVisibility(View.VISIBLE);
            tvWarnPassRepeat.setText("Password doesn't match");
            return false;
        }

        return true;
    }

    private void initViews() {
        Log.d("MainActivity","InitViews Started");

        tvWarnName = findViewById(R.id.tvWarnName);
        tvWarnEmail = findViewById(R.id.tvWarnEmail);
        tvWarnPassword = findViewById(R.id.tvWarnPass);
        tvWarnPassRepeat = findViewById(R.id.tvWarnPassRepeat);

        btnPickImage = findViewById(R.id.btnPickImage);
        btnRegister = findViewById(R.id.btnRegister);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etPassRepeat = findViewById(R.id.etPassRepeat);

        countriesSpinner = findViewById(R.id.spinCountry);
        rgGender = findViewById(R.id.rgGender);
        checkAgreement = findViewById(R.id.checkAgreement);
        constraintLayout = findViewById(R.id.constraintLayout);
    }
}