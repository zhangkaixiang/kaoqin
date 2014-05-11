package com.service;

 
import com.orm.TClass;
import java.util.List;

public interface TClassService 
{
    //添加
    public  boolean  addClasses(TClass classes);
    //更新
    public  boolean  updateClasses(TClass classes);
    
    //显示所有班级
    public List<TClass> allSClass();
    
    //根据名称加载班级
    public TClass findByName(String name);
    
    //根据id加载班级
    public TClass load(int id);
    //删除
    public boolean delete(TClass classes);
}
