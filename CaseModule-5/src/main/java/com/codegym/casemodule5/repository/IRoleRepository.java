package com.codegym.casemodule5.repository;

import com.codegym.casemodule5.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {
}
