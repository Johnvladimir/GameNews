package com.example.john.gamenews;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.john.gamenews.Clases.Utilidades_Tab;
import com.example.john.gamenews.Fragmentos.Fragment_LogOut;
import com.example.john.gamenews.Fragmentos.Fragment_Tab_css;
import com.example.john.gamenews.Fragmentos.Fragment_Tab_dota;
import com.example.john.gamenews.Fragmentos.Fragment_Tab_lol;
import com.example.john.gamenews.Fragmentos.Fragment_noticias;
import com.example.john.gamenews.Fragmentos.Fragments_Add_Tabs.Fragment_Tab_General;
import com.example.john.gamenews.Fragmentos.Fragments_Add_Tabs.Fragment_Tab_Image;
import com.example.john.gamenews.Fragmentos.Fragments_Add_Tabs.Fragment_Tab_Top_Players;

public class NavDraw extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        Fragment_noticias.OnFragmentInteractionListener,
        Fragment_Tab_lol.OnFragmentInteractionListener,
        Fragment_Tab_dota.OnFragmentInteractionListener,
        Fragment_Tab_css.OnFragmentInteractionListener,
        Fragment_Tab_General.OnFragmentInteractionListener,
        Fragment_Tab_Image.OnFragmentInteractionListener,
        Fragment_Tab_Top_Players.OnFragmentInteractionListener,
        Fragment_LogOut.OnFragmentInteractionListener {

    android.support.v4.app.Fragment miFragment;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_draw);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.nav_drawer);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        if (Utilidades_Tab.validarPantalla == true) {
            Fragment fragment = new Fragment_noticias();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenido, fragment).commit();
            Utilidades_Tab.validarPantalla = false;
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        drawerLayout = findViewById(R.id.nav_drawer);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        boolean fragmentseleccionado = false;

        if (id == R.id.nav_news) {
            miFragment = new Fragment_noticias();
            fragmentseleccionado = true;
        } else if (id == R.id.nav_lol) {
            miFragment = new Fragment_Tab_lol();
            fragmentseleccionado = true;
        } else if (id == R.id.nav_dota) {
            miFragment = new Fragment_Tab_dota();
            fragmentseleccionado = true;
        } else if (id == R.id.nav_cssgo) {
            miFragment = new Fragment_Tab_css();
            fragmentseleccionado = true;
        } else if (id == R.id.nav_logout) {
            miFragment = new Fragment_LogOut();
            fragmentseleccionado = true;
            Intent intent = new Intent(NavDraw.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        if (fragmentseleccionado) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenido, miFragment).commit();
        }

        drawerLayout = findViewById(R.id.nav_drawer);
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
