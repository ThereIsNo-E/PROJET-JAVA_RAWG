package org.main.utils;


import com.squareup.moshi.Json;

public class PlatformWrapper extends AbstractGameWrapper<PlatformInfo> {

    @Json(name = "platform")
    public PlatformInfo platformInfo;

    @Override
    public String toString() {
        return this.platformInfo.name;
    }
}
