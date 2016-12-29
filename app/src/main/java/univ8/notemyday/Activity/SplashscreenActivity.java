package univ8.notemyday.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import univ8.notemyday.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashscreenActivity extends Activity {

    private static boolean splashLoaded = false;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            if (!splashLoaded) {
                setContentView(R.layout.activity_splashscreen);
                int secondsDelayed = 1;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        startActivity(new Intent(SplashscreenActivity.this, MainActivity.class));
                        finish();
                    }
                }, secondsDelayed * 6000);

                splashLoaded = true;
            }
            else {
                Intent goToMainActivity = new Intent(SplashscreenActivity.this, MainActivity.class);
                goToMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(goToMainActivity);
                finish();
            }
        }
    }