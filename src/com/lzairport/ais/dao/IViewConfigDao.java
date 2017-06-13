package com.lzairport.ais.dao;


import javax.ejb.Local;
import com.lzairport.ais.models.ViewConfig;


/**
 * 显示配置ViewConfig的Dao层接口
 * @author ZhangYu
 * @version 0.9a 24/06/14
 * @since JDK 1.6
 *
 */

@Local
public interface IViewConfigDao extends IDao<Integer,ViewConfig>{
 

}