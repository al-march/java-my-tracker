package com.notter.rest.auth.model;

import com.notter.db.entity.UserEntity;
import lombok.Data;

@Data
public class User {
    Integer id;
    String name;
    String email;

    public static User toModel(UserEntity entity) {
        var u = new User();
        u.setId(entity.getId());
        u.setName(entity.getName());
        u.setEmail(entity.getEmail());

        return u;
    }
}
