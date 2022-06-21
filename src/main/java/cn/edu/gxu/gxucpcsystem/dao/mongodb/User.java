package cn.edu.gxu.gxucpcsystem.dao.mongodb;

/**
 * @author MaoMao
 * @Description MongoDB User 实体类
 * @create 2022-06-21 9:46 AM
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@SuppressWarnings("serial")

@Document(collection = "File") //指定实体对应MongoDB的哪一个集合
public class User implements Serializable {
    @Id
    private String _id;
    @Field("userName")
    private String userName;
    @Field("userId")
    private String userId;
    @Field("files_data")
    private byte[] files;
    @Field ("file_Name")
    private String fileName;
    public String getFileName(){
        return fileName;
    }
    public String getName(){
        return userName;
    }
    public String get_id() {
        return _id;
    }
    public String getUserId() {
        return userId;
    }
    public byte[] getFiles(){
        return files;
    }

}
//public class User implements Serializable {
//    @Id
//    private String _id;
//    @Field("name")
//    private String userName;
//    public String getId() {
//        return _id;
//    }
//    public String getName() {
//        return userName;
//    }
////    @Field("userId")
////    private String userId;
////    @Field("files_data")
////    private Byte[] files;
//
//}