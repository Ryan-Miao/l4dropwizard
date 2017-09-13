package com.test.com.test.connect;

import com.test.com.test.entiry.GithubUser;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import rx.Observable;


/**
 * Created by ryan on 9/14/17.
 */
public interface GithubConnector {
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
     * @param username
     * @return
     */
    @RequestLine("GET https://api.github.com/users/{username}")
    @Headers({"Accept: application/vnd.github.v3+json"})
    Observable<GithubUser> getUserProfile(@Param("username") String username);
}
