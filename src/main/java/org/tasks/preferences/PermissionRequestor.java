package org.tasks.preferences;

import android.Manifest;

public abstract class PermissionRequestor {
    public static final int REQUEST_FILE_WRITE = 50;
    public static final int REQUEST_CALENDAR = 51;
    public static final int REQUEST_MIC = 52;
    public static final int REQUEST_ACCOUNTS = 53;
    public static final int REQUEST_LOCATION = 54;
    public static final int REQUEST_CONTACTS = 55;

    private final PermissionChecker permissionChecker;

    public PermissionRequestor(PermissionChecker permissionChecker) {
        this.permissionChecker = permissionChecker;
    }

    public boolean requestMic() {
        if (permissionChecker.canAccessMic()) {
            return true;
        }
        requestPermission(Manifest.permission.RECORD_AUDIO, REQUEST_MIC);
        return false;
    }

    public boolean requestFileWritePermission() {
        if (permissionChecker.canWriteToExternalStorage()) {
            return true;
        }
        requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_FILE_WRITE);
        return false;
    }

    public boolean requestCalendarPermissions() {
        if (permissionChecker.canAccessCalendars()) {
            return true;
        }
        requestPermissions(
                new String[]{Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR},
                REQUEST_CALENDAR);
        return false;
    }

    public boolean requestAccountPermissions() {
        if (permissionChecker.canAccessAccounts()) {
            return true;
        }
        requestPermission(Manifest.permission.GET_ACCOUNTS, REQUEST_ACCOUNTS);
        return false;
    }

    public boolean requestFineLocation() {
        if (permissionChecker.canAccessLocation()) {
            return true;
        }
        requestPermission(Manifest.permission.ACCESS_FINE_LOCATION, REQUEST_LOCATION);
        return false;
    }

    public boolean requestMissedCallPermissions() {
        if (permissionChecker.canAccessMissedCallPermissions()) {
            return true;
        }
        requestPermissions(new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.READ_PHONE_STATE}, REQUEST_CONTACTS);
        return false;
    }

    private void requestPermission(String permission, int rc) {
        requestPermissions(new String[] {permission}, rc);
    }

    protected abstract void requestPermissions(String[] permissions, int requestCode);
}
