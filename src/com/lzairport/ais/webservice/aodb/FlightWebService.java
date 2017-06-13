package com.lzairport.ais.webservice.aodb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.models.aodb.DynFlight;
import com.lzairport.ais.models.aodb.Flight;
import com.lzairport.ais.service.aodb.IDynFlightService;
import com.lzairport.ais.utils.XMLUtil;
import com.lzairport.ais.vo.FlightVO;
import com.lzairport.ais.vo.converter.IConverter;



@Stateless
@WebService
public class FlightWebService implements IFlightWebService {

	@EJB
	private IDynFlightService dynFlightService;
	
	@EJB(beanName="FlightConverter")
	private IConverter<Flight,FlightVO> converter;
	
	@WebMethod
	@Override
	public String findDynByCondition(String condistion) throws Exception {
		// TODO Auto-generated method stub
		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[]{DynFlight.FLIGHTNO,"<>",""});
		conditions.setFetchOneToMany("ALL");
		Document document = DocumentHelper.createDocument();
		//根节点
		Element  msgElement = document.addElement("MSG");
		msgElement.addElement("Head");
		Element bodyElement =  msgElement.addElement("Body");
		for(DynFlight flight:dynFlightService.findByConditionAll(conditions)){
			FlightVO vo = converter.getVOject(flight);
			bodyElement.addElement("Item").add(XMLUtil.objectToElement(vo));			
		}
		
		return document.asXML();
	}

}
