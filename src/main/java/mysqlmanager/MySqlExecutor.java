package mysqlmanager;

import mysqlmanager.mapping.annotations.*;
import mysqlmanager.mapping.MySqlMapManager;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
public class MySqlExecutor {

    private final Connection connection;

    public MySqlExecutor(Connection connection) {
        this.connection = connection;
    }

    public MySqlExecutor createIndexIfNotExist(Class<?> entityClass) throws SQLException {
        checkMappedClass(entityClass);
        Statement statement = connection.createStatement();

        Entity entity = entityClass.getAnnotation(Entity.class);
        Field[] fields = entityClass.getDeclaredFields();

        SqlBuilder sqlBuilder = new SqlBuilder(statement);

        for (Field field : fields) {
            Column column = field.getAnnotation(Column.class);
            if (column == null) continue;

            sqlBuilder.createIndex(field, entity.name());
            sqlBuilder.execute();
        }
        return this;
    }

    public MySqlExecutor createTableIfNotExist(Class<?> entityClass) throws SQLException, IllegalStateException {

        checkMappedClass(entityClass);
        Statement statement = connection.createStatement();

        Entity entity = entityClass.getAnnotation(Entity.class);
        Field[] fields = entityClass.getDeclaredFields();

        SqlBuilder sqlBuilder = new SqlBuilder(statement);
        sqlBuilder.openCreateTable(entity.name());

        for (int index = 0; index < fields.length; index++) {

            Field field = fields[index];
            Column column = field.getAnnotation(Column.class);
            if (column == null) continue;

            sqlBuilder.createField(field);
            sqlBuilder.createPrimaryKey(field);
            sqlBuilder.createForeignKey(field);

            if (fields.length != index +1) sqlBuilder.addSeparator();
        }
        sqlBuilder.closeCreateTable();
        sqlBuilder.execute();
        return this;
    }

    private void checkMappedClass(Class<?> entity) {
        MySqlMapManager.getBy(entity);
    }

    private static class SqlBuilder {

        private StringBuilder sqlBuilder;
        private final Statement statement;

        public SqlBuilder(Statement statement) {
            sqlBuilder = new StringBuilder();
            this.statement = statement;
        }

        public void openCreateTable(String tableName) {
            sqlBuilder.append(String.format("CREATE TABLE %s (", tableName));
        }

        public void closeCreateTable() {
            sqlBuilder.append(");");
        }

        public void createField(Field field) throws IllegalStateException {

            mysqlmanager.mapping.annotations.Field f = field.getAnnotation(mysqlmanager.mapping.annotations.Field.class);
            NotNull nullable = field.getAnnotation(NotNull.class);

            if (f == null) throw new IllegalStateException(String.format("field %s is a column but not defined by @Field", field.getName()));

            sqlBuilder.append(String.format("%s %s(%s) %s ",
                    field.getName(),
                    f.type().name(),
                    f.size(),
                    nullable == null ? "" : "NOT NULL"
                    ));
        }

        public void createPrimaryKey(Field field) {
            if (field.getAnnotation(PrimaryKey.class) != null) sqlBuilder.append("PRIMARY KEY");
        }

        public void createForeignKey(Field field) throws IllegalStateException {
            ForeignKey fk = field.getAnnotation(ForeignKey.class);
            Reference ref = field.getAnnotation(Reference.class);

            if (fk == null) return;
            if (ref == null) throw new IllegalStateException(String.format("field %s is FK but does not use @Reference for referenced field", field.getName()));

            Class<?> entityClass = fk.table();
            Entity entity = entityClass.getAnnotation(Entity.class);

            sqlBuilder.append(String.format(", FOREIGN KEY (%s) REFERENCES %s(%s) ",
                    field.getName(),
                    entity.name(),
                    ref.field()));
        }

        public void createIndex(Field field, String tableName) {

            Index index = field.getAnnotation(Index.class);

            if (index == null) return;

            String name = index.name();
            if (name.equals("")) {
                name = String.format("IDX_%s_%s", tableName, field.getName().toUpperCase());
            }

            sqlBuilder.append(String.format("CREATE INDEX %s on %s(%s);", name, tableName, field.getName()));
        }

        public void addSeparator() {
            sqlBuilder.append(",");
        }

        public void execute() throws SQLException {
            if (sqlBuilder.toString().equals("")) return;

            statement.execute(sqlBuilder.toString());
            sqlBuilder = new StringBuilder();
        }
    }
}
