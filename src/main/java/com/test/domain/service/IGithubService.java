package com.test.domain.service;

import com.test.domain.entiry.GithubUser;

/**
 * Created by Ryan Miao on 10/26/17.
 */
public interface IGithubService {

    /**
     * Get Github user profile.
     * @param username github username.
     */
    public GithubUser getUserProfile(String username);
}
