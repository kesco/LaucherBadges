package com.kescoode.android.system;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;

public class HWLauncherBadgeImpl implements LauncherBadge {
    protected static final String MARKER = "com.huawei.android.launcher";
    private final Context _ctx;
    private final boolean _isEMUI;

    protected HWLauncherBadgeImpl(@NonNull Context context) {
        _ctx = context;
        _isEMUI = Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP;
    }

    @SuppressLint("NewApi")
    @Override
    public void show(int num) {
        if (!_isEMUI) return;
        String launcherClassName = Tools.getLaunchActivityName(_ctx);
        if (launcherClassName == null) {
            return;
        }
        Bundle arg = new Bundle();
        arg.putString("package", _ctx.getPackageName());
        arg.putString("class", launcherClassName);
        arg.putInt("badgenumber", num);
        _ctx.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, arg);
    }

}
