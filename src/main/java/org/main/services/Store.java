package org.main.services;

import com.squareup.moshi.Json;

public class Store extends AbstractGameElement<Store.StoreInfo>{

    @Json(name = "store")
    private Store.StoreInfo storeInfo;

    static class StoreInfo extends Info{}

    @Override
    public String toString() {
        return this.storeInfo.name;
    }
}