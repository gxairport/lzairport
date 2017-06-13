package com.lzairport.ais.dialog;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Composite;
import com.lzairport.ais.dialog.selectionAdapter.DelayCategorySelectionAdapter;
import com.lzairport.ais.models.aodb.Aircraft;
import com.lzairport.ais.models.aodb.Airlines;
import com.lzairport.ais.models.aodb.Airport;
import com.lzairport.ais.models.aodb.AreaAttribute;
import com.lzairport.ais.models.aodb.DelayCategory;
import com.lzairport.ais.models.aodb.DelayReason;
import com.lzairport.ais.models.aodb.Flight;
import com.lzairport.ais.models.aodb.FlightState;
import com.lzairport.ais.models.aodb.FlightTask;
import com.lzairport.ais.models.aodb.StopFlight;
import com.lzairport.ais.service.IService;
import com.lzairport.ais.service.aodb.IAircraftService;
import com.lzairport.ais.service.aodb.IAirlinesService;
import com.lzairport.ais.service.aodb.IAirportService;
import com.lzairport.ais.service.aodb.IAreaAttributeService;
import com.lzairport.ais.service.aodb.IDelayCategoryService;
import com.lzairport.ais.service.aodb.IDelayReasonService;
import com.lzairport.ais.service.aodb.IFlightService;
import com.lzairport.ais.service.aodb.IFlightStateService;
import com.lzairport.ais.service.aodb.IFlightTaskService;
import com.lzairport.ais.utils.Ais_String_Util;
import com.lzairport.ais.utils.DateTimeUtil;
import com.lzairport.ais.utils.SYS_VARS;
import com.lzairport.ais.utils.SYS_VARS.OutIn;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.springframework.context.support.AbstractApplicationContext;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.custom.CTabItem;


class AirportModifyListener implements ModifyListener {
	

	
	private Combo cmbDepAirport;
	private Combo cmbArrAirport;
	private Combo cmbAttribute;
	private Combo cmbInOut;
	
	

	private IAirportService airportService;
	

	
	public AirportModifyListener(Combo cmbDepAirport, Combo cmbArrAirport,
			Combo cmbAttribute, Combo cmbInOut,IAirportService airportService) {
		super();
		this.cmbDepAirport = cmbDepAirport;
		this.cmbArrAirport = cmbArrAirport;
		this.cmbAttribute = cmbAttribute;
		this.cmbInOut = cmbInOut;
		this.airportService = airportService;
	}

	@Override
	public void modifyText(ModifyEvent e) {
		// TODO Auto-generated method stub
		Airport depAirport = airportService.
				findByFieldSingle(Airport.THREECHARCODE,cmbDepAirport.getText());
		Airport arrAirport = airportService.
				findByFieldSingle(Airport.THREECHARCODE,cmbArrAirport.getText());
		//如果起飞落地都有则进行设置，否则设置退出
		if ((depAirport != null)&&(arrAirport != null)){
			//设置航线性质
			if ((depAirport.getAreaAttribute().getCnShortName().equals("国际"))||
					(arrAirport.getAreaAttribute().getCnShortName().equals("国际"))){
				cmbAttribute.setText("INT");
			}else if ((depAirport.getAreaAttribute().getCnShortName().equals("地区"))||
					(arrAirport.getAreaAttribute().getCnShortName().equals("地区"))){
				cmbAttribute.setText("REG");
			}else{
				cmbAttribute.setText("DOM");
			}
			
			//设置进出性质
			if((depAirport.getThreeCharCode().equals(SYS_VARS.LocalAirportThreeCode))
					&&(arrAirport.getThreeCharCode().equals(SYS_VARS.LocalAirportThreeCode))){
				cmbInOut.setText("Srd");
			}else if(depAirport.getThreeCharCode().equals(SYS_VARS.LocalAirportThreeCode)){
				cmbInOut.setText("Dep");
			}else if(arrAirport.getThreeCharCode().equals(SYS_VARS.LocalAirportThreeCode)){
				cmbInOut.setText("Arr");
			}
			
		}
	}
}


/**
 * 内部类用于机号选择后
 * 自动设置航空公司
 *
 */
class AircraftModifyListener implements ModifyListener {

	private Combo cmbAirline;
	
	private Combo cmbCraftno;
	
	

	private IAircraftService aircraftService;

	public AircraftModifyListener(Combo cmbAirline, Combo cmbCraftno,
			IAircraftService aircraftService) {
		super();
		this.cmbAirline = cmbAirline;
		this.cmbCraftno = cmbCraftno;
		this.aircraftService = aircraftService;
	}



	@Override
	public void modifyText(ModifyEvent e) {
		
		Aircraft aircraft = aircraftService.findByFieldSingle
				(Aircraft.CRAFTNO, cmbCraftno.getText());
		if (aircraft.getCarrier().getAirlines() != null){
			cmbAirline.setText(aircraft.getCarrier().getAirlines().getCnShortName());
		}
		
	}

	
	
	
}



/**
 * 航班修改的对话窗口
 * 可用于动态、计划、历史航班的修改
 * @author ZhangYu
 * @version 0.9a 22/12/14
 * @since JDK 1.6
 *
 */

public class FlightEditDialog extends EditDialog {
	
	private Text txtFlightNo;
	private Text txtPlnTakeOff;
	private Text txtAlteraTeakeOffTime;
	private Text txtPlnLandIn;
	private Text txtAlterateLandinTime;
	private Text txtEquipmentInfo;
	private Text txtRouteInfo;
	private Text txtAlternateInfo;
	private Text txtOtherInfo;
	private Text txtRealTakeOff;
	private Text txtRealLandIn;
	private Text txtTakeOffLandInCount;
	private Text txtLink;
	private Text txtFlightTime;
	
	
	private Combo cmbTask;
	private Combo cmbCraftno;
	private Combo cmbInOut;
	private Combo cmbDepAirport;
	private Combo cmbArrAirport;
	private Combo cmbAttribute;
	private Combo cmbState;
	private Combo cmbAlternateAirport;
	private Combo cmbAirline;
	private Combo cmbCategory;
	private Combo cmbReason;
	private Combo cmbStart;
	private Combo cmbEnd;
	private Combo cmbStop1;
	private Combo cmbStop2;
	
