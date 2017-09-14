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
     * @param username
     * @return
     */
    @RequestLine("GET https://api.github.com/users/{username}")
    @Headers({"Accept: application/vnd.github.v3+json"})
    Observable<GithubUser> getUserProfile(@Param("username") String username);
}
