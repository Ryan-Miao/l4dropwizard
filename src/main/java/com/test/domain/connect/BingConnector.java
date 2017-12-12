package com.test.domain.connect;

import com.test.domain.entiry.GithubUser;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import rx.Observable;


/**
 * Created by ryan on 9/14/17.
 */
public interface BingConnector {

    @RequestLine("GET /HPImageArchive.aspx?format=js&idx={idx}&n={size}")
    String getBingImgs(@Param("idx") int idx, @Param("size") int size);
}
