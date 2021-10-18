package mall;

import org.apache.log4j.Logger;

/**
 * @Auther:shuang
 * @Date:2021/3/29 - 03 - 29 - 22:59:31
 * @Description: vue_cli
 * @versin:1.0
 */
public class Test {
    private static Logger logger= Logger.getLogger(Test.class); // 获取logger实例

    public static void main(String[] args) {
        logger.info("普通Info信息");
        logger.debug("调试debug信息");
    }
}
