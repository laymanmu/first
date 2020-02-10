package com.example.springboot;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("User")
public class User implements Serializable {
    public enum Level { Apprentice, Adept, Expert, Master };

    private String id;
    private String name;
    private Level level;
    private int experience;

    public User() {
    }

    private User(Builder builder) {
        id = builder.id;
        name = builder.name;
        level = builder.level;
        experience = builder.experience;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public static final class Builder {
        private String id;
        private String name;
        private Level level;
        private int experience;

        public Builder() {
        }

        public Builder withId(String val) {
            id = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withLevel(Level val) {
            level = val;
            return this;
        }

        public Builder withExperience(int val) {
            experience = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
