package com.kescoode.android.system;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

public class Tools {
    private Tools() {
        throw new UnsupportedOperationException("The class has no instance.");
    }

    public static
    @Nullable
    String getLaunchActivityName(@NonNull Context context) {
        PackageManager pManager = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> apps = pManager.queryIntentActivities(intent, 0);
        String actName = null;
        for (ResolveInfo app : apps) {
            if (app.activityInfo.applicationInfo.packageName.equalsIgnoreCase(context.getPackageName()))
                actName = app.activityInfo.name;
        }
        return actName;
    }

    public static
    String getHomePackageName(@NonNull Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        String launcherName = "";
        ResolveInfo resolveInfo = context.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfo != null && resolveInfo.activityInfo != null && resolveInfo.activityInfo.packageName != null) {
            launcherName = resolveInfo.activityInfo.packageName;
        }
        return launcherName;
    }
}
