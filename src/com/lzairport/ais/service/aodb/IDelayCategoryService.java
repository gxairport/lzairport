package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;
import com.lzairport.ais.models.aodb.DelayCategory;
import com.lzairport.ais.service.IService;

/**
 * 延误类别的Service接口
 * @author ZhangYu
 * @version 0.9a 01/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IDelayCategoryService extends IService<Integer, DelayCategory> {

}
