package org.adex;

import java.util.function.Consumer;

public class BuilderDemo {

    public static void main(String[] args) {

        User user = User.builder()
                .set($ -> $.name = "med")
                .set($ -> $.email = "med@gmail.com")
                .build();
        System.out.println(user);
    }
}

class User {
    private final String name;
    private final String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        String name;
        String email;

        public UserBuilder set(Consumer<UserBuilder> consumer) {
            consumer.accept(this);
            return this;
        }

        public User build() {
            return new User(name, email);
        }
    }

}