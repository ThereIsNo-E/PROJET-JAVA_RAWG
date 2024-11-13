package org.main.services;


import com.squareup.moshi.Json;

public class Platform extends AbstractGameElement<Platform.PlatformInfo>{

    @Json(name = "platform")
    private Platform.PlatformInfo platformInfo;

    static class PlatformInfo extends Info{}

    @Override
    public String toString() {
        return this.platformInfo.name;
    }
}
