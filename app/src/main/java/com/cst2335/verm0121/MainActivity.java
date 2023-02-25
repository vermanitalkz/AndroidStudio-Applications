package com.cst2335.verm0121;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 *This class represents the main activity for the application
 * @author Vianshu Vermani
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /**
     *TextView variable for displaying the output messages
     */
    private TextView tv = null;

    /**
     *EditText variable for getting the user's input Password
     */
    private EditText et = null;

    /**
     *Button variable for login/submitting
     */
    private Button btn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.textView);
        EditText et = findViewById(R.id.editTextTextPersonName);
        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(clk -> {
            String password = et.getText().toString();
            checkPasswordComplexity(password);
            if (checkPasswordComplexity(password)) {
                tv.setText("You can go on");
            } else {
                tv.setText("You shall not pass");
            }
        });
    }
        /**
         * This function checks if the password meets the requirements
         * @param password function to check if the password meets the requirements
         * @return returns true if all requirements are met, password is complex
         */

        boolean checkPasswordComplexity(String password) {

            boolean passwordLength, hasUpperCase, hasLowerCase, hasNumber, hasSpecialCharacter;

            passwordLength = hasUpperCase = hasLowerCase = hasNumber = hasSpecialCharacter = false;
            if (password.length() >= 4 && password.length() <= 20) {
                passwordLength = true;
            }
            for (int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);

                if (Character.isUpperCase(c)) {
                    hasUpperCase = true;
                } else if (Character.isLowerCase(c)) {
                    hasLowerCase = true;
                } else if (Character.isDigit(c)) {
                    hasNumber = true;
                } else if (hasSpecialCharacter(c)) {
                    hasSpecialCharacter = true;
                }

            }

            if (!passwordLength) {
                Toast.makeText(getApplicationContext(), "Password must be Between 4 to 20 Characters", Toast.LENGTH_SHORT).show();
                return false;
            } else if (!hasUpperCase) {
                Toast.makeText(getApplicationContext(), "Password does have an uppercase letter", Toast.LENGTH_SHORT).show();
                return false;
            } else if (!hasLowerCase) {
                Toast.makeText(getApplicationContext(), "Password does have an lowercase letter", Toast.LENGTH_SHORT).show();
                return false;
            } else if (!hasNumber) {
                Toast.makeText(getApplicationContext(), "Password must have an number", Toast.LENGTH_SHORT).show();
                return false;
            } else if (!hasSpecialCharacter) {
                Toast.makeText(getApplicationContext(), "password doesn't have any special character", Toast.LENGTH_SHORT).show();
                return false;
            }

            return true;
        }
        /**
         * This functions checks if the given character is a special character
         * @param c the character to be checked
         * @return returns true if the character is a special character
         */
        private boolean hasSpecialCharacter(char c)
        {
            switch (c)
            {
                case '#':
                case '$':
                case '%':
                case '^':
                case '&':
                case '*':
                case '!':
                case '@':
                case '?':
                    return true;
                default:
                    return false;
            }
        }
    }
