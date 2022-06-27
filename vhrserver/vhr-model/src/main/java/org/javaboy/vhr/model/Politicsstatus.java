package org.javaboy.vhr.model;

import java.io.Serializable;

public class Politicsstatus implements Serializable {
    private Integer id;

    private String name;

    public Politicsstatus(String name) {
        this.name = name;
    }

    public Politicsstatus() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Politicsstatus that = (Politicsstatus) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
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