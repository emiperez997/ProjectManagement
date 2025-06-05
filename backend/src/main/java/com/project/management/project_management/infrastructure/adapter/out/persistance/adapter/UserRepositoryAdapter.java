package com.project.management.project_management.infrastructure.adapter.out.persistance.adapter;

import java.util.Optional;

import com.project.management.project_management.domain.model.User;
import com.project.management.project_management.domain.port.out.UserRepositoryPort;
import com.project.management.project_management.infrastructure.adapter.out.mapper.UserMapper;
import com.project.management.project_management.infrastructure.adapter.out.persistance.entity.UserEntity;
import com.project.management.project_management.infrastructure.adapter.out.persistance.repository.UserJpaRepository;

public class UserRepositoryAdapter implements UserRepositoryPort {
  private final UserJpaRepository userJpaRepository;
  private final UserMapper userMapper;

  public UserRepositoryAdapter(UserJpaRepository userJpaRepository, UserMapper userMapper) {
    this.userJpaRepository = userJpaRepository;
    this.userMapper = userMapper;
  }

  @Override
  public User save(User user) {
    UserEntity userEntity = userMapper.toEntity(user);
    UserEntity savedEntity = userJpaRepository.save(userEntity);
    return userMapper.toDomain(savedEntity);
  }

  @Override
  public Optional<User> findById(Long id) {
    return userJpaRepository.findById(id)
        .map(userMapper::toDomain);
  }

  @Override
  public Optional<User> findByUsername(String username) {
    return userJpaRepository.findByUsername(username)
        .map(userMapper::toDomain);
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return userJpaRepository.findByEmail(email)
        .map(userMapper::toDomain);
  }

  @Override
  public boolean existsByUsername(String username) {
    return userJpaRepository.existsByUsername(username);
  }

  @Override
  public boolean existsByEmail(String email) {
    return userJpaRepository.existsByEmail(email);
  }

  @Override
  public void deleteById(Long id) {
    userJpaRepository.deleteById(id);
  }

  // Additional methods for fetching with relationships
  public Optional<User> findByIdWithOwnedProjects(Long id) {
    return userJpaRepository.findByIdWithOwnedProjects(id)
        .map(userMapper::toDomain);
  }

  public Optional<User> findByIdWithMemberProjects(Long id) {
    return userJpaRepository.findByIdWithMemberProjects(id)
        .map(userMapper::toDomain);
  }

  public Optional<User> findByIdWithAssignedTasks(Long id) {
    return userJpaRepository.findByIdWithAssignedTasks(id)
        .map(userMapper::toDomain);
  }
}
