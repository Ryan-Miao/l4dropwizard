package com.test.domain.service.impl;

import com.test.domain.connect.GithubClient;
import com.test.domain.entiry.GithubUser;
import com.test.domain.service.IGithubService;

import javax.inject.Inject;

/**
 * Created by Ryan Miao on 10/26/17.
 */
public class GithubService implements IGithubService {

    private GithubClient githubClient;

    @Inject
    public GithubService(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    @Override
    public GithubUser getUserProfile(String username) {
        return githubClient.getUserProfile(username).toBlocking().first();
    }
}
