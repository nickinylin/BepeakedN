package dk.bepeaked.bodybook.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import dk.bepeaked.bodybook.R;


public class Login_act extends AppCompatActivity {

    Button btnLogin, btnAddUser;
    TextView tvMail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        btnLogin = (Button) findViewById(R.id.login_button);
        btnLogin.setOnClickListener(new OnClickListener() {

           @Override
           public void onClick(View v) {
               Intent i = new Intent(Login_act.this , Main_act.class);
               startActivity(i);
           }
        });

        btnAddUser = (Button) findViewById(R.id.addUser_button);
        btnAddUser.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login_act.this , AddUser_act.class);
                startActivity(i);
            }
        });


    }
}
