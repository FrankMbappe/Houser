package cm.btech2021.houser.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import cm.btech2021.houser.R;
import cm.btech2021.houser.activities.game.TicTacToeHomeActivity;
import cm.btech2021.houser.fragments.FavoritesFragment;
import cm.btech2021.houser.fragments.HomeFragment;
import cm.btech2021.houser.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity {
    TextView txtTitle;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setOnClickListener(view -> {
            startActivity(new Intent(this, TicTacToeHomeActivity.class));
        });

        // The initial fragment is the Home fragment
        loadFragment(new HomeFragment(), R.id.mainActivityFrameLayout);

        bottomNavigationView = findViewById(R.id.mainActivityBottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menuItemHome) {
                loadFragment(new HomeFragment(), R.id.mainActivityFrameLayout);
                return true;
            }
            if (item.getItemId() == R.id.menuItemFavorites) {
                loadFragment(new FavoritesFragment(), R.id.mainActivityFrameLayout);
                return true;
            }
            if (item.getItemId() == R.id.menuItemSettings) {
                loadFragment(new SettingsFragment(), R.id.mainActivityFrameLayout);
                return true;
            }
            return false;
        });
    }

    void loadFragment(Fragment fragment, int frameID) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(frameID, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}