	private DateTime dtExecDate ;
	
	private Button cbNormal;
	
	
	private boolean isNull;
	private Text txtStartTakeOff;
	private Text txtStop1LandIn;
	private Text txtStop1TakeOff;
	private Text txtStop2TakeOff;
	private Text txtEndLandIn;
	private Text txtStop2LandIn;
	
	
	private IFlightTaskService flightTaskService;
	
	private IAircraftService aircraftService;
	
	private IAirportService airportService;
		
	private IAirlinesService airlinesService;
	
	private IFlightStateService flightStateService;
	
	private IDelayCategoryService delayCategoryService;
	
	private IAreaAttributeService areaAttributeService;
	
	private IDelayReasonService delayReasonService;
	

	
    private AbstractApplicationContext ctx;
    private Text txtStart;
    private Text txtEnd;
    private Text txtTaskCn;
    private Text txtRouteAttrCn;
    private Text txtOutInCn;
    private Text text_5;
    private Text txtRoute;
    private Label labRoute;
    private Label labGate;
    private Combo cmbGate;
    private Label labCounter;
    private Combo cmbCounter;
    private Label labCraftType;
    private Group group_2;
    private Group group_3;
    private Text txtAlternateActualLandin;
    private Text txtAlternateAlteraTeakeOff;
    private Text txtAlternateActualTakeOff;
    private Button btnTra;
    private Button btnNight;
    private Text txtCraftType;
	
	/**
	 * 构造方法 
	 * @param parentShell Shell
	 * @param service 航班实体的Service
	 * @param object 航班实体
	 */

	public FlightEditDialog(IService<Integer,? extends Object> service, 
			Object object,AbstractApplicationContext ctx) {
		super(service, object);
		setBlockOnOpen(true);
		if (object != null){
			isNull = false;
		}else{
			isNull = true;
		}
		
		this.ctx = ctx;
		//初始化各种Service
		aircraftService  =  (IAircraftService) ctx.getBean("aircraftService");
	    airportService   =  (IAirportService) ctx.getBean("airportService");
		airlinesService  = (IAirlinesService) ctx.getBean("airlinesService");
		delayCategoryService = (IDelayCategoryService) ctx.getBean("delayCategoryService");
		areaAttributeService = (IAreaAttributeService) ctx.getBean("areaAttributeService");
		delayReasonService = (IDelayReasonService) ctx.getBean("delayReasonService");
		flightStateService = (IFlightStateService) ctx.getBean("flightStateService");
		flightTaskService = (IFlightTaskService) ctx.getBean("flightTaskService");
	
		
	}
	
