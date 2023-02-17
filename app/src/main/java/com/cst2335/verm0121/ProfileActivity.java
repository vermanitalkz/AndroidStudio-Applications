package com.cst2335.verm0121;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    public static final String ANDROID_LAB4 = "user_data";
    public static final String USER_NAME = "user_name";
    public static final String USER_ADDRESS = "user_address";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences sharedPreferences = getSharedPreferences(ANDROID_LAB4, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        EditText enter_name = findViewById(R.id.enterName);
        EditText enter_address = findViewById(R.id.editTextTextPersonName5);
        Button saving_data = findViewById(R.id.button3);
        Button clearing_data = findViewById(R.id.button);

        Intent forEmail = getIntent();
        String email = forEmail.getStringExtra("EMAIL");
        TextView entered_Email = findViewById(R.id.textView);
        entered_Email.setText(email);

        saving_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_data();

            }
        });

        String user_name = sharedPreferences.getString("user_name", "");
        String user_address = sharedPreferences.getString("user_address", "");

        enter_name.setText(user_name);
        enter_address.setText(user_address);


        clearing_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_data();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void save_data() {

        SharedPreferences sharedPreferences = getSharedPreferences(ANDROID_LAB4, MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        EditText editing_name = findViewById(R.id.enterName);
        EditText editing_address = findViewById(R.id.editTextTextPersonName5);

        String name = editing_name.getText().toString();
        String address = editing_address.getText().toString();

        edit.putString(USER_NAME, name);
        edit.putString(USER_ADDRESS, address);
        edit.apply();
    }


    public void clear_data() {
        SharedPreferences sharedPreferences = getSharedPreferences(ANDROID_LAB4, MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        EditText editing_name = findViewById(R.id.enterName);
        EditText editing_address = findViewById(R.id.editTextTextPersonName5);

        String address = editing_address.getText().toString();
        String name = editing_name.getText().toString();

        edit.remove(USER_NAME);
        edit.remove(USER_ADDRESS);
        edit.commit();

        editing_name.setText("");
        editing_address.setText("");

    }
}
