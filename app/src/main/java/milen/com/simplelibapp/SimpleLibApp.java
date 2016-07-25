package milen.com.simplelibapp;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class SimpleLibApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        //Realm configuration
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
