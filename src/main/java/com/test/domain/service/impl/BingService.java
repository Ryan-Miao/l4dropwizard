package com.test.domain.service.impl;

import com.test.domain.connect.BingClient;
import com.test.domain.service.IBingService;

import javax.inject.Inject;

/**
 * Created by Ryan Miao on 10/26/17.
 */
public class BingService implements IBingService {

    private BingClient bingClient;

    @Inject
    public BingService(BingClient bingClient) {
        this.bingClient = bingClient;
    }



    @Override
    public String getImages(int idx, int n) {
        return bingClient.getImgs(n, idx);
    }
}
