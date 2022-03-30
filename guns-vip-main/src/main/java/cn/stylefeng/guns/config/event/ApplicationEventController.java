package cn.stylefeng.guns.config.event;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by li wen ya on 2021/11/19
 */
@Api(description = "spring事件")
@RestController
@RequestMapping("/event")
public class ApplicationEventController {


    @Autowired
    private ApplicationEventPublisher context;

    @ApiOperation("发布")
    @GetMapping("/publisher")
    public void publisher(@ApiParam(value = "参数") @RequestParam String name){
        context.publishEvent(new MyApplicationEvent(name));
    }

}
