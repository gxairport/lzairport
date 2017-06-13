package com.lzairport.ais.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.IViewRelationDao;
import com.lzairport.ais.models.ViewRelation;
import com.lzairport.ais.service.IViewRelationService;

/**
 * 显示关联ViewRelation的Service层接口实现类
 * @author ZhangYu
 * @version 0.9a 01/05/15
 * @since JDK 1.6
 *
 */

@Stateless
public class ViewRelationService extends Service<Integer, ViewRelation>
		implements IViewRelationService {

	@EJB
	public void setViewRelationDao(IViewRelationDao viewRelationDao){
		setDao(viewRelationDao);
	}
}
