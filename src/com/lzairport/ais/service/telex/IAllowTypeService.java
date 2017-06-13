package com.lzairport.ais.service.telex;

import javax.ejb.Remote;

import com.lzairport.ais.models.telex.AllowType;
import com.lzairport.ais.service.IService;

/**
 * 电报所属类Service接口
 * @author ZhangYu
 * @version 0.9a 29/04/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IAllowTypeService extends IService<Integer, AllowType> {

	
}
