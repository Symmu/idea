package vue_cli.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.xml.crypto.Data;

/**
 * @Auther:shuang
 * @Date:2021/2/22 - 02 - 22 - 12:36:02
 * @Description: vue_cli
 * @versin:1.0
 */
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class User {
    private String id;//
    private String name;//
    private Integer age;
    private Data bir;
}
