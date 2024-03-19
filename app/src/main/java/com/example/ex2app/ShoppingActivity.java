package com.example.ex2app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ShoppingActivity extends AppCompatActivity {

    private TextView usernameTextView;
    private TextView milkQuantityTextView;
    private TextView eggsQuantityTextView;
    private Button addMilkButton;
    private Button subtractMilkButton;
    private Button addEggsButton;
    private Button subtractEggsButton;
    private Button saveButton;
    private Button logoutButton;

    private int milkQuantity = 0;
    private int eggsQuantity = 0;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        // Initialize views
        usernameTextView = findViewById(R.id.usernameTextView);
        milkQuantityTextView = findViewById(R.id.milkQuantityTextView);
        eggsQuantityTextView = findViewById(R.id.eggsQuantityTextView);
        addMilkButton = findViewById(R.id.addMilkButton);
        subtractMilkButton = findViewById(R.id.subtractMilkButton);
        addEggsButton = findViewById(R.id.addEggsButton);
        subtractEggsButton = findViewById(R.id.subtractEggsButton);
        saveButton = findViewById(R.id.saveButton);
        logoutButton = findViewById(R.id.logoutButton);

        // Retrieve username from intent and display
        username = getIntent().getStringExtra("username");
        usernameTextView.setText("Welcome, " + username + "!");

        // Retrieve and display saved quantities
        User currentUser = getUserByUsername(username);
        if (currentUser != null) {
            milkQuantity = currentUser.getMilkQuantity();
            eggsQuantity = currentUser.getEggsQuantity();
            updateMilkQuantity();
            updateEggsQuantity();
        }

        // Set click listeners for buttons
        addMilkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                milkQuantity++;
                updateMilkQuantity();
            }
        });

        subtractMilkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (milkQuantity > 0) {
                    milkQuantity--;
                    updateMilkQuantity();
                }
            }
        });

        addEggsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eggsQuantity++;
                updateEggsQuantity();
            }
        });

        subtractEggsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eggsQuantity > 0) {
                    eggsQuantity--;
                    updateEggsQuantity();
                }
            }
        });

        // Save button click listener
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update user's milk and eggs quantity in the user list
                updateUserShoppingList();
            }
        });

        // Logout button click listener
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to MainActivity (logout)
                Intent intent = new Intent(ShoppingActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // finish current activity
                Toast.makeText(ShoppingActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateMilkQuantity() {
        milkQuantityTextView.setText("Milk: " + milkQuantity);
    }

    private void updateEggsQuantity() {
        eggsQuantityTextView.setText("Eggs: " + eggsQuantity);
    }

    private void updateUserShoppingList() {
        // Find the user in the user list and update their shopping quantities
        for (User user : UserListManager.getInstance().getUserList()) {
            if (user.getUsername().equals(username)) {
                user.setMilkQuantity(milkQuantity);
                user.setEggsQuantity(eggsQuantity);
                break;
            }
        }
        // Inform the user that shopping list has been updated
        Toast.makeText(ShoppingActivity.this, "Shopping list saved", Toast.LENGTH_SHORT).show();
    }

    private User getUserByUsername(String username) {
        for (User user : UserListManager.getInstance().getUserList()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
