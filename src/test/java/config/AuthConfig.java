package config;

import org.aeonbits.owner.Config;


@Config.Sources({"classpath:auth.properties"})
public interface AuthConfig extends Config {

    String userName();

    String password();

    String app();

    String device();

    String os_version();

    String browserStackUrl();







}

