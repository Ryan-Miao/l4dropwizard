package com.test.domain.resource;

import com.miao.easyi18n.support.ResourceBundleMessageSource;
import com.test.domain.entiry.GithubUser;
import com.test.domain.service.IGithubService;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Ryan Miao on 11/20/17.
 */
public class GithubResourceTest {

    private static final IGithubService service = mock(IGithubService.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new GithubResource(service))
            .build();


    @Before
    public void setup() {

    }

    @After
    public void tearDown(){
        // we have to reset the mock after each test because of the
        // @ClassRule, or use a @Rule as mentioned below.
        reset(service);
    }

    @Test
    public void testGetPerson() {

        GithubUser user = new GithubUser();
        String name = "Ryan";
        user.setName(name);
        when(service.getUserProfile(anyString())).thenReturn(user);
        GithubUser githubUser = resources.target("/github/users/ryan-miao").request().get(GithubUser.class);
        assertEquals(name, githubUser.getName());
        verify(service).getUserProfile("ryan-miao");
    }

    @Test
    public void testI18n(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.addBasenames("i18n/messages2", "i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");

        String index = messageSource.getMessage("index", null, Locale.US);
        System.out.println(index);

    }

}