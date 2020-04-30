package com.biblio.microservicefileserver;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mes-configs")
@RefreshScope
public class ApplicationPropertiesConfiguration {

    private String photo_dir;
    private String photo_postfix;

    public String getPhoto_postfix() {
        return photo_postfix;
    }

    public void setPhoto_postfix(String photo_postfix) {
        this.photo_postfix = photo_postfix;
    }

    public String getPhoto_dir() {
        return photo_dir;
    }

    public void setPhoto_dir(String photo_dir) {
        this.photo_dir = photo_dir;
    }
}
