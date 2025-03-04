package br.com.dio.persistence.migration;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;

import br.com.dio.persistence.config.ConnectionConfig;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MigrationStrategy {
    private final Connection connection;

    public void executeMigration() {
        var originalOut = System.out;
        var originalErr = System.err;

        try (var fos = new FileOutputStream("liquibase.log")) {
            PrintStream printStream = new PrintStream(fos);
            System.setOut(printStream);
            System.setErr(printStream);

            try (var connection = ConnectionConfig.getConnection()) {
                var jdbcConnection = new JdbcConnection(connection);

                var liquibase = new Liquibase("db/changelog/db.changelog-master.yml",
                        new ClassLoaderResourceAccessor(),
                        jdbcConnection);

                liquibase.update();
            } catch (SQLException | LiquibaseException e) {
                e.printStackTrace(originalErr);
            }
        } catch (IOException ex) {
            ex.printStackTrace(originalErr);
        } finally {
            System.setOut(originalOut);
            System.setErr(originalErr);
        }
    }
}
