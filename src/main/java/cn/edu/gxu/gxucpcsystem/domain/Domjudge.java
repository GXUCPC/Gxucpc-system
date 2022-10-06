package cn.edu.gxu.gxucpcsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Domjudge {
    private Integer id;
    private Player player;
    private String name;
    private String username;
    private String password;
    private Boolean isQuery;
}
