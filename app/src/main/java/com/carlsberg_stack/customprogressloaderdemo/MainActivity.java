package com.carlsberg_stack.customprogressloaderdemo;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.carlsberg_stack.customp_progress_loader.CSProgress;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button indeterminate = (Button) findViewById(R.id.indeterminate);
        indeterminate.setOnClickListener(this);

        Button labelIndeterminate = (Button) findViewById(R.id.label_indeterminate);
        labelIndeterminate.setOnClickListener(this);

        Button detailIndeterminate = (Button) findViewById(R.id.detail_indeterminate);
        detailIndeterminate.setOnClickListener(this);

        Button determinate = (Button) findViewById(R.id.determinate);
        determinate.setOnClickListener(this);

        Button annularDeterminate = (Button) findViewById(R.id.annular_determinate);
        annularDeterminate.setOnClickListener(this);

        Button barDeterminate = (Button) findViewById(R.id.bar_determinate);
        barDeterminate.setOnClickListener(this);

        Button customView = (Button) findViewById(R.id.custom_view);
        customView.setOnClickListener(this);

        Button dimBackground = (Button) findViewById(R.id.dim_background);
        dimBackground.setOnClickListener(this);

        Button customColor = (Button) findViewById(R.id.custom_color_animate);
        customColor.setOnClickListener(this);
    }

    private CSProgress hud;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.indeterminate:
                hud = CSProgress.create(this)
                        .setStyle(CSProgress.Style.SPIN_INDETERMINATE);
                scheduleDismiss();
                break;
            case R.id.label_indeterminate:
                hud = CSProgress.create(this)
                        .setStyle(CSProgress.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setCancellable(true);
                scheduleDismiss();
                break;
            case R.id.detail_indeterminate:
                hud = CSProgress.create(this)
                        .setStyle(CSProgress.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setDetailsLabel("Downloading data");
                scheduleDismiss();
                break;
            case R.id.determinate:
                hud = CSProgress.create(MainActivity.this)
                        .setStyle(CSProgress.Style.PIE_DETERMINATE)
                        .setLabel("Please wait");
                simulateProgressUpdate();
                break;
            case R.id.annular_determinate:
                hud = CSProgress.create(MainActivity.this)
                        .setStyle(CSProgress.Style.ANNULAR_DETERMINATE)
                        .setLabel("Please wait");
                simulateProgressUpdate();
                break;
            case R.id.bar_determinate:
                hud = CSProgress.create(MainActivity.this)
                        .setStyle(CSProgress.Style.BAR_DETERMINATE)
                        .setLabel("Please wait");
                simulateProgressUpdate();
                break;
            case R.id.custom_view:
                ImageView imageView = new ImageView(this);
                imageView.setImageResource(R.mipmap.ic_launcher);
                hud = CSProgress.create(this)
                        .setCustomView(imageView)
                        .setLabel("This is a custom view");
                scheduleDismiss();
                break;
            case R.id.dim_background:
                hud = CSProgress.create(this)
                        .setStyle(CSProgress.Style.SPIN_INDETERMINATE)
                        .setDimAmount(0.5f);
                scheduleDismiss();
                break;
            case R.id.custom_color_animate:
                //noinspection deprecation
                hud = CSProgress.create(this)
                        .setStyle(CSProgress.Style.SPIN_INDETERMINATE)
                        .setWindowColor(getResources().getColor(R.color.colorPrimary))
                        .setAnimationSpeed(2);
                scheduleDismiss();
                break;
        }

        hud.show();
    }

    private void simulateProgressUpdate() {
        hud.setMaxProgress(100);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            int currentProgress;
            @Override
            public void run() {
                currentProgress += 1;
                hud.setProgress(currentProgress);
                if (currentProgress < 100) {
                    handler.postDelayed(this, 50);
                }
            }
        }, 100);
    }

    private void scheduleDismiss() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hud.dismiss();
            }
        }, 2000);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
