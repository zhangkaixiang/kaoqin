 
package com.dao;

 
import com.orm.TClass;
import java.util.List;

 
public interface TClassDao 
{
    //列出全部班级
    public List<TClass> allSClass();
    //查询唯一结果
    public TClass queryUnique(String queryString);
    //添加班级
    public void  save(TClass tclass);
    //修改班级
     public void  update(TClass tclass);
   //删除班级
     public void  delete(TClass tclass);
    //加载班级
    public TClass load(int id);
}
