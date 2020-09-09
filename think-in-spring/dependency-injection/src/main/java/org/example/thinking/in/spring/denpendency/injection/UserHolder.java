package org.example.thinking.in.spring.denpendency.injection;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.SuperUser;
import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;

/**
 * User 持有类
 * */
public class UserHolder {

    private User user;

    private SuperUser superUser;

    public UserHolder() {

    }

    public UserHolder(User user) {
        this.user = user;
    }

    public UserHolder(User user, SuperUser superUser) {
        this.user = user;
        this.superUser = superUser;
    }

    public User getUser() {
        return user;
    }

    public SuperUser getSuperUser() {
        return superUser;
    }

    public void setSuperUser(SuperUser superUser) {
        this.superUser = superUser;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", superUser=" + superUser +
                '}';
    }
}
