package com.abhijeet.periodicworkrequestdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.widget.TextView;

import com.abhijeet.periodicworkrequestdemo.BackGroundWork.MyWork;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(MyWork.class, 3, TimeUnit.SECONDS)
                .setConstraints(Constraints.NONE)
                .build();

        WorkManager.getInstance().enqueueUniquePeriodicWork("Abhijeet", ExistingPeriodicWorkPolicy.KEEP, periodicWorkRequest);

        WorkManager.getInstance().getWorkInfoByIdLiveData(periodicWorkRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                ((TextView) findViewById(R.id.tvText)).append(workInfo.getId() + " ----" + workInfo.getState());

                if (workInfo != null && workInfo.getState().isFinished()) {
                }
            }
        });

    }
}
