package org.main.services;

public class AbstractGameElement<T extends AbstractGameElement.Info> {
    static class Info {
        int id;
        String name;
    }

    protected T info;

    @Override
    public String toString() {
        return info != null ? info.name : "null";
    }
}
