package milen.com.simplelibapp.realm.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import milen.com.simplelibapp.api.api_models.Post;

public class PostRealmObject extends RealmObject {

    @Required
    private Integer userId;
    @PrimaryKey
    private Integer id;
    @Required
    private String title;
    @Required
    private String body;

    public PostRealmObject() {

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


}
