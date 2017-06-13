package com.lzairport.ais.dao.telex.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.dao.telex.ITelexDao;
import com.lzairport.ais.models.telex.TelexBO;

/**
 * 电报TelexBo的Dao层接口的实现类
 * @author ZhangYu
 * @version 0.9a 24/06/14
 * @since JDK 1.6
 *
 */

@Stateless
public class TelexDao extends AodbDaoImpl<Integer,TelexBO> implements ITelexDao  {
	
	
}
