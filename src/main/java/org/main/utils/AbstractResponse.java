package org.main.utils;

import java.util.List;

public class AbstractResponse<T> {
    private List<T> results;

    public List<T> getResults() {
        return results;
    }
}
