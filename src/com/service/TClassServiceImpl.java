 package com.service;

import com.dao.TClassDao;
import com.dao.TClassDaoImpl;
 
import com.orm.Course;
import com.orm.TClass;
import java.util.List;

 
public class TClassServiceImpl implements TClassService
{
    TClassDao dao = new TClassDaoImpl();

    
    public List<TClass> allSClass() {
        return dao.allSClass();
    }

    
    public TClass findByName(String name) 
    {
       return dao.queryUnique("from TClass as c where c.className = '"+name+"' ");
    }

    
    public TClass load(int id) {
        return dao.load(id);
    }

    
    public boolean addClasses(TClass classes) {
        try {
             dao.save(classes);
             return  true;
        } catch (Exception e) {
            System.out.println("添加班级失败");
            return  false;
        }
    }

    
    public boolean updateClasses(TClass classes) {
          try {
             dao.update(classes);
             return  true;
        } catch (Exception e) {
            System.out.println("更新班级失败");
            return  false;
        }
    }

    public boolean delete(TClass classes) {
        try {
            dao.delete(classes);
            return true;
        } catch (Exception e) {
        	System.out.println("更新班级失败");
            return false;
        }
    }
    
}
