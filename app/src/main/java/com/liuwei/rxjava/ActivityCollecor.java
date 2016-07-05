package com.liuwei.rxjava;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuwei on 16/7/5.
 */
public class ActivityCollecor {
    public static List<Activity> activitys = new ArrayList<Activity>();

    //add activity
    public static void addActicity(Activity activity) {
        activitys.add(activity);
    }

    //remove activity
    public static void removeActivity(Activity activity) {
        activitys.remove(activity);
    }

    //remover all activity
    public static void finishAll() {
        for (Activity activity : activitys) {
            if (activity != null && !activity.isFinishing()) {
                activity.finish();
            }
        }
    }

}
