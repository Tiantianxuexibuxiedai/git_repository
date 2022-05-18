package cn.jiyun.controller;

import cn.jiyun.entity.PageResult;
import cn.jiyun.entity.QueryPageBean;
import cn.jiyun.entity.Result;
import cn.jiyun.pojo.Brank;
import cn.jiyun.service.BrankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("one")
public class BrankController {
    @Autowired
    private BrankService brankService;
    @RequestMapping("findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return brankService.findPage(queryPageBean);
    }
    //添加添加添加
        //添加添加添加
    @RequestMapping("add")
    public Result add(@RequestBody Brank brank){
         String name=brankService.findn(brank.getName());
        try {
            if (name==null){
                brankService.add(brank);
                return new Result(true,"success");
            }
            return new Result(false,"名称重复");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"error");
        }
    }
    @RequestMapping("edit")
    public Result edit(@RequestBody Brank brank){
        try {
            brankService.edit(brank);
            return new Result(true,"success");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"error");
        }
    }
    @RequestMapping("del")
    public Result edit(Integer id){
        try {
            brankService.del(id);
            return new Result(true,"success");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"error");
        }
    }

}
