package cn.edu.gxu.gxucpcsystem.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
@Document(collection = "image") //指定实体对应MongoDB的哪一个集合
public class ImageMedal {
    @Id
    private String _id;
    @Field("filesData")
    private byte[] files;
    @Field("fileURI")
    private String fileURI;
    @Field("fileName")
    private String fileName;
    @Field("uploadDate")
    private long Date;
    @Field("filesize")
    private int size;
    @Field("Md5")
    private String md5;
}
