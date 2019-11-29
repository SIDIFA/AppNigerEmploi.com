package com.openclassrooms.mynavdrawer.Controllers.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SearchEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.openclassrooms.mynavdrawer.Controllers.Fragments.FragmentAnnonces;
import com.openclassrooms.mynavdrawer.Controllers.Fragments.FragmentCategories;
import com.openclassrooms.mynavdrawer.Controllers.Fragments.FragmentFavoris;
import com.openclassrooms.mynavdrawer.Controllers.Fragments.FragmentSearch;
import com.openclassrooms.mynavdrawer.R;

import static android.graphics.Color.WHITE;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //FOR DESIGN
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;

    //FOR FRAGMENTS
    private Fragment fragmentAnnonces;
    private Fragment fragmentCategories;
    private Fragment fragmentSearch;
    private Fragment fragmentFavoris;

    //FOR DATAS
    private static final int FRAGMENT_ANNONCES = 0;
    private static final int FRAGMENT_CATEGORIES = 1;
    private static final int FRAGMENT_SEARCH = 2;
    private static final int FRAGMENT_FAVORIS = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configure all views
        this.configureToolBar();
        this.configureDrawerLayout();
        this.configureNavigationView();

        // Show First Fragment
        this.showFirstFragment();
    }

    @Override
    public void onBackPressed() {
        // Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        // Show fragment after user clicked on a menu item
        switch (id){
            case R.id.activity_main_drawer_annonces:
                this.showFragment(FRAGMENT_ANNONCES);
                bottomNavigationView.getMenu().getItem(0).setChecked(true);
                break;
            case R.id.activity_main_drawer_categories:
                this.showFragment(FRAGMENT_CATEGORIES);
                bottomNavigationView.getMenu().getItem(1).setChecked(true);
                break;
            case R.id.activity_main_drawer_search:
                this.showFragment(FRAGMENT_SEARCH);
                bottomNavigationView.getMenu().getItem(2).setChecked(true);
                break;
            case R.id.activity_main_drawer_favoris:
                this.showFragment(FRAGMENT_FAVORIS);
                bottomNavigationView.getMenu().getItem(3).setChecked(true);
                break;
            case R.id.activity_main_drawer_facebook:
                Intent facebook = new Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com"));
                startActivity(facebook);
                break;
            case R.id.activity_main_drawer_linkedin:
                Intent linkedin = new Intent(Intent.ACTION_VIEW, Uri.parse("https://linkedin.com"));
                startActivity(linkedin);
                break;
            case R.id.contact:
                Intent contact = new Intent(MainActivity.this, Contact.class);
                startActivity(contact);
                break;
            case R.id.apropos:
                Intent apropos = new Intent(MainActivity.this, Apropos.class);
                startActivity(apropos);
                break;
            default:
                break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    // ---------------------
    // CONFIGURATION
    // ---------------------

    // Configure Toolbar
    private void configureToolBar(){
        this.toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        toolbar.setTitle("Annonces");
        setSupportActionBar(toolbar);
    }

    // Configure Drawer Layout
    private void configureDrawerLayout(){
        this.drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    // Configure NavigationView
    private void configureNavigationView(){
        this.navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        this.bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationBottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> updateMainFragment(item.getItemId()));
    }

    private boolean updateMainFragment(int id) {
        // Show fragment after user clicked on a menu item
        switch (id) {
            case R.id.activity_main_drawer_annonces:
                this.showFragment(FRAGMENT_ANNONCES);
                navigationView.getMenu().getItem(0).setChecked(true);
                break;
            case R.id.activity_main_drawer_categories:
                this.showFragment(FRAGMENT_CATEGORIES);
                navigationView.getMenu().getItem(1).setChecked(true);
                break;
            case R.id.activity_main_drawer_search:
                this.showFragment(FRAGMENT_SEARCH);
                navigationView.getMenu().getItem(2).setChecked(true);
                break;
            case R.id.activity_main_drawer_favoris:
                this.showFragment(FRAGMENT_FAVORIS);
                navigationView.getMenu().getItem(3).setChecked(true);
                break;
            default:
                break;
        }
        return true;
    }

    // ---------------------
    // FRAGMENTS
    // ---------------------

    // Show first fragment when activity is created
    private void showFirstFragment(){
        Fragment visibleFragment = getSupportFragmentManager().findFragmentById(R.id.activity_main_frame_layout);
        if (visibleFragment == null){
            // Show News Fragment
            this.showFragment(FRAGMENT_ANNONCES);
            // Mark as selected the menu item corresponding to FragmentAnnonces
            this.navigationView.getMenu().getItem(0).setChecked(true);
            this.bottomNavigationView.getMenu().getItem(0).setChecked(true);
        }
    }

    // Show fragment according an Identifier

    private void showFragment(int fragmentIdentifier){
        switch (fragmentIdentifier){
            case FRAGMENT_ANNONCES:
                this.showAnnoncesFragment();
                break;
            case FRAGMENT_CATEGORIES:
                this.showCategoriesFragment();
                break;
            case FRAGMENT_SEARCH:
                this.showSearchFragment();
                break;
            case FRAGMENT_FAVORIS:
                this.showFavorisFragment();
                break;
            default:
                break;
        }
    }

    // ---

    // Create each fragment page and show it

    private void showAnnoncesFragment(){
        if (this.fragmentAnnonces == null) this.fragmentAnnonces = FragmentAnnonces.newInstance();
        this.startTransactionFragment(this.fragmentAnnonces);
        toolbar.setTitle("Annonces");
    }

    private void showSearchFragment(){
        if (this.fragmentSearch == null) this.fragmentSearch = FragmentSearch.newInstance();
        this.startTransactionFragment(this.fragmentSearch);
        toolbar.setTitle("Recherche");
    }

    private void showCategoriesFragment(){
        if (this.fragmentCategories == null) this.fragmentCategories = FragmentCategories.newInstance();
        this.startTransactionFragment(this.fragmentCategories);
        toolbar.setTitle("Categories");
    }

    private void showFavorisFragment(){
        if (this.fragmentFavoris == null) this.fragmentFavoris = FragmentFavoris.newInstance();
        this.startTransactionFragment(this.fragmentFavoris);
        toolbar.setTitle("Favoris");
    }

    // ---

    // Generic method that will replace and show a fragment inside the MainActivity Frame Layout
    private void startTransactionFragment(Fragment fragment){
        if (!fragment.isVisible()){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_main_frame_layout, fragment).commit();
        }
    }
}