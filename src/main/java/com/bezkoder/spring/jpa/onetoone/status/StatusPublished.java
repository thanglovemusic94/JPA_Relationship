package com.bezkoder.spring.jpa.onetoone.status;

public enum StatusPublished {
    NO_PUBLISHED(0),
    PUBLISHED(1);

    private Integer id;

    private StatusPublished(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static StatusPublished valueOf(Integer id) {
        for (StatusPublished value : values())
            if (value.getId().equals(id))
                return value;
        return null;
    }
}
