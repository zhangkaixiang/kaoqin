/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.orm.Score;
import java.util.List;

 
public interface ScoreDao {
    //加载一个课程

    public Score load(int id);

    //保存一个课程
    public void save(Score score);

    //根据条件查询s
    public List<Score> queryList(String queryString);
    
    //更新一个课程
    public void update(Score score);
    
    //sql查询
    @SuppressWarnings("rawtypes")
	public List sqlquery(String queryString);
}
