package com.test.domain.service;

public interface IBingService {

    /**
     * Get bing image
     * @param idx 当前页
     * @param n   page size
     * @return images
     */
    String getImages(int idx, int n);
}
