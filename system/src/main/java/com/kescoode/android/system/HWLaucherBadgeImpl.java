package com.kescoode.android.system;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import java.util.Iterator;

public class HWLaucherBadgeImpl implements LaucherBadge {
    private final Context _ctx;
    private final PackageManager _pManager;

    public HWLaucherBadgeImpl(Context context) {
        _ctx = context;
        _pManager = context.getPackageManager();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void show(int num) {
        String launcherClassName = getLaunchActivityName(_ctx);
        if (launcherClassName == null) {
            return;
        }
        Bundle localBundle = new Bundle();
        localBundle.putString("package", _ctx.getPackageName());
        localBundle.putString("class", launcherClassName);
        localBundle.putInt("badgenumber", num);
        _ctx.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, localBundle);
    }

    public static String getLaunchActivityName(Context context) {
        PackageManager localPackageManager = context.getPackageManager();
        Intent localIntent = new Intent("android.intent.action.MAIN");
        localIntent.addCategory("android.intent.category.LAUNCHER");
        try {
            Iterator<ResolveInfo> localIterator = localPackageManager.queryIntentActivities(localIntent, 0).iterator();
            while (localIterator.hasNext()) {
                ResolveInfo localResolveInfo = localIterator.next();
                if (!localResolveInfo.activityInfo.applicationInfo.packageName.equalsIgnoreCase(context.getPackageName()))
                    continue;
                String str = localResolveInfo.activityInfo.name;
                return str;
            }
        } catch (Exception localException) {
            return null;
        }
        return null;
    }
}
