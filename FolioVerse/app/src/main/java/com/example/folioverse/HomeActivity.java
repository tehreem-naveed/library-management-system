package com.example.folioverse;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Profile icon click
        ImageView profileIcon = findViewById(R.id.profileIcon);
        profileIcon.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, UserProfileActivity.class);
            startActivity(intent);
        });

        // Notification icon click
        ImageView notificationIcon = findViewById(R.id.notificationIcon);
        notificationIcon.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, AlertsActivity.class);
            startActivity(intent);
        });

        // Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                return true;

            } else if (itemId == R.id.nav_search) {
                startActivity(new Intent(HomeActivity.this, SearchActivity.class));
                return true;

            } else if (itemId == R.id.nav_notifications) {
                startActivity(new Intent(HomeActivity.this, AlertsActivity.class));
                return true;

            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(HomeActivity.this, UserProfileActivity.class));
                return true;
            }

            return false;
        });
    }

    // 📚 Handle Book Cover Clicks
    public void openBookDetail(View view) {
        Intent intent = new Intent(HomeActivity.this, BookDetailActivity.class);
        int id = view.getId();

        // (Your putBookExtras calls here as in your original code...)

        startActivity(intent);
    }

    private void putBookExtras(Intent intent, int image, String title, String author, String genre, String description) {
        intent.putExtra("image", image);
        intent.putExtra("title", title);
        intent.putExtra("author", author);
        intent.putExtra("genre", genre);
        intent.putExtra("description", description);
    }
}

