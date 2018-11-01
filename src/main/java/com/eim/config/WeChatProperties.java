package com.eim.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Zy on 2018/11/1.
 */
@Data
@ConfigurationProperties(prefix = "wechat")
@Component
public class WeChatProperties {

    private String mpAppId;

    private  String mpAppSecret;

}
