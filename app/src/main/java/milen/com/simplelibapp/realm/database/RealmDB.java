package milen.com.simplelibapp.realm.database;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class RealmDB {

    public RealmDB(){
    }

    public static Realm getRealmInstance() {
         return Realm.getDefaultInstance();
    }

    public <T extends RealmObject> T add(T model) {
        final Realm realm = getRealmInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
        return model;
    }
    public <T extends RealmObject> List<T> addAll(List<T> list) {
        final Realm realm = getRealmInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(list);
        realm.commitTransaction();
        return list;
    }

    public <T extends RealmObject> RealmResults<T> findAll(Class<T> clazz) {
        return getRealmInstance().where(clazz).findAll();
    }
    public <T extends RealmObject> RealmResults<T> findAllByProperty(Class<T> clazz, String propertyName, Object propertyValue) {
        if(propertyValue instanceof Integer) {
            return getRealmInstance().where(clazz).equalTo(propertyName, (Integer) propertyValue).findAll();
        }else if(propertyValue instanceof String){
            return getRealmInstance().where(clazz).equalTo(propertyName, (String) propertyValue).findAll();
        }else if(propertyValue instanceof Boolean){
            return getRealmInstance().where(clazz).equalTo(propertyName, (Boolean) propertyValue).findAll();
        }
        return null;
    }
    public <T extends RealmObject> RealmObject findFirst(Class<T> clazz) {
        return getRealmInstance().where(clazz).findFirst();
    }
    public <T extends RealmObject> boolean deleteAll(Class<T> modelClass) {
        final Realm realm = getRealmInstance();
        RealmResults<T> existingRecords = realm.where(modelClass).findAll();
//        We can use realm.executeTransaction() to
        if(existingRecords != null && existingRecords.size() > 0) {
            realm.beginTransaction();
            existingRecords.deleteAllFromRealm();
            realm.commitTransaction();
            return true;
        }
        return false;
    }
    public <T extends RealmObject> boolean deleteAllBy(Class<T> modelClass, String propertyName, Object propertyValue) {
        final Realm realm = getRealmInstance();
        RealmResults<T> existingRecords = null;
        if(propertyValue instanceof Integer) {
            existingRecords = getRealmInstance().where(modelClass).equalTo(propertyName, (Integer) propertyValue).findAll();
        }else if(propertyValue instanceof String){
            existingRecords = getRealmInstance().where(modelClass).equalTo(propertyName, (String) propertyValue).findAll();
        }else if(propertyValue instanceof Boolean){
            existingRecords = getRealmInstance().where(modelClass).equalTo(propertyName, (Boolean) propertyValue).findAll();
        }
        if(existingRecords != null && existingRecords.size() > 0){
            realm.beginTransaction();
            existingRecords.clear();
            realm.commitTransaction();
            return true;
        }
        return false;
    }
}
