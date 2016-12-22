package ka.ti.asma.univ8.notemyday;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;
    private boolean firstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Noter ma journée");

        if (savedInstanceState == null) {
            // Initialisation du fragment manager
            fragmentManager = getSupportFragmentManager();

            // afficher le premier fragment par défaut
            fragmentManager.beginTransaction().replace(R.id.content_frame, new MydayFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10001 && resultCode == this.RESULT_OK) {
            // recreate MydayFragment
            fragmentManager.beginTransaction().replace(R.id.content_frame, new MydayFragment()).commit();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        createFragment(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void createFragment(int id) {
        if (id == R.id.nav_fragment_myday) {
            // Affichage du fragment MydayFragment
            fragmentManager.beginTransaction().replace(R.id.content_frame, new MydayFragment()).commit();
        } else if (id == R.id.nav_fragment_days_history) {
            // Affichage du fragment DaysHistory
            fragmentManager.beginTransaction().replace(R.id.content_frame, new DaysHistory()).commit();
        } else if (id == R.id.nav_fragment_graphe) {
            // Affichage du fragment GrapheFragment
            fragmentManager.beginTransaction().replace(R.id.content_frame, new GrapheFragment()).commit();
        }else if (id == R.id.nav_fragment_Apropos) {
            // Affichage du fragment A prorpos
            fragmentManager.beginTransaction().replace(R.id.content_frame, new AproposFragment()).commit();
        }
    }
}
