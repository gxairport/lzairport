package com.lzairport.ais.dao.impl;

import javax.ejb.Stateless;
import com.lzairport.ais.dao.IViewConfigDao;
import com.lzairport.ais.models.ViewConfig;

/**
 * 显示配置ViewConfig的Dao层接口的实现类
 * @author ZhangYu
 * @version 0.9a 24/06/14
 * @since JDK 1.6
 *
 */



@Stateless
public class ViewConfigDao  extends AodbDaoImpl<Integer, ViewConfig> implements IViewConfigDao {


	
}
