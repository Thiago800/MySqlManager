import mysqlmanager.enums.Type;
import mysqlmanager.mapping.annotations.*;

@Entity(name = "user")
public class User {

    @Column
    @PrimaryKey
    @NotNull
    @Field(type = Type.INT)
    private int id;

    @Column
    @Field(type = Type.INT)
    @ForeignKey(table = Address.class)
    @Reference(field = "id")
    private int addressId;

    @Column
    @NotNull
    @Index
    @Field(type = Type.VARCHAR, size = 45)
    private String login;

    @Column
    @NotNull
    @Field(type = Type.VARCHAR, size = 45)
    private String password;
}
