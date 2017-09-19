package com.test.domain.connect;

import com.test.domain.entiry.GithubUser;
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
    @RequestLine("GET /users/{username}")
    @Headers({"Accept: application/vnd.github.v3+json"})
    Observable<GithubUser> getUserProfile(@Param("username") String username);
}
