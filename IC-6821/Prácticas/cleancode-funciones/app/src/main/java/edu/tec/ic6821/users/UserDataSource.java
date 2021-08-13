package edu.tec.ic6821.users;

import java.util.List;

public interface UserDataSource {
    List<User> fetch();
}
