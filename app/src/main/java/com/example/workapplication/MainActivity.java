package com.example.workapplication;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.workapplication.databinding.ActivityMainBinding;
import com.example.workapplication.databinding.FragmentHomeBinding;
import com.example.workapplication.ui.WelcomeActivity;
import com.example.workapplication.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


import java.net.URI;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.b
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.send) {
            Intent intent = new Intent(this, PostPicture.class);
            startActivityForResult(intent, 7);
            return true;
        }
        if (id == R.id.user) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 7 && null != data) {
                String path = data.getStringExtra("path");
                String words = data.getStringExtra("words");
                Log.e("**res**", path + "|" + words);
                HomeFragment frg = (HomeFragment) getFragment(HomeFragment.class);
                frg.MessageSender(words,path);
                if (frg != null) {
                    Log.e("SSDDDTTT", frg.toString());
                } else {
                    Log.e("SSDDDTTT", "null");
                }
            }
        }
    }

    public Fragment getFragment(Class<?> clazz) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null && fragments.size() > 0) {
            NavHostFragment navHostFragment = (NavHostFragment) fragments.get(0);
            List<Fragment> childfragments = navHostFragment.getChildFragmentManager().getFragments();
            if (childfragments != null && childfragments.size() > 0) {
                for (int j = 0; j < childfragments.size(); j++) {
                    Fragment fragment = childfragments.get(j);
                    if (fragment.getClass().isAssignableFrom(clazz)) {
                        Log.i("FindFragment", "getFragment1: " + fragment);
                        return fragment;
                    }
                }
            }
        }
        return null;
    }
}

