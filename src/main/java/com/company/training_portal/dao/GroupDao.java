package com.company.training_portal.dao;

import com.company.training_portal.model.Group;

import java.util.Collection;

public interface GroupDao {

    Group findGroupByGroupId(Long groupId);

    Collection<Group> findGroupsByAuthorId(Long authorId);

    Collection<String> findAllGroupNames();

    Collection<String> findAllGroupNamesByAuthorId(Long authorId);

    Collection<Group> findAllGroups();

    Integer findStudentsNumberInGroup(Long groupId);

    Integer findGroupsNumberByAuthorId(Long authorId);

    Integer addGroup(Group group);

    void updateGroup(Group group);

    void deleteGroup(Integer groupId);
}
