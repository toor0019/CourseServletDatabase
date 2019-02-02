/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto.builder;

import dto.Group;

/**
 *
 * @author gurki
 */
public class GroupBuilder {

    private final Group student = new Group();

    public GroupBuilder setName(String name) {
        student.setName(name);
        return this;
    }

    public GroupBuilder setId(int id) {
        student.setId(id);
        return this;
    }

    public GroupBuilder setId(String id) {
        return setId(Integer.valueOf(id));
    }

    public Group get() {
        return student;
    }
}
