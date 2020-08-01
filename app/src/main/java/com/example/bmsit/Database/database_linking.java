package com.example.bmsit.Database;

import android.app.Application;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

public class database_linking {

    public class ContackApplication extends Application {


        public static final String APPLICATION_ID = "2E922EA9-24B1-C848-FF89-8B51C099E400";
        public static final String API_KEY = "B484254B-B41F-C1D1-FFD2-F2BE61C98900";
        public static final String SERVER_URL = "https://api.backendless.com";

        public  BackendlessUser user;

        @Override
        public void onCreate() {
            super.onCreate();

            Backendless.setUrl( SERVER_URL );
            Backendless.initApp( getApplicationContext(),
                    APPLICATION_ID,
                    API_KEY );

        }
    }

}
