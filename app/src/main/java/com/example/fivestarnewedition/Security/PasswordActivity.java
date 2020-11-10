package com.example.fivestarnewedition.Security;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fivestarnewedition.Constant.Constant;
import com.example.fivestarnewedition.R;


public class PasswordActivity extends AppCompatActivity {
    private EditText newPassword ;
//    private EditText oldPassword;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        newPassword = findViewById(R.id.new_password);
//        oldPassword = findViewById(R.id.old_password);
        save = findViewById(R.id.save_btn);


        if (getIntent().getStringExtra("code").equals("0")){
            changePassword();
        }else {
            login();
        }
    }

    private void login() {
        newPassword.setHint("Password");
//        oldPassword.setVisibility(View.GONE);
        save.setText("Log In");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constant.getPassword(PasswordActivity.this).contains(newPassword.getText())){
                    Intent my = new Intent(PasswordActivity.this, SecurityActivity.class);
                    startActivity(my);
                    PasswordActivity.this.finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Wrong Password",Toast.LENGTH_SHORT).show();
                    newPassword.requestFocus();
                }
            }
        });
    }

    private void changePassword(){
//        oldPassword.setHint("Old Password");
        newPassword.setVisibility(View.VISIBLE);
        newPassword.setHint("New Password");
        save.setText("Set Password");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (true){
                    if (newPassword.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(),"New Password is Empty!!!",Toast.LENGTH_SHORT).show();
                        newPassword.requestFocus();
                        return;
                    }
                    else {
                        Constant.changePassword(PasswordActivity.this ,newPassword.getText().toString());
                        Toast.makeText(getApplicationContext(),"Password Changed",Toast.LENGTH_SHORT).show();
                        PasswordActivity.this.finish();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Wrong Password",Toast.LENGTH_SHORT).show();
//                    oldPassword.requestFocus();
                }
            }
        });
    }
}