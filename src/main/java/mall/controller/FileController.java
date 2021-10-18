package mall.controller;

import cn.hutool.core.io.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mall.vo.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther:shuang
 * @Date:2021/4/12 - 04 - 12 - 13:46:56
 * @Description: vue_cli
 * @versin:1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/files")
@Api(value = "图片", tags = "图片上传",description = "图片上传模块")
public class FileController {
    /**
     * 单文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation(value = "上传单张图片")
    public Result upload(MultipartFile file) {
        Result result =new Result();
        String filePath = System.getProperty("user.dir") + "/src/main/resources/static/upload_img/";
        String flag = System.currentTimeMillis() + "";
        String fileName = file.getOriginalFilename();
        try {
            FileUtil.writeBytes(file.getBytes(), filePath + flag + "-" + fileName);
            System.out.println(fileName + "--上传成功");
            System.out.println("result ========= " + result);
            result.setStatus(true);
            Thread.sleep(1L);
        } catch (Exception e) {
            System.err.println(fileName + "--文件上传失败");
            result.setStatus(false);
        }
        result.setMsg(flag);
        System.out.println("result = " + result);
        return result;
    }
}
