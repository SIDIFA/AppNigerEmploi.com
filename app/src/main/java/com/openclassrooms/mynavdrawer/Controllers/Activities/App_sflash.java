package com.openclassrooms.mynavdrawer.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.openclassrooms.mynavdrawer.R;

public class App_sflash extends AppCompatActivity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash_screen);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(App_sflash.this,MainActivity.class);
                App_sflash.this.startActivity(mainIntent);
                App_sflash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}