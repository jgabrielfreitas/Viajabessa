package br.com.mobi.viajabessa.marketing;

import android.os.Build;

/**
 * Created by JGabrielFreitas on 25/02/15.
 */

public class PhoneInformations {

    private String androidVersion;
    private String phoneModel;
    private String phoneManufacturer;

    public PhoneInformations() {

        this.phoneModel = Build.MODEL;
        this.phoneManufacturer = Build.MANUFACTURER;
        this.androidVersion = Build.VERSION.RELEASE;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public String getPhoneManufacturer() {
        return phoneManufacturer;
    }

    public String toString() {
        return "/" + getAndroidVersion() + "/" + getPhoneModel()+ "/" + getPhoneManufacturer();
    }
}
