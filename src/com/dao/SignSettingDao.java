 
package com.dao;

import com.orm.Sign;
import java.util.List;

public interface SignSettingDao 
{
    //根据条件查找
    public List<Sign> query(String queryString);
    //添加一条时间规则
    public void save(Sign signSetting);
    //加载一条时间规则
    public Sign load(int id);
    //修改一条时间规则
    public void saveOrUpdate(Sign signSetting);
    //删除一条时间规则
    public void delete(Sign sign);
}
