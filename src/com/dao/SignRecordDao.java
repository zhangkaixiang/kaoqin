/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.orm.Signrecord;
import java.util.List;

public interface SignRecordDao {
   //加载一个 
    public Signrecord  load(int id);
    
    //保存一个 
    public void save(Signrecord course);
    
    //根据条件查询s
    public List<Signrecord> queryList(String queryString); 
    
    //SQL查询
    public List<Signrecord> sqlQueryList(String queryString);
}
