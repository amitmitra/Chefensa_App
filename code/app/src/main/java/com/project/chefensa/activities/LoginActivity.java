package com.project.chefensa.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.AppEventsLogger;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.project.chefensa.R;
import com.project.chefensa.adapters.MenuListAdapter;
import com.project.chefensa.util.ChefensaUtil;

/*
Author: Amit Mitra
 */


/**
 * ************ IMPORTANT SETUP NOTES: ************
 * In order for Google+ sign in to work with your app, you must first go to:
 * https://developers.google.com/+/mobile/android/getting-started#step_1_enable_the_google_api
 * and follow the steps in "Step 1" to create an OAuth 2.0 client for your package.
 */
public class LoginActivity extends ActionBarActivity implements OnClickListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    private static final String TAG = "LoginActivity";
    private SharedPreferences prefs;
    private Context mContext;
    private View mLoginFormView;

    //facebook: start
    private final String PENDING_ACTION_BUNDLE_KEY = "com.facebook.samples.hellofacebook:PendingAction";
    private enum PendingAction {
        NONE,
        POST_PHOTO,
        POST_STATUS_UPDATE
    }
    private PendingAction pendingAction = PendingAction.NONE;
    private UiLifecycleHelper uiHelper;
    private GraphUser user;
    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(state, exception);
        }
    };
    //facebook:end

    //google: start
    private static final int RC_SIGN_IN = 0;
    private static final int PROFILE_PIC_SIZE = 400;
    private GoogleApiClient mGoogleApiClient;
    private boolean mIntentInProgress;
    private boolean mSignInClicked;
    private ConnectionResult mConnectionResult;
    private String mPersonName;
    private String mPersonPhotoUrl;
    private String mPersonGooglePlusProfile;
    private String mEmail;
    //google: end

    //drawer: start
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private ActionBar mActionBar;
    //drawer: end

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //facebook: start
        uiHelper = new UiLifecycleHelper(this, callback);
        uiHelper.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            String name = savedInstanceState.getString(PENDING_ACTION_BUNDLE_KEY);
            pendingAction = PendingAction.valueOf(name);
        }
        //facebook: end

        setContentView(R.layout.login_layout);
        prefs = getSharedPreferences(ChefensaUtil.CHEFENSA_SHARED_PREF, MODE_PRIVATE);
        mContext = this;

        //drawer: start
        mActionBar = getSupportActionBar();
        mActionBar.setTitle("Chefensa");
        mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffff4444")));
        //drawer: end

        //facebook: start
        ((LoginButton)findViewById(R.id.facebook_login_button)).setUserInfoChangedCallback(new LoginButton.UserInfoChangedCallback() {
            @Override
            public void onUserInfoFetched(GraphUser user) {
                LoginActivity.this.user = user;
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean(ChefensaUtil.IS_PERSON_LOGGEDIN_KEY, true);
                editor.putInt(ChefensaUtil.PERSON_LOGIN_TYPE_KEY, ChefensaUtil.LOGIN_TYPE_FACEBOOK);
                editor.apply();
                populateUIAfterLogin(R.id.facebook_login_button);
            }
        });
        //facebook: end

        //google: start
        mLoginFormView = findViewById(R.id.login_form);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerList = (ListView)findViewById(R.id.listview_drawer);
        (findViewById(R.id.plus_sign_in_button)).setOnClickListener(this);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();
        //google: end


        (findViewById(R.id.skip_bt)).setOnClickListener(this);
    }

    //facebook: start
    @Override
    protected void onResume() {
        super.onResume();
        uiHelper.onResume();
        AppEventsLogger.activateApp(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
        AppEventsLogger.deactivateApp(this);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);

        outState.putString(PENDING_ACTION_BUNDLE_KEY, pendingAction.name());
    }
    private void onSessionStateChange(SessionState state, Exception exception) {
        if (pendingAction != PendingAction.NONE &&
                (exception instanceof FacebookOperationCanceledException ||
                        exception instanceof FacebookAuthorizationException)) {
            new AlertDialog.Builder(this)
                    .setTitle("Cancelled")
                    .setMessage("Permission not granted")
                    .setPositiveButton("OK", null)
                    .show();
            pendingAction = PendingAction.NONE;
        } else if (state == SessionState.OPENED_TOKEN_UPDATED) {
            mPersonName = user.getName();
            mEmail = user.getId();
            updateUI(true);
        }
    }
    private void callFacebookLogout(Context c){
        Session session = Session.getActiveSession();
        if (session != null) {

            if (!session.isClosed()) {
                session.closeAndClearTokenInformation();
                //clear your preferences if saved
            }
        } else {

            session = new Session(c);
            Session.setActiveSession(session);

            session.closeAndClearTokenInformation();
            //clear your preferences if saved

        }

    }
    //facebook: end

    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.plus_sign_in_button:
                signInWithGplus();
                break;
        }
        populateUIAfterLogin(v.getId());
    }
    private void signInWithGplus() {
        if (!mGoogleApiClient.isConnecting()) {
            mSignInClicked = true;
            resolveSignInError();
        }
    }
    private void signOutFromGplus() {
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            mGoogleApiClient.disconnect();
            mGoogleApiClient.connect();
        }
    }
    private void resolveSignInError() {
        if (mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }
    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (!result.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this,
                    0).show();
            return;
        }
        if (!mIntentInProgress) {
            // Store the ConnectionResult for later usage
            mConnectionResult = result;

            if (mSignInClicked) {
                // The user has already clicked 'sign-in' so we attempt to
                // resolve all
                // errors until the user is signed in, or they cancel.
                resolveSignInError();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        if (requestCode == RC_SIGN_IN) {
            if (responseCode != RESULT_OK) {
                mSignInClicked = false;
            }
            mIntentInProgress = false;
            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        }
    }

    @Override
    public void onConnected(Bundle arg0) {
        mSignInClicked = false;
        Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG).show();

        // Get user's information
        getProfileInformation();

        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(ChefensaUtil.IS_PERSON_LOGGEDIN_KEY, true);
        editor.putInt(ChefensaUtil.PERSON_LOGIN_TYPE_KEY, ChefensaUtil.LOGIN_TYPE_GOOGLE);
        editor.apply();
        updateUI(true);

    }
    @Override
    public void onConnectionSuspended(int arg0) {
        mGoogleApiClient.connect();
        updateUI(false);
    }
    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            mLoginFormView.setVisibility(View.GONE);
            mDrawerLayout.setVisibility(View.VISIBLE);
        } else {
            mLoginFormView.setVisibility(View.VISIBLE);
            mDrawerLayout.setVisibility(View.GONE);
        }
    }

    private void populateUIAfterLogin(int id){
        String[] title = null;
        int[] icon = null;
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        switch (id){
            case R.id.plus_sign_in_button:
                title = new String[] {"My Profile", "My Orders", "About Us", "How It Works", "SignOut" };
                icon = new int[] { R.drawable.chefensa_logo, R.drawable.chefensa_logo,
                        R.drawable.chefensa_logo, R.drawable.chefensa_logo, R.drawable.chefensa_logo };
                break;
            case R.id.facebook_login_button:
                title = new String[] {"My Profile", "My Orders", "About Us", "How It Works", "SignOut" };
                icon = new int[] { R.drawable.chefensa_logo, R.drawable.chefensa_logo,
                        R.drawable.chefensa_logo, R.drawable.chefensa_logo, R.drawable.chefensa_logo };
                break;
            case R.id.skip_bt:
                title = new String[] {"About Us", "How It Works", "SignIn" };
                icon = new int[] { R.drawable.chefensa_logo, R.drawable.chefensa_logo, R.drawable.chefensa_logo};
                break;
        }
        mDrawerList.setAdapter(new MenuListAdapter(this, title, icon));
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent myProfileIntent = new Intent(mContext, MyProfileActivity.class);
                        myProfileIntent.putExtra("pname", mPersonName);
                        myProfileIntent.putExtra("photourl", mPersonPhotoUrl);
                        myProfileIntent.putExtra("profile", mPersonGooglePlusProfile);
                        myProfileIntent.putExtra("email", mEmail);
                        startActivity(myProfileIntent);
                        break;
                    case 4:
                        int loginType = prefs.getInt(ChefensaUtil.PERSON_LOGIN_TYPE_KEY, -1);
                        if(loginType == ChefensaUtil.LOGIN_TYPE_GOOGLE) {
                            signOutFromGplus();
                        } else if(loginType == ChefensaUtil.LOGIN_TYPE_FACEBOOK){
                            callFacebookLogout(mContext);
                        }
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString(ChefensaUtil.PERSON_LOGIN_TYPE_KEY, "");
                        editor.putBoolean(ChefensaUtil.IS_PERSON_LOGGEDIN_KEY, false);
                        editor.apply();
                        updateUI(false);
                        break;
                }
            }
        });
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer,R.string.drawer_open, R.string.drawer_close){
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        updateUI(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        if(mDrawerToggle != null) {
            mDrawerToggle.syncState();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        if(mDrawerToggle != null) {
            mDrawerToggle.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mActionBar.setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (item.getItemId() == android.R.id.home) {

            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.closeDrawer(mDrawerList);
            } else {
                mDrawerLayout.openDrawer(mDrawerList);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Fetching user's information name, email, profile pic
     * */
    private void getProfileInformation() {
        try {
            if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
                Person currentPerson = Plus.PeopleApi
                        .getCurrentPerson(mGoogleApiClient);
                mPersonName = currentPerson.getDisplayName();
                mPersonPhotoUrl = currentPerson.getImage().getUrl();
                mPersonGooglePlusProfile = currentPerson.getUrl();
                mEmail = Plus.AccountApi.getAccountName(mGoogleApiClient);
                Log.e(TAG, "Name: " + mPersonName + ", plusProfile: "
                        + mPersonGooglePlusProfile + ", email: " + mEmail
                        + ", Image: " + mPersonPhotoUrl);
                mPersonPhotoUrl = mPersonPhotoUrl.substring(0, mPersonPhotoUrl.length() - 2) + PROFILE_PIC_SIZE;

            } else {
                Toast.makeText(getApplicationContext(),
                        "Person information is null", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



