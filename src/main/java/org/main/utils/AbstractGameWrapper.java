package org.main.utils;

public class AbstractGameWrapper<T extends AbstractGameWrapper.Info> {
    static class Info {
        int id;
        String name;

        public int getId() {
            return id;
        }


        public String getName() {
            return name;
        }

        @Override
        public String toString() { return name; }
    }

    protected T info;

    @Override
    public String toString() {
        return info != null ? info.name : "null";
    }
}
