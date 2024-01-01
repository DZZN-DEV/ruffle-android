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

import com.erz.joysticklibrary.JoyStick;

public class MainActivity extends AppCompatActivity implements JoyStick.JoyStickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestNoStatusBarFeature();
        setContentView(R.layout.activity_main);
        hideActionBar();

        JoyStick joy1 = (JoyStick) findViewById(R.id.joy1);
        joy1.setListener(this);
        joy1.setPadColor(Color.parseColor("#55ffffff"));
        joy1.setButtonColor(Color.parseColor("#55ff0000"));

        JoyStick joy2 = (JoyStick) findViewById(R.id.joy2);
        joy2.setListener(this);
        joy2.enableStayPut(true);
        joy2.setPadBackground(R.drawable.pad);
        joy2.setButtonDrawable(R.drawable.button);

        try {
            // Load tboi.swf directly from the assets folder
            InputStream inputStream = getAssets().open("tboi.swf");
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            FullscreenNativeActivity.SWF_BYTES = bytes;

            // Start FullscreenNativeActivity
            startActivity(new Intent(MainActivity.this, FullscreenNativeActivity.class));
            
        } catch (IOException e) {
            // Handle IOException
            e.printStackTrace();
        }
    }

    @Override
    public void onMove(JoyStick joyStick, double angle, double power, int direction) {
        switch (joyStick.getId()) {
            case R.id.joy1:
                gameView.move(angle, power);
                break;
            case R.id.joy2:
                gameView.rotate(angle);
                break;
        }
    }

    @Override
    public void onTap() {

    }

    @Override
    public void onDoubleTap() {

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
