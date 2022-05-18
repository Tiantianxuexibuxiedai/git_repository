package cn.jiyun.service;

import cn.jiyun.dao.BrankMapper;
import cn.jiyun.entity.PageResult;
import cn.jiyun.entity.QueryPageBean;
import cn.jiyun.pojo.Brank;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class BrankService {
    @Autowired
    private BrankMapper brankMapper;
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<Brank> p=brankMapper.find(queryPageBean.getQueryString());
        return new PageResult(p.getTotal(),p.getResult());
    }

    public String findn(String name) {
       return brankMapper.findn(name);
    }

    public void add(Brank brank) {
        brankMapper.insert(brank);
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("brank:"+brank.getId(), JSONObject.toJSONString(brank));
    }

    public void edit(Brank brank) {
        brankMapper.updateByPrimaryKeySelective(brank);
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("brank:"+brank.getId(), JSONObject.toJSONString(brank));
    }

    public void del(Integer id) {
        brankMapper.deleteByPrimaryKey(id);
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.del("brank:"+id);
    }
}
