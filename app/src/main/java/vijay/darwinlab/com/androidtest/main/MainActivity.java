package vijay.darwinlab.com.androidtest.main;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vijay.darwinlab.com.androidtest.R;
import vijay.darwinlab.com.androidtest.fragments.EnterPinFragment;
import vijay.darwinlab.com.androidtest.fragments.HomeFragment;
import vijay.darwinlab.com.androidtest.fragments.RecieveFragment;
import vijay.darwinlab.com.androidtest.fragments.SendFragment;

/**
 * Created by Vijay on 05-09-2017.
 */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, NavigationDrawerInterface {

    @BindView(R.id.menuTitle)
    TextView menuTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    @BindView(R.id.menuIcon)
    ImageView menuIcon;
    private Boolean isDrawerEnabled=false;
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initUiObjects();
        addFragmentLock();
    }

    private void addFragmentLock() {
        EnterPinFragment enterPinFragment = new EnterPinFragment();
        getSupportFragmentManager().beginTransaction().
                add(R.id.frameContainer, enterPinFragment).commit();
    }

    private void initUiObjects() {
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        navView.setNavigationItemSelectedListener(this);

    }


    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            new AlertDialog.Builder(this)
                    .setMessage(getResources().getString(R.string.confirmationMessage))
                    .setCancelable(false)
                    .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton(getResources().getString(R.string.no), null)
                    .show();

        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.send) {
            SendFragment sendFragment = new SendFragment();
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.frameContainer, sendFragment).addToBackStack("SendFragment").commit();
        } else if (id == R.id.recieve) {
            RecieveFragment recieveFragment = new RecieveFragment();
            getSupportFragmentManager().
                    beginTransaction().replace(R.id.frameContainer, recieveFragment).addToBackStack("RecieveFragment").commit();

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick(R.id.menuIcon)
    public void onViewClicked() {
        if (isDrawerEnabled) {
            drawerLayout.openDrawer(GravityCompat.START);
        } else {
            onBackPressed();
        }
    }
    @Override
    public void changeDrawerAttributes(Boolean visibilty, Drawable iconId, String titleTextId) {
        if (visibilty) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            toggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_UNLOCKED);
            toggle.syncState();
            isDrawerEnabled = true;
        } else {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            toggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            toggle.syncState();
            isDrawerEnabled = false;
        }
        menuIcon.setImageDrawable(iconId);
        menuTitle.setText(titleTextId);

        if (menuTitle.getText().toString().equalsIgnoreCase(getResources().getString(R.string.menuTitle))) {
            Spannable WordtoSpan = new SpannableString("LedgerEX");
            WordtoSpan.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.blue)), 6, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            menuTitle.setText(WordtoSpan);
        }
    }
}
