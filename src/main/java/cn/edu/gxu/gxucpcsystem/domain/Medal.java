package cn.edu.gxu.gxucpcsystem.domain;

/**
 * @author MaoMao
 * @Description MongoDB 实体类
 * @create 2022-09-05 11:06 AM
 */


import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;


@Data
@Document(collection = "FileTest") //指定实体对应MongoDB的哪一个集合
public class Medal implements Serializable {
    @Id
    private String _id;
    @Field("files_data")
    private byte[] files;
    @Field ("fileName")
    private String fileName;
    @Field("uploadDate")
    private long  Date;
    @Field("contestId")
    private int contestId;
    @Field("userName")
    private String name;
    @Field("filesize")
    private int size;
}
