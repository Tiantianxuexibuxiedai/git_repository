package cn.jiyun.dao;

import cn.jiyun.pojo.Brank;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface BrankMapper extends Mapper<Brank> {
    Page<Brank> find(String queryString);

    @Select("select * from brank where name like #{value}")
    String findn(String name);
}