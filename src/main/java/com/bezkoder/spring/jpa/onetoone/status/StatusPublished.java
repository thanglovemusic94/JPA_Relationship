package com.bezkoder.spring.jpa.onetoone.status;

public enum StatusPublished {
    NO_PUBLISHED(0),
    PUBLISHED(1);

    private int id;

    private StatusPublished(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static StatusPublished valueOf(int id) {
        for (StatusPublished value : values())
            if (value.getId() == id)
                return value;
        return null;
    }
}
