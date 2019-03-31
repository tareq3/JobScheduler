/*
 * Created by Tareq Islam on 3/31/19 2:49 PM
 *
 *  Last modified 3/31/19 2:49 PM
 */

package com.mti.jobscheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;
import android.widget.Toast;

/***
 * Created by mtita on 31,March,2019.
 */
public class ExampleJobService extends JobService {
    private static final String TAG="Tareq";
    private boolean jobCancelled=false;
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d("" +TAG, "Job Started");
        Toast.makeText(this, "LALa", Toast.LENGTH_SHORT).show();
        doBackGroundWork(params);
        return true;
    }

    private void doBackGroundWork(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<10 ;i++){

                    if(jobCancelled) return;
                    Log.d("" +TAG, "run:"+i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
                Log.d("" +TAG, "Job Finished");
                jobFinished(params, false);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d("" +TAG, "Job Cancelled");
        jobCancelled=true;
        return true;
    }
}
