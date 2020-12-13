package com.gmail.bishoybasily.manager.model.repository;

import com.gmail.bishoybasily.manager.model.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bishoybasily
 * @since 2020-12-12
 */
public interface TenantsRepository extends JpaRepository<Tenant, String> {
}
