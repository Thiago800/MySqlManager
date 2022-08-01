import mysqlmanager.enums.Type;
import mysqlmanager.mapping.annotations.*;

@Entity(name = "address")
public class Address {

    @Column
    @PrimaryKey
    @NotNull
    @Field(type = Type.INT)
    private int id;

    @Column
    @Index
    @Field(type = Type.VARCHAR, size = 45)
    private String street;

    @Column
    @Field(type = Type.VARCHAR, size = 10)
    private String number;
}
