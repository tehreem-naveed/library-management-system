package com.example.folioverse;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private StoryAdapter storyAdapter;
    private List<Book> fullStoryList;
    private TextView noResultsText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        EditText searchInput = findViewById(R.id.searchInput);
        RecyclerView trendingBooksRecyclerView = findViewById(R.id.trendingBooksRecyclerView);
        RecyclerView allStoriesRecyclerView = findViewById(R.id.allStoriesRecyclerView);
        noResultsText = findViewById(R.id.noResultsText);

        // 📦 Trending Books
        List<Book> trendingBooksList = new ArrayList<>();
        trendingBooksList.add(new Book(R.drawable.cover_fantasy_1, "Realm of Fire", "A. Knight", "🔥 Popular", "A fantasy tale of dragons and destiny."));
        trendingBooksList.add(new Book(R.drawable.cover_fantasy_2, "Shadowborn", "M. Rivers", "✨ New Arrival", "A mysterious journey through enchanted forests."));
        trendingBooksList.add(new Book(R.drawable.cover_fantasy_3, "Eternal Blade", "D. Winter", "🏆 Editor's Pick", "A warrior’s quest for the blade of legends."));
        trendingBooksList.add(new Book(R.drawable.cover_fantasy_4, "The Last Mage", "L. Phoenix", "⭐ Top Rated", "Magic, secrets, and the fall of an empire."));

        // 📘 Full Story List
        fullStoryList = new ArrayList<>();
        fullStoryList.add(new Book(R.drawable.cover_horror_1, "Whispers in the Dark", "E. Hollow", "Horror", "A haunted house mystery you can't escape."));
        fullStoryList.add(new Book(R.drawable.cover_horror_2, "The Cursed Room", "N. Black", "Horror", "A chilling tale of a room that consumes its guests."));
        fullStoryList.add(new Book(R.drawable.cover_mystery_1, "Vanishing Point", "K. Steele", "Mystery", "A detective races to solve a string of vanishings."));
        fullStoryList.add(new Book(R.drawable.cover_mystery_2, "The Hidden Key", "J. Grey", "Mystery", "A detective’s pursuit of an ancient relic."));
        fullStoryList.add(new Book(R.drawable.cover_scifi_1, "Starborne", "C. Vega", "Sci-Fi", "Humans discover a forgotten alien legacy."));
        fullStoryList.add(new Book(R.drawable.cover_scifi_2, "Quantum Run", "M. Trask", "Sci-Fi", "Time loops and paradoxes in a space race."));
        fullStoryList.add(new Book(R.drawable.cover_scifi_3, "Galactic Drift", "T. Nova", "Sci-Fi", "Journey to a galaxy torn by rebellion."));
        fullStoryList.add(new Book(R.drawable.cover_nonfiction_1, "Mind Matters", "Dr. Selim", "Non-Fiction", "Explore the psychology behind success."));
        fullStoryList.add(new Book(R.drawable.cover_nonfiction_2, "The Focus Code", "A. Lee", "Non-Fiction", "A productivity framework for modern life."));
        fullStoryList.add(new Book(R.drawable.cover_humor_1, "Giggles Galore", "L. Laff", "Humor", "Jokes and stories that will make your belly hurt."));
        fullStoryList.add(new Book(R.drawable.cover_humor_2, "Comic Crisis", "F. Snaps", "Humor", "An office comedy that gets way out of hand."));
        fullStoryList.add(new Book(R.drawable.cover_humor_3, "Laugh Factory", "S. Chuckles", "Humor", "A collection of hilarious short stories."));

        // ✅ Trending RecyclerView
        TrendingAdapter trendingAdapter = new TrendingAdapter(this, trendingBooksList);
        trendingBooksRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        trendingBooksRecyclerView.setAdapter(trendingAdapter);

        // ✅ All Stories RecyclerView
        storyAdapter = new StoryAdapter(this, fullStoryList);
        allStoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        allStoriesRecyclerView.setAdapter(storyAdapter);

        // 🔍 Search functionality
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterStories(s.toString());
            }
        });

        // ✅ Bottom Navigation Intent Setup with Profile Click
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_search);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                startActivity(new Intent(SearchActivity.this, HomeActivity.class));

                return true;
            } else if (itemId == R.id.nav_notifications) {
                startActivity(new Intent(SearchActivity.this, AlertsActivity.class));

                return true;
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(SearchActivity.this, UserProfileActivity.class));

                return true;
            } else {
                return itemId == R.id.nav_search;
            }
        });
    }

    // 🔍 Filter the stories list
    private void filterStories(String query) {
        List<Book> filteredList = new ArrayList<>();
        for (Book book : fullStoryList) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(book);
            }
        }

        storyAdapter.updateData(filteredList);
        noResultsText.setVisibility(filteredList.isEmpty() ? View.VISIBLE : View.GONE);
    }

    // 📖 Open Book Detail Activity
    private void openBookDetail(Book book) {
        Intent intent = new Intent(this, BookDetailActivity.class);
        intent.putExtra("image", book.getImageResId());
        intent.putExtra("title", book.getTitle());
        intent.putExtra("author", book.getAuthor());
        intent.putExtra("genre", book.getGenre());
        intent.putExtra("description", book.getDescription());
        startActivity(intent);
    }
}
