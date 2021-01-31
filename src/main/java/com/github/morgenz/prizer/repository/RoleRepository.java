package com.github.morgenz.prizer.repository;

import com.github.morgenz.prizer.domain.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {
    public Role findByRoleName(String roleName);
}
