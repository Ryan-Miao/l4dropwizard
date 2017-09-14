package com.test.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.configuration.modules.GithubApiConfig;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Created by rmiao on 3/14/2017.
 */
public class HelloWorldConfiguration extends Configuration {

    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Stranger";

    @NotEmpty
    private Map<String, Object> hystrixConfig;

    @NotNull
    private GithubApiConfig githubApiConfig;

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }

    public Map<String, Object> getHystrixConfig() {
        return hystrixConfig;
    }

    public GithubApiConfig getGithubApiConfig() {
        return githubApiConfig;
    }
}
