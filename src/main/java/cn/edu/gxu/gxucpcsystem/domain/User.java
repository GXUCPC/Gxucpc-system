package cn.edu.gxu.gxucpcsystem.domain;
/**
 * @author MaoMao
 * @Description MongoDB User 实体类
 * @create 2022-06-21 9:46 AM
 */
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serializable;
@SuppressWarnings("serial")
@Data
@Document(collection = "File") //指定实体对应MongoDB的哪一个集合
public class User implements Serializable {
    @Id
    private String _id;
    @Field("userName")
    private String userName;
    @Field("userId")
    private String userId;
    @Field("file_data")
    private byte[] files;
    @Field ("filename")
    private String fileName;
}