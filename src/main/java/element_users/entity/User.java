package element_users.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Auther:shuang
 * @Date:2021/2/26 - 02 - 26 - 13:44:24
 * @Description: vue_cli
 * @versin:1.0
 */
@Data
public class User {
    private String id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date bir;
    private String sex;
    private String address ;

}
