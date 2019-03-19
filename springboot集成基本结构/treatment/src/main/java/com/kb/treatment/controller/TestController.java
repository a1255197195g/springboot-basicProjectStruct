package com.kb.treatment.controller;

import com.kb.treatment.util.response.Result;
import com.kb.treatment.util.response.ResultUtil;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@Api("swaggerTestController相关api")
public class TestController {

    @ApiOperation(value = "根据id查询学生的信息",notes = "查询数据库中某个学生的信息")
    @ApiImplicitParam(name ="id",value = "学生id",paramType = "path",required = true,dataType = "String")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户id",dataType = "String",paramType = "query",example = "1112")
    })
    @ApiResponses({
            @ApiResponse(code=400,message = "请求参数没有填好"),
            @ApiResponse(code=404,message="请求路径没有找到"),
            @ApiResponse(code=500,message="服务器内部错误")
    })
    @GetMapping(value = "/test")
    public Result test(  ) throws Exception {

        return ResultUtil.success("helloWorld");
    }

}
