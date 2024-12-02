package org.main.utils;

import com.squareup.moshi.Json;

public class StoreWrapper extends AbstractGameWrapper<StoreInfo> {

    public StoreWrapper(StoreInfo storeInfo) {
        this.storeInfo = storeInfo;
    }

    @Json(name = "store")
    public StoreInfo storeInfo;

    @Override
    public String toString() {
        return this.storeInfo.name;
    }
}