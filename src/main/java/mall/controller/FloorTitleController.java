package mall.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mall.entity.FloorTitle;
import mall.mapper.FloorTitleMapper;
import mall.service.FloorTitleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shuang
 * @since 2021-03-14
 */
@RestController
@RequestMapping("/home")
//@Api(value = "底部数据")
@Api(value = "首页", tags = "首页",description = "首页模块")
public class FloorTitleController {

    @Resource
    private FloorTitleMapper floorTitleMapper;

    @GetMapping("/floordata")
    @ApiOperation(value = "楼层")
    public List<FloorTitle> findFloorData(){
        return floorTitleMapper.floordata();
    }
}

