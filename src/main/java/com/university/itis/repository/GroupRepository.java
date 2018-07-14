package com.university.itis.repository;

import com.university.itis.model.Group;
import com.university.itis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findAllByUser(User user);

    List<Group> findAllByIdIn(List<Long> id);

    List<Group> findAllByParentId(Long parentId);

    List<Group> findAllByUserId(Long userId);

    List<Group> findAllByUserIdOrAdminId(Long userId, Long adminId);
}
