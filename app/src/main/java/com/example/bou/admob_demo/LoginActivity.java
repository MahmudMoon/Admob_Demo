package com.example.bou.admob_demo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    EditText Email,Password;
    Button SignIn;
    FirebaseAuth firebaseAuth;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init_views();
        init_variables();
        init_functions();
    }

    private void init_views() {
        Email = (EditText)findViewById(R.id.name_);
        Password = (EditText)findViewById(R.id.passWord_);
        SignIn = (Button)findViewById(R.id.btn_signIn);
        linearLayout = (LinearLayout)findViewById(R.id.background);
    }

    private void init_variables() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void init_functions() {
        SignIn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean networkCheck =  isNetWorkAvailable();
                if(networkCheck) {

                boolean a = false;
                if(a) {
                    String email = Email.getText().toString();
                    String password = Password.getText().toString();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("email",email);
                    startActivity(intent);
                    return;
                }


                    final String email = Email.getText().toString();
                    String password = Password.getText().toString();
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("email",email);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Snackbar snackbar = Snackbar.make(linearLayout,"No Internet Connection",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
    }

    private boolean isNetWorkAvailable() {

        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}

