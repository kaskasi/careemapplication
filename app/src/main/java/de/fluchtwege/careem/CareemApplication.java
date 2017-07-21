package de.fluchtwege.careem;

import android.app.Application;

import de.fluchtwege.careem.di.CareemModule;
import de.fluchtwege.careem.di.DaggerCareemComponent;
import de.fluchtwege.careem.di.CareemComponent;

public class CareemApplication extends Application {

    private CareemComponent careemComponent;

    private static CareemApplication careemApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        careemApplication = this;
        initDagger();
    }

    private void initDagger() {
        careemComponent = DaggerCareemComponent.builder()
                .careemModule(new CareemModule())
                .build();
    }

    public static CareemComponent getInjector() {
        return careemApplication.careemComponent;
    }
}
