package org.main.utils;


import com.squareup.moshi.Json;

public class PlatformWrapper extends AbstractGameWrapper<PlatformInfo> {

    public PlatformWrapper(PlatformInfo platformInfo) {
        this.platformInfo = platformInfo;
    }
    @Json(name = "platform")
    public PlatformInfo platformInfo;

    @Override
    public String toString() {
        return this.platformInfo.name;
    }
}
