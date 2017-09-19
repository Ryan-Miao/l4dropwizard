package com.test.domain.entiry;

/**
 * {
 "login": "Ryan-Miao",
 "id": 11866078,
 "avatar_url": "https://avatars3.githubusercontent.com/u/11866078?v=4",
 "gravatar_id": "",
 "url": "https://api.github.com/users/Ryan-Miao",
 "html_url": "https://github.com/Ryan-Miao",
 "followers_url": "https://api.github.com/users/Ryan-Miao/followers",
 "following_url": "https://api.github.com/users/Ryan-Miao/following{/other_user}",
 "gists_url": "https://api.github.com/users/Ryan-Miao/gists{/gist_id}",
 "starred_url": "https://api.github.com/users/Ryan-Miao/starred{/owner}{/repo}",
 "subscriptions_url": "https://api.github.com/users/Ryan-Miao/subscriptions",
 "organizations_url": "https://api.github.com/users/Ryan-Miao/orgs",
 "repos_url": "https://api.github.com/users/Ryan-Miao/repos",
 "events_url": "https://api.github.com/users/Ryan-Miao/events{/privacy}",
 "received_events_url": "https://api.github.com/users/Ryan-Miao/received_events",
 "type": "User",
 "site_admin": false,
 "name": "Ryan Miao",
 "company": "@ExpediaInc ",
 "blog": "https://ryan-miao.github.io/",
 "location": "中国深圳",
 "email": null,
 "hireable": true,
 "bio": "wind",
 "public_repos": 39,
 "public_gists": 0,
 "followers": 6,
 "following": 7,
 "created_at": "2015-04-09T06:16:22Z",
 "updated_at": "2017-07-19T01:24:07Z"
 }
 * Created by ryan on 9/14/17.
 */
public class GithubUser {
    private String login;
    private long id;
    private String avatar_url;
    private String url;
    private String name;
    private String email;
    private String location;
    private String blog;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }
}
