package com.lee.vocabulary.core.config.hibernate.namingstrategy;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CustomPhysicalNamingStrategy implements PhysicalNamingStrategy {
    @Override
    public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return convertToSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return convertToSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        Identifier identifier1 = replaceModelName(identifier);
        return convertToSnakeCase(identifier1);
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return convertToSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return convertToSnakeCase(identifier);
    }

    private Identifier replaceModelName(Identifier identifier) {
        if (identifier != null) {
            String newIdentifier = identifier.getText().replace("Model", "");
            return Identifier.toIdentifier(newIdentifier);
        }
        return null;
    }

    private Identifier convertToSnakeCase(Identifier identifier) {
        if (identifier != null) {
            String regex = "([a-z])([A-Z])";
            String replacement = "$1_$2";
            String newIdentifier = identifier.getText().replaceAll(regex, replacement).toLowerCase();
            return Identifier.toIdentifier(newIdentifier);
        }
        return null;
    }
}
