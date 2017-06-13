package com.lzairport.ais.service;

import javax.ejb.Remote;

import com.lzairport.ais.models.ViewRelation;

/**
 * 显示关联ViewRelation的Service层接口
 * @author ZhangYu
 * @version 0.9a 01/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IViewRelationService extends IService<Integer, ViewRelation> {

}
