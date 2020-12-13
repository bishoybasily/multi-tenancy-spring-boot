package com.gmail.bishoybasily.application.model.entity;

import com.gmail.bishoybasily.application.config.TenantBasedDataSource;
import lombok.Data;
import lombok.experimental.Accessors;
import org.flywaydb.core.Flyway;

/**
 * @author bishoybasily
 * @since 2020-12-12
 */
@Data
@Accessors(chain = true)
public class Tenant {

    private String id;

    public void provision() {
        Flyway.configure()
                .dataSource(TenantBasedDataSource.getDataSource(id))
                .baselineOnMigrate(true)
                .validateOnMigrate(false)
                .load()
                .migrate();
    }

}
