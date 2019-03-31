/*
 * Created by Tareq Islam on 3/31/19 2:47 PM
 *
 *  Last modified 3/31/19 2:44 PM
 */

package com.mti.jobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComponentName componentName=new ComponentName(MainActivity.this,ExampleJobService.class);
                JobInfo jobInfo=new JobInfo.Builder(123, componentName)
                            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                            .setPersisted(true)
                            .setPeriodic(15*60*1000)
                            .build();
                JobScheduler scheduler= (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
               int resultCode= scheduler.schedule(jobInfo);
               if(resultCode==JobScheduler.RESULT_SUCCESS){
                   Log.d("Tareq" , "Job Scheduled from activity");
               }else{
                   Log.d("Tareq" , "Job Cancelled");
               }
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JobScheduler scheduler= (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
                scheduler.cancel(123);
                Log.d("Tareq" , "Job Cancelled");
            }
        });
    }
}
