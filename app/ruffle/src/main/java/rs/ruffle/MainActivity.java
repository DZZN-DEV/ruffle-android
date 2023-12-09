package rs.ruffle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestNoStatusBarFeature();
        setContentView(R.layout.activity_main);
        hideActionBar();

        try {
            // Load tboi.swf directly from the assets folder
            InputStream inputStream = getAssets().open("tboi.swf");
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            FullscreenNativeActivity.SWF_BYTES = bytes;

            // Start FullscreenNativeActivity
            startActivity(new Intent(MainActivity.this, FullscreenNativeActivity.class));
            finish(); // Optional: Finish the current activity after starting FullscreenNativeActivity
        } catch (IOException e) {
            // Handle IOException
            e.printStackTrace();
        }
    }

    private void requestNoStatusBarFeature() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
    }
}