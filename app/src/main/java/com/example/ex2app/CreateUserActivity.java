package com.example.ex2app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CreateUserActivity extends AppCompatActivity {

    private EditText newUsernameEditText;
    private EditText newPasswordEditText;
    private EditText phoneNumberEditText;
    private Button createUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        newUsernameEditText = findViewById(R.id.newUsernameEditText);
        newPasswordEditText = findViewById(R.id.newPasswordEditText);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        createUserButton = findViewById(R.id.createUserButton);

        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = newUsernameEditText.getText().toString();
                String newPassword = newPasswordEditText.getText().toString();
                String phoneNumber = phoneNumberEditText.getText().toString();

                if (newUsername.isEmpty() || newPassword.isEmpty() || phoneNumber.isEmpty()) {
                    Toast.makeText(CreateUserActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Check if the username already exists
                    if (UserListManager.getInstance().isUsernameExists(newUsername)) {
                        Toast.makeText(CreateUserActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                    } else {
                        // Create a new user and add it to the user list
                        User newUser = new User(newUsername, newPassword, phoneNumber);
                        UserListManager.getInstance().addUser(newUser);
                        Toast.makeText(CreateUserActivity.this, "New user created successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CreateUserActivity.this, MainActivity.class);
                        startActivity(intent);

                        // Clear input fields after successful creation
                        newUsernameEditText.setText("");
                        newPasswordEditText.setText("");
                        phoneNumberEditText.setText("");
                    }
                }
            }
        });
    }
}
