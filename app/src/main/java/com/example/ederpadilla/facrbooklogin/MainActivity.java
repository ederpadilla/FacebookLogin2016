package com.example.ederpadilla.facrbooklogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

public class MainActivity extends AppCompatActivity {
    Bundle inBundle;
    String name;
    String surname;
    String imageUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inBundle = getIntent().getExtras();
        if (AccessToken.getCurrentAccessToken()==null){
            goLoginScreen();
        }else{
            TextView tv_name = (TextView)findViewById(R.id.tv_name);
            name = inBundle.get("name").toString();
            surname = inBundle.get("surname").toString();
            imageUrl = inBundle.get("imageUrl").toString();
            tv_name.setText(name+" "+surname);
            new DownloadImage((ImageView)findViewById(R.id.imgv_pic_porfile)).execute(imageUrl);
        }
    }

    private void goLoginScreen() {
        Intent gotoLogin = new Intent(this,LoginActivity.class);
        gotoLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(gotoLogin);
    }

    public void logout(View view) {
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }
}
