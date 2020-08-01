package com.example.bmsit.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.bmsit.Database.Contacts;
import com.example.bmsit.Login.Login;
import com.example.bmsit.R;
import com.example.bmsit.changepassword.Forgot_password;

public class Register extends AppCompatActivity {


    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;
    private EditText username;
    private EditText userusn;
    private EditText userpassword;
    private EditText comfirm_password;
    private EditText user_email;
    private EditText userphonenumber;
    private Spinner user_branch;
    private Spinner user_sec;
    private Spinner user_sem;
    private TextView  userlogin;
    private Button usersignup;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        // Spinning branch ,semister ,section

        Spinner Branch_Spinner = (Spinner) findViewById(R.id.Branch);

        ArrayAdapter<String> Branch_Adapter = new ArrayAdapter<String>(Register.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Branch_Array));

        Branch_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Branch_Spinner.setAdapter(Branch_Adapter);


        Spinner Semister_Spinner = (Spinner) findViewById(R.id.Sem);

        ArrayAdapter<String> Semister_Adapter = new ArrayAdapter<String>(Register.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Semister_Array));
        Semister_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Semister_Spinner.setAdapter(Semister_Adapter);


        Spinner Section_spinner = (Spinner) findViewById(R.id.Section);

        ArrayAdapter<String> Section_Adapter = new ArrayAdapter<String>(Register.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Section_Array));
        Section_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Section_spinner.setAdapter(Section_Adapter);



        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);


        username = findViewById(R.id.user_name);
        userusn = findViewById(R.id.user_usn);
        userpassword = findViewById(R.id.user_password);
        userphonenumber = findViewById(R.id.user_phone_number);
        user_sec = findViewById(R.id.Section);
        user_sem = findViewById(R.id.Sem);
        user_branch = findViewById(R.id.Branch);
        userlogin = findViewById(R.id.user_login);
        usersignup = findViewById(R.id.user_signin);
        user_email = findViewById(R.id.editText);



        userlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showProgress(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent open_login_activity = new Intent(Register.this,Login.class);
                        startActivity(open_login_activity);
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                        finish();
                        showProgress(false);
                    }
                },2000);
            }
        });

        usersignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().isEmpty() || userpassword.getText().toString().isEmpty() || user_email.getText().toString().isEmpty()
                        || userphonenumber.getText().toString().isEmpty() || userusn.getText().toString().isEmpty()){

                    Toast.makeText(Register.this,"Enter All Fields",Toast.LENGTH_LONG).show();
                }
                else {

                    Contacts contacts = new Contacts();
                    contacts.setName(username.getText().toString().trim());
                    contacts.setUsn(userusn.getText().toString().trim());
                    contacts.setPassword(userpassword.getText().toString().trim());
                    contacts.setPhonenumber(userpassword.getText().toString().trim());
                    contacts.setEmail(user_email.getText().toString().trim());

                    tvLoad.setText("Registering New user");
                    showProgress(true);



                    Backendless.Persistence.save(contacts, new AsyncCallback<Contacts>() {
                        @Override
                        public void handleResponse(Contacts response) {

                            Toast.makeText(Register.this,"Successfully Registered ",Toast.LENGTH_LONG).show();
                            username.setText(null);
                            userpassword.setText(null);
                            userusn.setText(null);
                            user_email.setText(null);
                            userphonenumber.setText(null);
                            showProgress(false);

                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {

                            Toast.makeText(Register.this,"Error:" + fault.getMessage(),Toast.LENGTH_LONG).show();
                            showProgress(false);
                        }
                    });







                    /*if(true){

                        BackendlessUser userdetails = new BackendlessUser();

                        userdetails.setEmail(user_email.getText().toString().trim());
                        userdetails.setPassword(userpassword.getText().toString().trim());
                        userdetails.setProperty("Name",username.getText().toString().trim());


                        Backendless.UserService.register(userdetails, new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse(BackendlessUser response) {
                                Toast.makeText(Register.this ,"Registered",Toast.LENGTH_LONG).show();



                                Intent open_user_details = new Intent(Register.this, Forgot_password.class);
                                startActivity(open_user_details);
                                Register.this.finish();

                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {

                                Toast.makeText(Register.this,"Error" + fault.getMessage(),Toast.LENGTH_LONG).show();
                                showProgress(false);

                            }
                        });

                    }

                    else {
                        Toast.makeText(Register.this,"Password did not match",Toast.LENGTH_LONG).show();
                    }*/
                }

            }

        });


    }





    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });

            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    @Override
    public void onBackPressed() {
       // super.onBackPressed();
    }
}
