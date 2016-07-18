package com.example.rest;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public final class EmployeeDTO {

    private String id;

    @Size(max = Employee.MAX_LENGTH_DESCRIPTION)
    private String name;

    @NotEmpty
    @Size(max = Employee.MAX_LENGTH_TITLE)
    private String title;

    public EmployeeDTO() {

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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String description) {
        this.name = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format(
                "EmployeeDTO[id=%s, description=%s, title=%s]",
                this.id,
                this.name,
                this.title
        );
    }
}