	/**
	 * 初始化各Combo，给各Combo赋值
	 */
	private void initCmbItems(){
		
		//取得各所需要添加到Combo Items的数据
		List<FlightTask> tasks = flightTaskService.getAll();
		List<Airlines> airlines = airlinesService.getAll();
		List<Airport> airports = airportService.getAll();
		List<FlightState> flightStates = flightStateService.getAll();
		List<DelayCategory> categories =  delayCategoryService.getAll();
		List<AreaAttribute> areaAttributes = areaAttributeService.getAll();
		List<Aircraft> aircrafts = aircraftService.getAll();
	
		
		
		//给各Combo Items赋值
		for (FlightTask task:tasks){
			cmbTask.add(task.getCode());
		}
		for (Airport airport:airports){
			cmbDepAirport.add(airport.getThreeCharCode());
		}
		cmbArrAirport.setItems(cmbDepAirport.getItems());
		cmbStart.setItems(cmbDepAirport.getItems());
		cmbStop1.setItems(cmbDepAirport.getItems());
		cmbStop2.setItems(cmbDepAirport.getItems());
		cmbEnd.setItems(cmbDepAirport.getItems());
		cmbAlternateAirport.setItems(cmbDepAirport.getItems());
		for(Airlines airline:airlines){
			cmbAirline.add(airline.getCnShortName());
		}
		for(FlightState state:flightStates){
			cmbState.add(state.getCnShortName());
		}
		for(DelayCategory category:categories){
			cmbCategory.add(category.getCnShortName());
		}
		for(AreaAttribute areaAttribute:areaAttributes){
			cmbAttribute.add(areaAttribute.getCode());
			
		}
		for (Aircraft aircraft:aircrafts){
			cmbCraftno.add(aircraft.getCraftno());
		}
	
		
		cmbInOut.setItems(SYS_VARS.OutInCode.toArray(new String[SYS_VARS.OutInCode.size()]));
		
		cmbAttribute.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				txtRouteAttrCn.setText(areaAttributeService.findByFieldSingle(AreaAttribute.CODE,cmbAttribute.getText()).getCnShortName());
				
			}
		});
		
		cmbInOut.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				Integer index = SYS_VARS.OutInCode.indexOf(cmbInOut.getText());
				if (index  != -1){
					txtOutInCn.setText(SYS_VARS.OutInCn.get(index));
				}
			}
		});
	
		cmbTask.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				txtTaskCn.setText(flightTaskService.findByFieldSingle(FlightTask.CODE, cmbTask.getText()).getCnShortName());
			}
		});
		
		
	}

	@Override
	protected void objToDialog(Object object) {
		// TODO Auto-generated method stub
		if (object instanceof Flight) {
			//如果是航班实体，根据航班实体的数据填充对话框中的Txt、Combo
			Flight flight =  (Flight) object;
			
			cbNormal.setSelection(flight.isNormal());
			txtTakeOffLandInCount.setText(String.valueOf(flight.getTakeOffLandInCount()));
			//如果取值不为空，填充各txt
			if (flight.getFlightNO() != null){
				txtFlightNo.setText(flight.getFlightNO());
			}
			if (flight.getFlightTime() != null){
				txtFlightTime.setText(flight.getFlightTime());
			}
			if (flight.getLinkFlight() != null){
				txtLink.setText(flight.getLinkFlight().toString());
			}
			if (flight.getEquipmentInfo() != null){
				txtEquipmentInfo.setText(flight.getEquipmentInfo());
			}
			if(flight.getRouteInfo() != null){
				txtRouteInfo.setText(flight.getRouteInfo());
			}
			if(flight.getAlternateInfo() != null){
				txtAlternateInfo.setText(flight.getAlternateInfo());
			}
			if (flight.getOtherInfo() != null){
				txtOtherInfo.setText(flight.getOtherInfo());
			}
			if (flight.getPlanTakeOffTime() != null){
				txtPlnTakeOff.setText(DateTimeUtil.DateToStr(flight.getPlanTakeOffTime()));
			}
			if (flight.getPlanLandInTime() != null){
				txtPlnLandIn.setText(DateTimeUtil.DateToStr(flight.getPlanLandInTime()));
			}
			if(flight.getAlteraTeakeOffTime() != null){
				txtAlteraTeakeOffTime.setText(DateTimeUtil.DateToStr(flight.getAlteraTeakeOffTime()));
			}
			if (flight.getAlterateLandinTime() != null){
				txtAlterateLandinTime.setText(DateTimeUtil.DateToStr(flight.getAlterateLandinTime()));
			}
			if(flight.getActualTakeOffTime() != null){
				txtRealTakeOff.setText(DateTimeUtil.DateToStr(flight.getActualTakeOffTime()));
			}
			if(flight.getActualLandInTime() != null){
				txtRealLandIn.setText(DateTimeUtil.DateToStr(flight.getActualLandInTime()));
			}
			if(flight.getAlternateActualLandInTime() != null){
				txtAlternateActualLandin.setText(DateTimeUtil.DateToStr(flight.getAlternateActualLandInTime()));
			}
			if(flight.getAlternateAlteraTeakeOffTime() != null){
				txtAlternateAlteraTeakeOff.setText(DateTimeUtil.DateToStr(flight.getAlternateAlteraTeakeOffTime()));
			}
			if(flight.getAlternateActualTakeOffTime() != null){
				txtAlternateActualTakeOff.setText(DateTimeUtil.DateToStr(flight.getAlternateActualTakeOffTime()));
			}
			
			if(flight.getStartPlanTakeOffTime() != null){
				txtStartTakeOff.setText(flight.getStartPlanTakeOffTime());
			}

			if (flight.getTerminalPlanLandInTime() != null){
				txtEndLandIn.setText(flight.getTerminalPlanLandInTime());
			}
			if(flight.getRoute() != null){
				txtRoute.setText(flight.getRoute());
			}
			
			//如果取值不为空，填充各Combo			
			if (flight.getTask() != null){
				cmbTask.setText(flight.getTask().getCode());
				//txtTaskCn.setText(flight.getTask().getCnShortName());
			}
			if (flight.getAircraft() !=null){
				cmbCraftno.setText(flight.getAircraft().getCraftno());
				txtCraftType.setText(flight.getAircraft().getCraftType().getCode());
			}
			if (flight.getDepAirport() != null){
				cmbDepAirport.setText(flight.getDepAirport().getThreeCharCode());
			}
			if (flight.getArrAirport() != null){
				cmbArrAirport.setText(flight.getArrAirport().getThreeCharCode());
			}
			if (flight.getState() != null){
				cmbState.setText(flight.getState().getCnShortName());
			}
			if (flight.getAttribute() != null){
				cmbAttribute.setText(flight.getAttribute().getCode());
				//txtRouteAttrCn.setText(flight.getAttribute().getCnShortName());
			}
			if (flight.getStartAirport() != null){
				cmbStart.setText(flight.getStartAirport().getThreeCharCode());
			}
			if (flight.getTerminalAirport() != null){
				cmbEnd.setText(flight.getTerminalAirport().getThreeCharCode());
			}
			if(flight.getAlternateAirport() != null){
				cmbAlternateAirport.setText(flight.getAlternateAirport().getThreeCharCode());
			}
			if(flight.getAirlines() !=null){
				cmbAirline.setText(flight.getAirlines().getCnShortName());
			}
		
			
			if(flight.getStopFlights() != null){
				for (StopFlight stop:flight.getStopFlights()){
					
					if (stop.getOrderCode() == 0){
						
						cmbStop1.setText(stop.getStopAirport().getThreeCharCode());
						if (stop.getPlanTakeOffTime() != null){
							txtStop1TakeOff.setText(stop.getPlanTakeOffTime());
						}
						if (stop.getPlanLandInTime() != null){
							txtStop1LandIn.setText(stop.getPlanLandInTime());
						}						
					}

					if (stop.getOrderCode() == 1){
						
						cmbStop2.setText(stop.getStopAirport().getThreeCharCode());
						if (stop.getPlanTakeOffTime() != null){
							txtStop2TakeOff.setText(stop.getPlanTakeOffTime());
						}
						if (stop.getPlanLandInTime() != null){
							txtStop2LandIn.setText(stop.getPlanLandInTime());
						}
						
					}

				}
			}
			
			if ((flight.getIsOutIn() != null)&&(flight.getIsOutIn().ordinal() >=0)){
				cmbInOut.setText(SYS_VARS.OutInCode.get(flight.getIsOutIn().ordinal()));
				//txtOutInCn.setText(SYS_VARS.OutInCn.get(flight.getIsOutIn().ordinal()));
				
			}
			
			if (flight.getIsOutIn() != null && flight.getIsOutIn().equals(OutIn.Arr)){
				cmbGate.setEnabled(false);
				cmbCounter.setEnabled(false);
			}
			
			
			if (flight.getInternalDelayReason() != null){
				//设置延误类别的Combo
				cmbCategory.setText(flight.getInternalDelayReason().getDelayCategory().getCnShortName());
				//取得该延误类别的所有的延误原因，加入延误原因的Combo 并填充Flight的延误原因
				List<DelayReason>  reasons = delayReasonService.findByFieldAll
						(DelayReason.DELAYCATEGORY,flight.getInternalDelayReason().getDelayCategory());
				
				for(DelayReason reason:reasons){
					cmbReason.add(reason.getCnShortName());
				}
				cmbReason.setText(flight.getInternalDelayReason().getCnShortName());
			}
			
			if(flight.getExecDate() != null){
				 Calendar cal = Calendar.getInstance(); 
				 cal.setTime(flight.getExecDate());
				 dtExecDate.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
			}
			
		}
	}


	@Override
	protected void dialogToObj(Object object) throws Exception {
		// TODO Auto-generated method stub
		

		if (object instanceof Flight) {
			Flight flight = (Flight) object;

	
			//直接进行Set的字段，因为开销比较小
			flight.setFlightNO(txtFlightNo.getText());
			flight.setNormal(cbNormal.getSelection());
			flight.setStartPlanTakeOffTime(txtStartTakeOff.getText());
			flight.setTerminalPlanLandInTime(txtEndLandIn.getText());
			flight.setPlanLandInTime(DateTimeUtil.strToDate(txtPlnLandIn.getText()));
			flight.setPlanTakeOffTime(DateTimeUtil.strToDate(txtPlnTakeOff.getText()));
			flight.setAlteraTeakeOffTime(DateTimeUtil.strToDate(txtAlteraTeakeOffTime.getText()));
			flight.setAlterateLandinTime(DateTimeUtil.strToDate(txtAlterateLandinTime.getText()));
			flight.setAlteraTeakeOffTime(DateTimeUtil.strToDate(txtAlteraTeakeOffTime.getText()));
			flight.setAlternateActualLandInTime(DateTimeUtil.strToDate(txtAlternateActualLandin.getText()));
			flight.setAlternateAlteraTeakeOffTime(DateTimeUtil.strToDate(txtAlternateAlteraTeakeOff.getText()));
			flight.setAlternateActualTakeOffTime(DateTimeUtil.strToDate(txtAlternateActualTakeOff.getText()));
			flight.setActualLandInTime(DateTimeUtil.strToDate(txtRealLandIn.getText()));
			flight.setActualTakeOffTime(DateTimeUtil.strToDate(txtRealTakeOff.getText()));
			flight.setFlightTime(txtFlightTime.getText());
			flight.setEquipmentInfo(txtEquipmentInfo.getText());
			flight.setRouteInfo(txtRouteInfo.getText());
			flight.setAlternateInfo(txtAlternateInfo.getText());
			flight.setOtherInfo(txtOtherInfo.getText());
			//设置日期
			Calendar cal = Calendar.getInstance();
			cal.set(dtExecDate.getYear(), dtExecDate.getMonth(), dtExecDate.getDay(),0,0,0);
			flight.setExecDate(cal.getTime());
			if ((txtTakeOffLandInCount.getText() ==null)||(Ais_String_Util.isNumeric(txtTakeOffLandInCount.getText()))){
				flight.setTakeOffLandInCount(0);
			}else{
				flight.setTakeOffLandInCount(Integer.parseInt(txtTakeOffLandInCount.getText()));
			}

			//如果在SYS_VARS_Util.OutInCn中有Combo的值，则进行设置
			if ((cmbInOut.getText() != null) &&(SYS_VARS.OutInCode.indexOf(cmbInOut.getText()) != -1)){
				flight.setIsOutIn(OutIn.values()[SYS_VARS.OutInCode.indexOf(cmbInOut.getText())]);
			}
			
			//根据Combo的值与航班实体类的属性进行对比，如果发生改变，则进行Set
			if (((flight.getTask() != null)&&!flight.getTask().getCode().equals(cmbTask.getText()))
				||((flight.getTask() == null)&&(cmbTask.getText()!= null))){
				flight.setTask(flightTaskService.findByFieldSingle(FlightTask.CODE,cmbTask.getText()));
			}
			if (((flight.getAircraft() != null)&&!flight.getAircraft().getCraftno().equals(cmbCraftno.getText()))
					||((flight.getAircraft() == null)&&(cmbCraftno.getText()!= null))){
				flight.setAircraft(aircraftService.findByFieldSingle(Aircraft.CRAFTNO, cmbCraftno.getText()));
			}
			if (((flight.getState() != null)&&!flight.getState().getCnShortName().equals(cmbState.getText()))
					||((flight.getState() == null)&&(cmbState.getText()!= null))){
				flight.setState(flightStateService.findByFieldSingle(FlightState.CNSHORTNAME, cmbState.getText()));
			}
			if (((flight.getAttribute() != null)&&!flight.getAttribute().getCode().equals(cmbAttribute.getText()))
					||((flight.getAttribute() == null)&&(cmbAttribute.getText()!= null))){
				flight.setAttribute(areaAttributeService.findByFieldSingle(AreaAttribute.CODE, cmbAttribute.getText()));
			}
			if (((flight.getInternalDelayReason() != null)&&!flight.getInternalDelayReason().getCnShortName().equals(cmbReason.getText()))
					||((flight.getInternalDelayReason() == null)&&(cmbReason.getText()!= null))){
				flight.setInternalDelayReason(delayReasonService.findByFieldSingle(DelayReason.CNSHORTNAME, cmbReason.getText()));
			}
			if (((flight.getDepAirport() != null)&&!flight.getDepAirport().getThreeCharCode().equals(cmbDepAirport.getText()))
					||((flight.getDepAirport() == null)&&(cmbDepAirport.getText()!= null))){
				flight.setDepAirport(airportService.findByFieldSingle(Airport.THREECHARCODE,cmbDepAirport.getText()));
			}
			if (((flight.getArrAirport() != null)&&!flight.getArrAirport().getThreeCharCode().equals(cmbArrAirport.getText()))
					||((flight.getArrAirport() == null)&&(cmbArrAirport.getText()!= null))){
				flight.setArrAirport(airportService.findByFieldSingle(Airport.THREECHARCODE,cmbArrAirport.getText()));
			}
			if (((flight.getStartAirport() != null)&&!flight.getStartAirport().getThreeCharCode().equals(cmbStart.getText()))
					||((flight.getStartAirport() == null)&&(cmbStart.getText()!= null))){
				flight.setStartAirport(airportService.findByFieldSingle(Airport.THREECHARCODE,cmbStart.getText()));
			}
			if (((flight.getTerminalAirport() != null)&&!flight.getTerminalAirport().getThreeCharCode().equals(cmbEnd.getText()))
					||((flight.getTerminalAirport() == null)&&(cmbEnd.getText()!= null))){
				flight.setTerminalAirport(airportService.findByFieldSingle(Airport.THREECHARCODE,cmbEnd.getText()));
			}
			if (((flight.getAlternateAirport() != null)&&!flight.getAlternateAirport().getThreeCharCode().equals(cmbAlternateAirport.getText()))
					||((flight.getAlternateAirport() == null)&&(cmbAlternateAirport.getText()!= null))){
				flight.setAlternateAirport(airportService.findByFieldSingle(Airport.THREECHARCODE,cmbAlternateAirport.getText()));
			}
			if (((flight.getAirlines() != null)&&!flight.getAirlines().getCnShortName().equals(cmbAirline.getText()))
					||((flight.getAirlines() == null)&&(cmbAirline.getText()!= null))){
				flight.setAirlines(airlinesService.findByFieldSingle(Airlines.CNSHORTNAME, cmbAirline.getText()));
			}
		
			
			if (isNull){
				((IFlightService<?, ?>)service).createFlightDisPatchs(flight);
				
			}

			
			//处理经停航线，忽略开销问题
			Airport stop1 = airportService.findByFieldSingle(Airport.THREECHARCODE,cmbStop1.getText());
			Airport stop2 = airportService.findByFieldSingle(Airport.THREECHARCODE,cmbStop2.getText());
			
			Set<StopFlight> stops = new HashSet<StopFlight>();
			int orderCode = 0;
			if (stop1 != null){
				StopFlight stopFlight = new StopFlight();
				stopFlight.setStopAirport(stop1);
				stopFlight.setPlanTakeOffTime(txtStop1TakeOff.getText());
				stopFlight.setPlanLandInTime(txtStop1LandIn.getText());
				stopFlight.setOrderCode(orderCode++);
				stops.add(stopFlight);
			}
			if (stop2 != null){
				StopFlight stopFlight = new StopFlight();
				stopFlight.setStopAirport(stop2);
				stopFlight.setPlanTakeOffTime(txtStop2TakeOff.getText());
				stopFlight.setPlanLandInTime(txtStop2LandIn.getText());
				stopFlight.setOrderCode(orderCode++);
				stops.add(stopFlight);
			}
			flight.setStopFlights(((IFlightService<?, ?>)service).createStopFlights(flight,stops));
		}
		
		
	}

	@Override
	protected Composite createDialogComposite(Composite composite) {
		composite.setLayout(null);
		
		Group grpFlight = new Group(composite, SWT.NONE);
		grpFlight.setText("\u822A\u73ED\u4FE1\u606F");
		grpFlight.setBounds(10, 10, 538, 789);
		
		Label labCraftno = new Label(grpFlight, SWT.NONE);
		labCraftno.setText("\u673A\u53F7\uFF1A");
		labCraftno.setBounds(206, 29, 45, 19);
		
		cmbCraftno = new Combo(grpFlight, SWT.NONE);
		
		cmbCraftno.setBounds(200, 54, 130, 27);
		Label labFlightNo = new Label(grpFlight, SWT.NONE);
		labFlightNo.setText("\u822A\u73ED\u53F7\uFF1A");
		labFlightNo.setBounds(15, 29, 60, 19);
		
		txtFlightNo = new Text(grpFlight, SWT.BORDER);
		txtFlightNo.setBounds(15, 54, 130, 25);
		
		Label labArrAirport = new Label(grpFlight, SWT.NONE);
		labArrAirport.setText("\u843D\u5730\u673A\u573A\uFF1A");
		labArrAirport.setBounds(407, 87, 76, 19);
		
		cmbArrAirport = new Combo(grpFlight, SWT.NONE);
		cmbArrAirport.setBounds(400, 112, 130, 27);
		
		Label LabExecDate = new Label(grpFlight, SWT.NONE);
		LabExecDate.setText("\u6267\u884C\u65E5\u671F\uFF1A");
		LabExecDate.setBounds(405, 29, 105, 19);
		
		dtExecDate = new DateTime(grpFlight, SWT.BORDER | SWT.DROP_DOWN);
		dtExecDate.setDate(2014, 8, 24);
		dtExecDate.setBounds(400, 54, 130, 28);
		
		Label LabAirline = new Label(grpFlight, SWT.NONE);
		LabAirline.setText("\u822A\u7A7A\u516C\u53F8\uFF1A");
		LabAirline.setBounds(15, 87, 76, 19);
		
		cmbAirline = new Combo(grpFlight, SWT.READ_ONLY);
		cmbAirline.setBounds(15, 112, 130, 27);
		cmbAirline.setText("\u822A\u7A7A\u516C\u53F8\uFF1A");
		
		
		
		Label labDepAirport = new Label(grpFlight, SWT.NONE);
		labDepAirport.setBounds(206, 87, 76, 19);
		labDepAirport.setText("\u8D77\u98DE\u673A\u573A\uFF1A");
		
		cmbDepAirport = new Combo(grpFlight, SWT.NONE);
		cmbDepAirport.setBounds(200, 112, 130, 27);
		
		
		
		Group grpRoute = new Group(grpFlight, SWT.NONE);
		grpRoute.setLocation(15, 145);
		grpRoute.setSize(507, 197);
		grpRoute.setText("\u822A\u7EBF");
		
		Label labStart = new Label(grpRoute, SWT.NONE);
		labStart.setText("\u822A\u7EBF\u5F00\u59CB");
		labStart.setBounds(76, 11, 60, 19);
		
		cmbStart = new Combo(grpRoute, SWT.NONE);
		cmbStart.setBounds(76, 41, 90, 27);
		
		Label label_1 = new Label(grpRoute, SWT.NONE);
		label_1.setText("\u7ECF\u505C\u4E00");
		label_1.setBounds(204, 11, 45, 19);
		
		Label label_2 = new Label(grpRoute, SWT.NONE);
		label_2.setText("\u7ECF\u505C\u4E8C");
		label_2.setBounds(313, 11, 45, 19);
		
		cmbStop1 = new Combo(grpRoute, SWT.NONE);
		cmbStop1.setBounds(185, 41, 90, 27);
		
		cmbStop2 = new Combo(grpRoute, SWT.NONE);
		cmbStop2.setBounds(295, 41, 90, 27);
		
		Label labEnd = new Label(grpRoute, SWT.NONE);
		labEnd.setText("\u822A\u7EBF\u7ED3\u675F");
		labEnd.setBounds(423, 11, 60, 19);
		
		cmbEnd = new Combo(grpRoute, SWT.NONE);
		cmbEnd.setBounds(410, 41, 90, 27);
		
		txtStartTakeOff = new Text(grpRoute, SWT.BORDER | SWT.CENTER);
		txtStartTakeOff.setBounds(76, 117, 90, 27);
		
		txtStop1LandIn = new Text(grpRoute, SWT.BORDER | SWT.CENTER);
		txtStop1LandIn.setBounds(185, 79, 90, 27);
		
		txtStop1TakeOff = new Text(grpRoute, SWT.BORDER | SWT.CENTER);
		txtStop1TakeOff.setBounds(185, 117, 90, 27);
		
		txtStop2TakeOff = new Text(grpRoute, SWT.BORDER | SWT.CENTER);
		txtStop2TakeOff.setBounds(295, 117, 90, 27);
		
		txtEndLandIn = new Text(grpRoute, SWT.BORDER | SWT.CENTER);
		txtEndLandIn.setBounds(410, 79, 90, 27);
		
		txtStop2LandIn = new Text(grpRoute, SWT.BORDER | SWT.CENTER);
		txtStop2LandIn.setBounds(295, 79, 90, 27);
		
		txtStart = new Text(grpRoute, SWT.BORDER | SWT.CENTER);
		txtStart.setEnabled(false);
		txtStart.setBounds(76, 79, 90, 27);
		
		txtEnd = new Text(grpRoute, SWT.BORDER | SWT.CENTER);
		txtEnd.setEnabled(false);
		txtEnd.setBounds(410, 117, 90, 27);
		
		Label label = new Label(grpRoute, SWT.NONE);
		label.setText("\u843D\u5730");
		label.setBounds(10, 82, 60, 19);
		
		Label label_4 = new Label(grpRoute, SWT.NONE);
		label_4.setText("\u8D77\u98DE");
		label_4.setBounds(10, 120, 60, 19);
		
		txtRoute = new Text(grpRoute, SWT.BORDER | SWT.CENTER);
		txtRoute.setEnabled(false);
		txtRoute.setBounds(72, 155, 428, 27);
		
		labRoute = new Label(grpRoute, SWT.NONE);
		labRoute.setText("\u822A\u7EBF");
		labRoute.setBounds(10, 158, 60, 19);
		
		Label labThree = new Label(grpRoute, SWT.NONE);
		labThree.setText("IATA");
		labThree.setBounds(10, 41, 60, 19);
		
		Group group = new Group(grpFlight, SWT.NONE);
		group.setText("\u4FE1\u606F");
		group.setBounds(15, 348, 507, 322);
		
		Label labTask = new Label(group, SWT.NONE);
		labTask.setBounds(10, 75, 55, 19);
		labTask.setText("\u4EFB\u52A1\uFF1A");
		
		cmbTask = new Combo(group, SWT.READ_ONLY);
		cmbTask.setBounds(10, 100, 81, 27);
		
		Label labAttribute = new Label(group, SWT.WRAP);
		labAttribute.setBounds(10, 133, 75, 19);
		labAttribute.setText("\u822A\u6BB5\u6027\u8D28\uFF1A");
		
		cmbInOut = new Combo(group, SWT.READ_ONLY);
		cmbInOut.setBounds(10, 216, 81, 27);
		cmbInOut.setEnabled(false);
		cmbInOut.setText("PLN");
		
		Label labInOut = new Label(group, SWT.NONE);
		labInOut.setBounds(10, 191, 45, 19);
		labInOut.setText("\u8FDB\u51FA\uFF1A");
		
		cmbAttribute = new Combo(group, SWT.READ_ONLY);
		cmbAttribute.setBounds(10, 158, 81, 27);
		cmbAttribute.setEnabled(false);
		
	
		
		Label labState = new Label(group, SWT.WRAP);
		labState.setBounds(10, 249, 75, 19);
		labState.setText("\u72B6\u6001\uFF1A");
		
		cmbState = new Combo(group, SWT.READ_ONLY);
		cmbState.setBounds(10, 274, 81, 27);
		cmbState.setEnabled(false);
		cmbState.select(0);
		
		cbNormal = new Button(group, SWT.CHECK);
		cbNormal.setBounds(373, 103, 121, 19);
		cbNormal.setText("\u822A\u73ED\u6B63\u5E38");
		cbNormal.setSelection(true);
		
		txtTaskCn = new Text(group, SWT.BORDER | SWT.CENTER);
		txtTaskCn.setEnabled(false);
		txtTaskCn.setBounds(123, 100, 186, 27);
		
		txtRouteAttrCn = new Text(group, SWT.BORDER | SWT.CENTER);
		txtRouteAttrCn.setEnabled(false);
		txtRouteAttrCn.setBounds(123, 158, 186, 27);
		
		txtOutInCn = new Text(group, SWT.BORDER | SWT.CENTER);
		txtOutInCn.setEnabled(false);
		txtOutInCn.setBounds(123, 216, 186, 27);
		
		text_5 = new Text(group, SWT.BORDER | SWT.CENTER);
		text_5.setEnabled(false);
		text_5.setBounds(123, 274, 186, 27);
		
		Label labTakeOffLandInCount = new Label(group, SWT.NONE);
		labTakeOffLandInCount.setBounds(315, 17, 75, 19);
		labTakeOffLandInCount.setText("\u8D77\u964D\u67B6\u6B21\uFF1A");
		
		txtTakeOffLandInCount = new Text(group, SWT.BORDER);
		txtTakeOffLandInCount.setBounds(315, 42, 87, 25);
		
		labGate = new Label(group, SWT.NONE);
		labGate.setText("\u767B\u673A\u53E3");
		labGate.setBounds(15, 20, 55, 19);
		
		cmbGate = new Combo(group, SWT.READ_ONLY);
		cmbGate.setBounds(10, 42, 81, 27);
		
		labCounter = new Label(group, SWT.NONE);
		labCounter.setText("\u503C\u673A\u67DC\u53F0");
		labCounter.setBounds(115, 20, 68, 19);
		
		cmbCounter = new Combo(group, SWT.NONE);
		cmbCounter.setBounds(115, 42, 81, 27);
		
		labCraftType = new Label(group, SWT.NONE);
		labCraftType.setText("\u673A\u578B");
		labCraftType.setBounds(215, 17, 55, 19);
		
		Label labFlightTime = new Label(group, SWT.NONE);
		labFlightTime.setBounds(410, 17, 75, 19);
		labFlightTime.setText("\u98DE\u884C\u65F6\u95F4\uFF1A");
		
		txtFlightTime = new Text(group, SWT.BORDER);
		txtFlightTime.setBounds(410, 42, 84, 25);
		
		btnTra = new Button(group, SWT.CHECK);
		btnTra.setText("\u4E2D\u8F6C");
		btnTra.setBounds(373, 161, 121, 19);
		
		btnNight = new Button(group, SWT.CHECK);
		btnNight.setText("\u8FC7\u591C");
		btnNight.setBounds(373, 219, 121, 19);
		
		group_3 = new Group(grpFlight, SWT.NONE);
		group_3.setText("\u5173\u8054\u822A\u73ED");
		group_3.setBounds(15, 676, 507, 86);
		
		txtLink = new Text(group_3, SWT.BORDER | SWT.READ_ONLY);
		txtLink.setBounds(10, 40, 342, 25);
		
		Button btnLink = new Button(group_3, SWT.NONE);
		btnLink.setBounds(383, 36, 98, 29);
		btnLink.setText("\u8F6C\u5230");
				
				CTabFolder tabFolder = new CTabFolder(composite, SWT.BORDER);
				tabFolder.setBounds(576, 21, 428, 746);
				tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
				
				CTabItem tbTime = new CTabItem(tabFolder, SWT.NONE);
				tbTime.setText("  \u65F6\u95F4  ");
				
				Composite composite_1 = new Composite(tabFolder, SWT.NONE);
				tbTime.setControl(composite_1);
				
				Group group_1 = new Group(composite_1, SWT.NONE);
				group_1.setText("\u65F6\u95F4");
				group_1.setBounds(10, 10, 389, 298);
				
				Label LabPlnTakeoff = new Label(group_1, SWT.NONE);
				LabPlnTakeoff.setBounds(37, 26, 107, 19);
				LabPlnTakeoff.setText("\u8BA1\u5212\u8D77\u98DE\u65F6\u95F4\uFF1A");
				
				Label labRealTakeOff = new Label(group_1, SWT.NONE);
				labRealTakeOff.setBounds(37, 116, 107, 19);
				labRealTakeOff.setText("\u5B9E\u9645\u8D77\u98DE\u65F6\u95F4\uFF1A");
				
				Label labAlterateLandinTime = new Label(group_1, SWT.NONE);
				labAlterateLandinTime.setBounds(37, 206, 107, 19);
				labAlterateLandinTime.setText("\u9884\u8BA1\u843D\u5730\u65F6\u95F4\uFF1A");
				
				txtPlnTakeOff = new Text(group_1, SWT.BORDER);
				txtPlnTakeOff.setBounds(181, 21, 170, 25);
				
				txtAlterateLandinTime = new Text(group_1, SWT.BORDER);
				txtAlterateLandinTime.setBounds(181, 205, 170, 25);
				txtAlterateLandinTime.setEnabled(false);
				
				Label labPlnLandIn = new Label(group_1, SWT.NONE);
				labPlnLandIn.setBounds(37, 161, 107, 19);
				labPlnLandIn.setText("\u8BA1\u5212\u843D\u5730\u65F6\u95F4\uFF1A");
				
				txtRealTakeOff = new Text(group_1, SWT.BORDER);
				txtRealTakeOff.setBounds(181, 113, 170, 25);
				txtRealTakeOff.setEnabled(false);
				
				Label labAlteraTeakeOffTime = new Label(group_1, SWT.NONE);
				labAlteraTeakeOffTime.setBounds(37, 71, 107, 19);
				labAlteraTeakeOffTime.setText("\u9884\u8BA1\u8D77\u98DE\u65F6\u95F4\uFF1A");
				
				Label labRealLandIn = new Label(group_1, SWT.NONE);
				labRealLandIn.setBounds(37, 251, 107, 19);
				labRealLandIn.setText("\u5B9E\u9645\u843D\u5730\u65F6\u95F4\uFF1A");
				
				txtPlnLandIn = new Text(group_1, SWT.BORDER);
				txtPlnLandIn.setBounds(181, 159, 170, 25);
				
				txtAlteraTeakeOffTime = new Text(group_1, SWT.BORDER);
				txtAlteraTeakeOffTime.setBounds(181, 67, 170, 25);
				txtAlteraTeakeOffTime.setEnabled(false);
				
				txtRealLandIn = new Text(group_1, SWT.BORDER);
				txtRealLandIn.setBounds(181, 251, 170, 25);
				txtRealLandIn.setEnabled(false);
						
						Group group_4 = new Group(composite_1, SWT.NONE);
						group_4.setText("\u5907\u964D\u4FE1\u606F");
						group_4.setBounds(10, 331, 389, 192);
						
						Label labAlternateAirport = new Label(group_4, SWT.NONE);
						labAlternateAirport.setBounds(40, 23, 76, 19);
						labAlternateAirport.setText("\u5907\u964D\u673A\u573A\uFF1A");
						
						cmbAlternateAirport = new Combo(group_4, SWT.READ_ONLY);
						cmbAlternateAirport.setBounds(191, 18, 161, 27);
						cmbAlternateAirport.setEnabled(false);
						
						txtAlternateActualLandin = new Text(group_4, SWT.BORDER);
						txtAlternateActualLandin.setBounds(191, 63, 161, 25);
						
						Label labAlternateAlalteraLandin = new Label(group_4, SWT.NONE);
						labAlternateAlalteraLandin.setText("\u5907\u964D\u5B9E\u9645\u843D\u5730\uFF1A");
						labAlternateAlalteraLandin.setBounds(40, 65, 107, 19);
						
						txtAlternateAlteraTeakeOff = new Text(group_4, SWT.BORDER);
						txtAlternateAlteraTeakeOff.setBounds(191, 106, 161, 25);
						
						Label labAlternateAlteraTeakeOff = new Label(group_4, SWT.NONE);
						labAlternateAlteraTeakeOff.setText("\u5907\u964D\u9884\u8BA1\u8D77\u98DE\uFF1A");
						labAlternateAlteraTeakeOff.setBounds(40, 107, 107, 19);
						
						txtAlternateActualTakeOff = new Text(group_4, SWT.BORDER);
						txtAlternateActualTakeOff.setBounds(191, 149, 161, 25);
						
						Label labAlternateActualTakeOff = new Label(group_4, SWT.NONE);
						labAlternateActualTakeOff.setText("\u5907\u964D\u5B9E\u9645\u8D77\u98DE\uFF1A");
						labAlternateActualTakeOff.setBounds(40, 149, 107, 19);
								
										Group grpDly = new Group(composite_1, SWT.NONE);
										grpDly.setBounds(10, 539, 389, 134);
										grpDly.setText("\u5EF6\u8BEF");
										
										Label labCategory = new Label(grpDly, SWT.WRAP);
										labCategory.setText("\u5EF6\u8BEF\u5206\u7C7B");
										labCategory.setBounds(22, 32, 75, 19);
										
										cmbCategory = new Combo(grpDly, SWT.READ_ONLY);
										cmbCategory.setEnabled(false);
										cmbCategory.setBounds(103, 29, 263, 27);		
										Label labReason = new Label(grpDly, SWT.WRAP);
										labReason.setText("\u5EF6\u8BEF\u539F\u56E0");
										labReason.setBounds(22, 85, 75, 19);
										
										cmbReason = new Combo(grpDly, SWT.READ_ONLY);
										cmbReason.setEnabled(false);
										cmbReason.setBounds(103, 82, 263, 27);
										cmbCategory.addSelectionListener(new DelayCategorySelectionAdapter(cmbReason,ctx));
										
										final Button cbEditAll = new Button(composite_1, SWT.CHECK);
										cbEditAll.setBounds(285, 688, 114, 19);
										cbEditAll.setText("\u4FEE\u6539\u6240\u6709\u5B57\u6BB5");
										
										
									
				
				CTabItem tbFPL = new CTabItem(tabFolder, SWT.NONE);
				tbFPL.setText("  FPL  ");
				
				group_2 = new Group(tabFolder, SWT.NONE);
				tbFPL.setControl(group_2);
				
				Label labEquipmentInfo = new Label(group_2, SWT.NONE);
				labEquipmentInfo.setBounds(10, 35, 120, 19);
				labEquipmentInfo.setText("\u673A\u8F7D\u8BBE\u5907\u4E0E\u80FD\u529B\uFF1A");
				
				txtEquipmentInfo = new Text(group_2, SWT.BORDER);
				txtEquipmentInfo.setBounds(10, 60, 363, 51);
				
				Label labRouteInfo = new Label(group_2, SWT.NONE);
				labRouteInfo.setBounds(10, 134, 75, 19);
				labRouteInfo.setText("\u5DE1\u822A\u4FE1\u606F\uFF1A");
				
				txtRouteInfo = new Text(group_2, SWT.BORDER);
				txtRouteInfo.setBounds(10, 168, 363, 51);
				
				Label labAlternateInfo = new Label(group_2, SWT.NONE);
				labAlternateInfo.setBounds(10, 355, 60, 19);
				labAlternateInfo.setText("\u5907\u964D\u573A\uFF1A");
				
				txtAlternateInfo = new Text(group_2, SWT.BORDER);
				txtAlternateInfo.setBounds(10, 380, 363, 25);
				
				Label labOtherInfo = new Label(group_2, SWT.NONE);
				labOtherInfo.setBounds(10, 242, 75, 19);
				labOtherInfo.setText("\u5176\u4ED6\u60C5\u62A5\uFF1A");
				
				txtOtherInfo = new Text(group_2, SWT.BORDER);
				txtOtherInfo.setBounds(10, 279, 363, 51);
				
				CTabItem tbLoad = new CTabItem(tabFolder, SWT.NONE);
				tbLoad.setText("  \u8F7D\u91CF  ");
				
				CTabItem tbTelex = new CTabItem(tabFolder, SWT.NONE);
				tbTelex.setText("\u7535\u62A5");
				
				
	 //设置起飞，落地机场修改的监听器，用于自动设置相关字段
	 AirportModifyListener apmodifyListener = new AirportModifyListener
						(cmbDepAirport, cmbArrAirport, cmbAttribute, cmbInOut,airportService);
	 
	 txtCraftType = new Text(group, SWT.BORDER);
	 txtCraftType.setBounds(211, 42, 87, 25);
     AircraftModifyListener acmodifyListener = new AircraftModifyListener
				(cmbAirline, cmbCraftno,aircraftService) ;		
	 initCmbItems();
	 
	 cmbArrAirport.addModifyListener(apmodifyListener);
	 cmbDepAirport.addModifyListener(apmodifyListener);
	 cmbCraftno.addModifyListener(acmodifyListener);
			
		
		//设置所有航班字段可修改状态的监听器
		cbEditAll.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub
				cmbAttribute.setEnabled(cbEditAll.getSelection());
				cmbInOut.setEnabled(cbEditAll.getSelection());
				cmbState.setEnabled(cbEditAll.getSelection());
				cmbAlternateAirport.setEnabled(cbEditAll.getSelection());
				cmbCategory.setEnabled(cbEditAll.getSelection());
				cmbReason.setEnabled(cbEditAll.getSelection());
				txtAlteraTeakeOffTime.setEnabled(cbEditAll.getSelection());
				txtAlterateLandinTime.setEnabled(cbEditAll.getSelection());
				txtRealLandIn.setEnabled(cbEditAll.getSelection());
				txtRealTakeOff.setEnabled(cbEditAll.getSelection());
				
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	
		return null;
	}
}
