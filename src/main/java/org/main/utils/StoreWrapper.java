package org.main.utils;

import com.squareup.moshi.Json;

public class StoreWrapper extends AbstractGameWrapper<StoreInfo> {

    @Json(name = "store")
    private StoreInfo storeInfo;

    @Override
    public String toString() {
        return this.storeInfo.name;
    }
}