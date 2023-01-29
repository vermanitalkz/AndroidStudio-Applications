package com.cst2335.verm0121;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this,getResources().getString(R.string.toast_message),Toast.LENGTH_LONG).show();
        });
        CompoundButton mybtn = findViewById(R.id.switch2);
        mybtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            Snackbar snackbar = Snackbar.make(findViewById(R.id.parent),getResources().getString(R.string.snackON), Snackbar.LENGTH_LONG);
            Snackbar snackbar2 = Snackbar.make(findViewById(R.id.parent), getResources().getString(R.string.snackOFF), Snackbar.LENGTH_LONG);

            @Override
            public void onCheckedChanged(CompoundButton mybtn, boolean b) {
                if(b) {

                    snackbar.show();
                    snackbar.setAction("undo", click -> mybtn.setChecked(!b));
                    snackbar.setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            snackbar2.show();
                            mybtn.setChecked(false);
                        }
                    });
                }
                else{
                    snackbar2.show();
                    snackbar2.setAction("undo", click -> mybtn.setChecked(!b));
                    snackbar2.setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            snackbar.show();
                            mybtn.setChecked(true);
                        }
                    });

                }

            }
        });
    }
}