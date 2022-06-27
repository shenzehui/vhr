package org.javaboy.vhr.model;

import java.io.Serializable;

public class Nation implements Serializable {
    private Integer id;

    private String name;

    public Nation(String name) {
        this.name = name;
    }

    public Nation() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Nation nation = (Nation) o;

        return name.equals(nation.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}