package org.maria.enrollme;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ClientEntity {
    private int id;
    private String name;
    private String phone;
    private String socilaMedia;
    private String location;
}
