package com.steam.kmicic.prizer.repository;

import com.steam.kmicic.prizer.domain.ApplicationUser;
import com.steam.kmicic.prizer.domain.Listing;
import com.steam.kmicic.prizer.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {
    public Role findByRoleName(String roleName);
}
