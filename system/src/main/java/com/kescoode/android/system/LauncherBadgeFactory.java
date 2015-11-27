package com.kescoode.android.system;

import android.content.Context;
import android.support.annotation.NonNull;

public class LauncherBadgeFactory {
    private final Context _ctx;

    public LauncherBadgeFactory(@NonNull Context _ctx) {
        this._ctx = _ctx;
    }

    public @NonNull LauncherBadge build() {
        switch (Tools.getHomePackageName(_ctx)) {
            case HWLauncherBadgeImpl.MARKER:
                return new HWLauncherBadgeImpl(_ctx);
            default:
                return new NonLauncherBadgeImpl();
        }
    }
}
