package com.abhijeet.periodicworkrequestdemo;

import android.app.Application;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.abhijeet.periodicworkrequestdemo.BackGroundWork.MyWork;

import java.util.concurrent.TimeUnit;

public class App extends Application{

    private static App mContext;

    public static App getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        initPeriodicWorker();
    }


    private void initPeriodicWorker() {
        WorkManager mWorkManager = WorkManager.getInstance();
       // mWorkManager.cancelAllWorkByTag("Abhijeet");

        PeriodicWorkRequest periodicBuilder =new  PeriodicWorkRequest.Builder(MyWork.class, 10, TimeUnit.SECONDS).addTag("Abhijeet").build();
        mWorkManager.enqueue(periodicBuilder);


//        mWorkManager.getWorkInfoByIdLiveData(periodicBuilder.getId()).observe(getContext(), new Observer<WorkInfo>() {
//            @Override
//            public void onChanged(WorkInfo workInfo) {
//                Log.d("MyWork---",workInfo.getState().name());
//            }
//        });
    }


}
