package com.example.bmsit.Login;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.local.UserIdStorageFactory;
import com.example.bmsit.Database.Contacts;
import com.example.bmsit.Database.user_details;
import com.example.bmsit.R;
import com.example.bmsit.Register.Register;
import com.example.bmsit.SplashScreen.Splash_Screen;
import com.example.bmsit.changepassword.Forgot_password;
import com.example.bmsit.ui.main.SectionsPagerAdapter;
import com.example.bmsit.user_logs;

public class Login extends AppCompatActivity {

    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;

    private EditText user_usn;
    private EditText user_pass;
    private Button user_login;
    private TextView user_signin;
    private TextView forgot_password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        user_usn = findViewById(R.id.user_usn);
        user_pass = findViewById(R.id.user_password);
        user_login = findViewById(R.id.user_login);
        user_signin = findViewById(R.id.user_signin);
        forgot_password = findViewById(R.id.forgot_password);






       /* tvLoad.setText("Athenticating Please Wait....");
        showProgress(true);


        Backendless.UserService.isValidLogin(new AsyncCallback<Boolean>() {
            @Override
            public void handleResponse(Boolean response) {
                if(response){

                    tvLoad.setText("User Authenticated ");
                    tvLoad.setText("Signing In");
                    String userObjId = UserIdStorageFactory.instance().getStorage().get();

                    Backendless.Data.of(user_details.class).findById(userObjId, new AsyncCallback<user_details>() {
                        @Override
                        public void handleResponse(user_details response) {

                            startActivity(new Intent(Login.this, SectionsPagerAdapter.class));
                            Login.this.finish();
                            showProgress(false);
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {

                            Toast.makeText(Login.this,"Error" + fault.getMessage(),Toast.LENGTH_LONG).show();
                            showProgress(false);

                        }
                    });

                }
                else {
                    showProgress(false);
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Toast.makeText(Login.this,"Error" + fault.getMessage(),Toast.LENGTH_LONG).show();
                showProgress(false);

            }
        });*/



        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showProgress(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                Intent open_password_activity = new Intent(Login.this, Forgot_password.class);
                startActivity(open_password_activity);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
                showProgress(false);
                    }
                },2000);
            }
        });

        user_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgress(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent open_register_form = new Intent(Login.this, Register.class);
                        startActivity(open_register_form);
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                        finish();
                        showProgress(false);
                    }
                },2000);
            }
        });




        user_login.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {



                Intent open = new Intent(Login.this, user_logs.class);
                startActivity(open);

               /* showProgress(true);

                if(user_usn.getText().toString().trim().isEmpty() || user_pass.getText().toString().trim().isEmpty()){

                    Toast.makeText(Login.this,"Enter All Fields ",Toast.LENGTH_LONG).show();
                    showProgress(false);
                }

                else{


                    Contacts contacts = new Contacts();
                    contacts.setUsn(user_usn.getText().toString().trim());
                    contacts.setPassword(user_pass.getText().toString().trim());
                }*/
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
      //  super.onBackPressed();
    }
}