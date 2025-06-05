package com.project.management.project_management.infrastructure.adapter.out.persistance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.management.project_management.infrastructure.adapter.out.persistance.entity.UserEntity;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
  Optional<UserEntity> findByUsername(String username);

  Optional<UserEntity> findByEmail(String email);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);

  @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.ownedProjects WHERE u.id = :id")
  Optional<UserEntity> findByIdWithOwnedProjects(@Param("id") Long id);

  @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.memberProjects WHERE u.id = :id")
  Optional<UserEntity> findByIdWithMemberProjects(@Param("id") Long id);

  @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.assignedTasks WHERE u.id = :id")
  Optional<UserEntity> findByIdWithAssignedTasks(@Param("id") Long id);
}
