package com.example.homecaredoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {
    final Fragment currentTreatment = new CurrentTreatment();
    final Fragment treatmentHistory = new TreatmentHistory();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment activeFragment = currentTreatment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_main);

        // Set listener for navigation items
        BottomNavigationView navView = findViewById(R.id.nav_view);
        adjustGravity(navView);
        adjustWidth(navView);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.treatment:
                        fm.beginTransaction().hide(activeFragment).show(currentTreatment).commit();
                        activeFragment = currentTreatment;
                        return true;
                    case R.id.history:
                        fm.beginTransaction().hide(activeFragment).show(treatmentHistory).commit();
                        activeFragment = treatmentHistory;
                        return true;
                }
                return false;
            }
        });

        // Add all fragments but show only maps fragment
        fm.beginTransaction().add(R.id.nav_host_fragment, currentTreatment, "1").commit();
        fm.beginTransaction().add(R.id.nav_host_fragment, treatmentHistory, "2").hide(treatmentHistory).commit();
    }

    private static void adjustGravity(View v) {
            ViewGroup parent = (ViewGroup) v.getParent();
            parent.setPadding(0, 0, 0, 0);
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) parent.getLayoutParams();
            params.gravity = Gravity.CENTER;
            parent.setLayoutParams(params);
        if (v instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) v;
            for (int i = 0; i < vg.getChildCount(); i++) {
                adjustGravity(vg.getChildAt(i));
            }
        }
    }

    private static void adjustWidth(BottomNavigationView nav) {
        try {
            Field menuViewField = nav.getClass().getDeclaredField("mMenuView");
            menuViewField.setAccessible(true);
            Object menuView = menuViewField.get(nav);

            Field itemWidth = menuView.getClass().getDeclaredField("mActiveItemMaxWidth");
            itemWidth.setAccessible(true);
            itemWidth.setInt(menuView, Integer.MAX_VALUE);
        }
        catch (NoSuchFieldException e) {
            // TODO
        }
        catch (IllegalAccessException e) {
            // TODO
        }
    }
}
