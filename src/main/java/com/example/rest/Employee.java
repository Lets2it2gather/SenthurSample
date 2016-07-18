package com.example.rest;

import static com.example.util.PreCondition.isTrue;
import static com.example.util.PreCondition.notEmpty;
import static com.example.util.PreCondition.notNull;

import org.springframework.data.annotation.Id;

final class Employee {

    static final int MAX_LENGTH_NAME = 500;
    static final int MAX_LENGTH_TITLE = 100;

    @Id
    private String id;

    private String name;

    private String title;

    public Employee() {}

    private Employee(Builder builder) {
        this.name = builder.name;
        this.title = builder.title;
    }

    static Builder getBuilder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public void update(String title, String name) {
        checkTitleAndname(title, name);

        this.title = title;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "employee[id=%s, name=%s, title=%s]",
                this.id,
                this.name,
                this.title
        );
    }

    /**
     * We don't have to use the builder pattern here because the constructed class has only two String fields.
     * However, I use the builder pattern in this example because it makes the code a bit easier to read.
     */
    static class Builder {

        private String name;

        private String title;

        private Builder() {}

        Builder name(String name) {
            this.name = name;
            return this;
        }

        Builder title(String title) {
            this.title = title;
            return this;
        }

        Employee build() {
            Employee build = new Employee(this);

            build.checkTitleAndname(build.getTitle(), build.getName());

            return build;
        }
    }

    private void checkTitleAndname(String title, String name) {
        notNull(title, "Title cannot be null");
        notEmpty(title, "Title cannot be empty");
        isTrue(title.length() <= MAX_LENGTH_TITLE,
                "Title cannot be longer than %d characters",
                MAX_LENGTH_TITLE
        );

        if (name != null) {
            isTrue(name.length() <= MAX_LENGTH_NAME,
                    "name cannot be longer than %d characters",
                    MAX_LENGTH_NAME
            );
        }
    }
}
