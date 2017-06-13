package com.lzairport.ais.dialog;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.swt.widgets.Composite;
import com.lzairport.ais.models.aodb.Airlines;
import com.lzairport.ais.models.aodb.Airport;
import com.lzairport.ais.models.aodb.AreaAttribute;
import com.lzairport.ais.models.aodb.CraftType;
import com.lzairport.ais.models.aodb.FlightTask;
import com.lzairport.ais.models.aodb.ScheduleFlight;
import com.lzairport.ais.models.aodb.StopFlight;
import com.lzairport.ais.service.aodb.IAirlinesService;
import com.lzairport.ais.service.aodb.IAirportService;
import com.lzairport.ais.service.aodb.IAreaAttributeService;
import com.lzairport.ais.service.aodb.ICraftTypeService;
import com.lzairport.ais.service.aodb.IFlightTaskService;
import com.lzairport.ais.service.aodb.IScheduleFlightService;
import com.lzairport.ais.utils.SYS_VARS;
import com.lzairport.ais.utils.SYS_VARS.OutIn;
import com.lzairport.ais.utils.SYS_VARS.Quarter;
import com.lzairport.ais.utils.SYS_VARS.Week;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.springframework.context.support.AbstractApplicationContext;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;




/**
 * 长期航班计划修改的对话窗口
 * 可用于动态、计划、历史航班的修改
 * @author ZhangYu
 * @version 0.9a 22//14
 * @since JDK 1.6
 *
 */


public class ScheduleFlightEditDialog extends EditDialog {
	
	private Text txtFlightNo;
	private Text txtPlnTakeOff;
	private Text txtPlnLandIn;
	private Text txtStartTakeOff;
	private Text txtStop1LandIn;
	private Text txtStop2LandIn;
	private Text txtEndLandIn;
	private Text txtStop1TakeOff;
	private Text txtStop2TakeOff;
	private Text txtFlightTime;
	
	private Combo cmbQuarter;
	private Combo cmbTask;
	private Combo cmbAttribute;
	private Combo cmbCraftType;
	private Combo cmbAirline;
	private Combo cmbInOut;
	private Combo cmbDepAirport;
	private Combo cmbArrAirport;
	private Combo cmbStart;
	private Combo cmbStop1;
	private Combo cmbStop2;
	private Combo cmbEnd;
	private Combo cmbCounter;
	private Combo cmbGate;
	private Combo cmbRouteType;
	
	private Button cbMonday;
	private Button cbTuesday;
	private Button cbWednesday;
	private Button cbThursday;
	private Button cbFriday;
	private Button cbSaturday;
	private Button cbSunday;
	
	private CTabFolder tabFolder;
	private CTabItem tbTime;
	private Composite composite_1;
	
	private Group group;
	private Group group_1;
	private Group group_2;
	
	private Text txtRouteAttrCn;
	private Text txtTaskCn;
	private Text txtOutInCn;
	private Text txtSeat;
	
	
	private DateTime dtStart;
	private DateTime dtEnd;
	
	private IFlightTaskService flightTaskService;
	
	private IAirportService airportService;
	
	
	private IAreaAttributeService areaAttributeService;
	
	private ICraftTypeService craftTypeService;
	
	private IAirlinesService airlinesService;
	
	private IScheduleFlightService scheduleFlightService;
	

	
	
	
	public ScheduleFlightEditDialog(IScheduleFlightService service,
			Object  object,AbstractApplicationContext ctx) {
		super(service, object);

		//初始化各种Service
		scheduleFlightService = service;
	    airportService   =  (IAirportService) ctx.getBean("airportService");
		airlinesService  = (IAirlinesService) ctx.getBean("airlinesService");
		areaAttributeService = (IAreaAttributeService) ctx.getBean("areaAttributeService");
		flightTaskService = (IFlightTaskService) ctx.getBean("flightTaskService");
		craftTypeService = (ICraftTypeService) ctx.getBean("craftTypeService");
	
		
	}
	
	/**
	 * 初始化各Combo，给各Combo赋值
	 */	
	private void initCmbItems(){
		
		//取得各所需要添加到Combo Items的数据
		List<FlightTask> tasks =flightTaskService.getAll();
		List<Airlines> airlines = airlinesService.getAll();
		List<Airport> airports = airportService.getAll();
		List<AreaAttribute> areaAttributes = areaAttributeService.getAll();
		List<CraftType> craftTypes = craftTypeService.getAll();
		
		
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
		for(Airlines airline:airlines){
			cmbAirline.add(airline.getCnShortName());
		}
		for(AreaAttribute areaAttribute:areaAttributes){
			cmbAttribute.add(areaAttribute.getCode());
		}
		for(CraftType craftType:craftTypes)
		{
			cmbCraftType.add(craftType.getCode());
		}
		
		
		cmbInOut.setItems(SYS_VARS.OutInCode.toArray(new String[SYS_VARS.OutInCode.size()]));
		cmbQuarter.setItems(SYS_VARS.QuarterCn.toArray(new String[SYS_VARS.QuarterCn.size()]));
		cmbRouteType.setItems(SYS_VARS.RouteTypeCn.toArray(new String[SYS_VARS.RouteTypeCn.size()]));
	
		//执行频率读取
		
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
		if (object instanceof ScheduleFlight){
			ScheduleFlight flight = (ScheduleFlight) object;
			
			//如果取值不为空，填充各txt
			if (flight.getFlightNO() != null){
				txtFlightNo.setText(flight.getFlightNO());
			}
			if (flight.getFlightTime() != null){
				txtFlightTime.setText(flight.getFlightTime());
			}
			if (flight.getPlanTakeOffTime() != null){
				txtPlnTakeOff.setText(flight.getPlanTakeOffTime());
			}
			if (flight.getPlanLandInTime() != null){
				txtPlnLandIn.setText(flight.getPlanLandInTime());
			}
			if(flight.getStartPlanTakeOffTime() != null){
				txtStartTakeOff.setText(flight.getStartPlanTakeOffTime());
			}

			if (flight.getTerminalPlanLandInTime() != null){
				txtEndLandIn.setText(flight.getTerminalPlanLandInTime());
			}
			
			txtSeat.setText(((Integer)flight.getSeatNum()).toString());
			//如果取值不为空，填充各Combo	
			if (flight.getTask() != null){
				cmbTask.setText(flight.getTask().getCode());
			}
			if((flight.getQuarter() != null)&&(flight.getQuarter().ordinal() >=0)){
				cmbQuarter.setText(SYS_VARS.QuarterCn.get(flight.getQuarter().ordinal()));
			}
			if ((flight.getIsOutIn() != null)&&(flight.getIsOutIn().ordinal() >=0)){
				cmbInOut.setText(SYS_VARS.OutInCode.get(flight.getIsOutIn().ordinal()));
			}
			

			
			if (flight.getDepAirport() != null){
				cmbDepAirport.setText(flight.getDepAirport().getThreeCharCode());
			}
			if (flight.getArrAirport() != null){
				cmbArrAirport.setText(flight.getArrAirport().getThreeCharCode());
			}
			if (flight.getAttribute() != null){
				cmbAttribute.setText(flight.getAttribute().getCode());
			}
			if (flight.getStartAirport() != null){
				cmbStart.setText(flight.getStartAirport().getThreeCharCode());
			}
			if (flight.getTerminalAirport() != null){
				cmbEnd.setText(flight.getTerminalAirport().getThreeCharCode());
			}
			if(flight.getAirlines() !=null){
				cmbAirline.setText(flight.getAirlines().getCnShortName());
			}
			if(flight.getCraftType() !=null){
				cmbCraftType.setText(flight.getCraftType().getCode());
			}
		
			
			//经停设置读取
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
		
			//执行频率读取
			Set<Week>  weeks =flight.getExecWeek();
			if (weeks != null){

				if (weeks.contains(Week.Monday)){
					cbMonday.setSelection(true);
				}
				if (weeks.contains(Week.Tuesday)){
					cbTuesday.setSelection(true);
				}
				if (weeks.contains(Week.Wednesday)){
					cbWednesday.setSelection(true);
				}
				if (weeks.contains(Week.Thursday)){
					cbThursday.setSelection(true);
				}
				if (weeks.contains(Week.Friday)){
					cbFriday.setSelection(true);
				}
				if (weeks.contains(Week.Saturday)){
					cbSaturday.setSelection(true);
				}
				if (weeks.contains(Week.Sunday)){
					cbSunday.setSelection(true);
				}
				
			}
			
			if(flight.getStartTime()!= null){
				 Calendar cal = Calendar.getInstance(); 
				 cal.setTime(flight.getStartTime());
				 dtStart.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
			}
			
			if(flight.getEndTime()!= null){
				 Calendar cal = Calendar.getInstance(); 
				 cal.setTime(flight.getEndTime());
				 dtEnd.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
			}
			
		}
	}

	@Override
	protected void dialogToObj(Object object) {
		// TODO Auto-generated method stub
		if (object instanceof ScheduleFlight){
			ScheduleFlight flight = (ScheduleFlight) object;

			//直接进行Set的字段，因为开销比较小
			flight.setFlightNO(txtFlightNo.getText());
			flight.setStartPlanTakeOffTime(txtStartTakeOff.getText());
			flight.setTerminalPlanLandInTime(txtEndLandIn.getText());
			flight.setPlanLandInTime(txtPlnLandIn.getText());
			flight.setPlanTakeOffTime(txtPlnTakeOff.getText());
			flight.setFlightTime(txtFlightTime.getText());
			flight.setSeatNum(Integer.valueOf(txtSeat.getText()));
			
			//设置开始时间和结束时间
			Calendar cal = Calendar.getInstance();
			cal.set(dtStart.getYear(), dtStart.getMonth(), dtStart.getDay(),0,0,0);
			flight.setStartTime(cal.getTime());
			cal.set(dtEnd.getYear(), dtEnd.getMonth(), dtEnd.getDay(),0,0,0);
			flight.setEndTime(cal.getTime());
			
			//如果在SYS_VARS_Util.OutInCn中有Combo的值，则进行设置
			if ((cmbInOut.getText() != null) &&(SYS_VARS.OutInCode.indexOf(cmbInOut.getText()) != -1)){
				flight.setIsOutIn(OutIn.values()[SYS_VARS.OutInCode.indexOf(cmbInOut.getText())]);
			}
			if ((cmbQuarter.getText() != null) &&(SYS_VARS.QuarterCn.indexOf(cmbQuarter.getText()) != -1)){
				flight.setQuarter(Quarter.values()[SYS_VARS.QuarterCn.indexOf(cmbQuarter.getText())]);
			}
			
			if ((cmbQuarter.getText() != null) &&(SYS_VARS.QuarterCn.indexOf(cmbQuarter.getText()) != -1)){
				flight.setQuarter(Quarter.values()[SYS_VARS.QuarterCn.indexOf(cmbQuarter.getText())]);
			}
			

			
			//根据Combo的值与航班实体类的属性进行对比，如果发生改变，则进行Set
			if (((flight.getTask() != null)&&!flight.getTask().getCode().equals(cmbTask.getText()))
				||((flight.getTask() == null)&&(cmbTask.getText()!= null))){
				flight.setTask(flightTaskService.findByFieldSingle(FlightTask.CODE,cmbTask.getText()));
			}
			if (((flight.getAttribute() != null)&&!flight.getAttribute().getCode().equals(cmbAttribute.getText()))
					||((flight.getAttribute() == null)&&(cmbAttribute.getText()!= null))){
				flight.setAttribute(areaAttributeService.findByFieldSingle(AreaAttribute.CODE, cmbAttribute.getText()));
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
			if (((flight.getAirlines() != null)&&!flight.getAirlines().getCnShortName().equals(cmbAirline.getText()))
					||((flight.getAirlines() == null)&&(cmbAirline.getText()!= null))){
				flight.setAirlines(airlinesService.findByFieldSingle(Airlines.CNSHORTNAME, cmbAirline.getText()));
			}
			if (((flight.getCraftType() != null)&&!flight.getCraftType().getCode().equals(cmbCraftType.getText()))
					||((flight.getCraftType() == null)&&(cmbCraftType.getText()!= null))){
				flight.setCraftType(craftTypeService.findByFieldSingle(CraftType.CODE, cmbCraftType.getText()));
			}
		
			
		
		//设置经停航段
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
		flight.setStopFlights(scheduleFlightService.createStopFlights(flight,stops));
			
		//设置执行频率
		flight.setExecWeek(new HashSet<Week>());
		if (cbMonday.getSelection()){
			flight.getExecWeek().add(Week.Monday);
		}
		if (cbTuesday.getSelection()){
			flight.getExecWeek().add(Week.Tuesday);
		}
		if (cbWednesday.getSelection()){
			flight.getExecWeek().add(Week.Wednesday);
		}
		if (cbThursday.getSelection()){
			flight.getExecWeek().add(Week.Thursday);
		}
		if (cbFriday.getSelection()){
			flight.getExecWeek().add(Week.Friday);
		}
		if (cbSaturday.getSelection()){
			flight.getExecWeek().add(Week.Saturday);
		}
		if (cbSunday.getSelection()){
			flight.getExecWeek().add(Week.Sunday);
		}
		
		
		}
		
	}

	@Override
	protected Composite createDialogComposite(Composite composite) {
		composite.setLayout(null);
		//composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Group grpFlight = new Group(composite, SWT.NONE);
		grpFlight.setBounds(13, 0, 610, 695);
		grpFlight.setText("\u957F\u671F\u822A\u73ED\u8BA1\u5212");
		
	
		
		Label labFlightNo = new Label(grpFlight, SWT.NONE);
		labFlightNo.setBounds(10, 33, 60, 19);
		labFlightNo.setText("\u822A\u73ED\u53F7\uFF1A");
		
		txtFlightNo = new Text(grpFlight, SWT.BORDER);
		txtFlightNo.setBounds(99, 30, 147, 25);
		
		Label lbAirline = new Label(grpFlight, SWT.NONE);
		lbAirline.setBounds(324, 33, 76, 19);
		lbAirline.setText("\u822A\u7A7A\u516C\u53F8\uFF1A");
		
		cmbAirline = new Combo(grpFlight, SWT.READ_ONLY);
		cmbAirline.setBounds(442, 30, 147, 27);
		
		Label labDepAirport = new Label(grpFlight, SWT.NONE);
		labDepAirport.setBounds(10, 91, 76, 19);
		labDepAirport.setText("\u8D77\u98DE\u673A\u573A\uFF1A");
		
		cmbDepAirport = new Combo(grpFlight, SWT.NONE);
		cmbDepAirport.setBounds(99, 88, 147, 27);
		
		Label labArrAirport = new Label(grpFlight, SWT.NONE);
		labArrAirport.setBounds(324, 91, 76, 19);
		labArrAirport.setText("\u843D\u5730\u673A\u573A\uFF1A");
		
		cmbArrAirport = new Combo(grpFlight, SWT.NONE);
		cmbArrAirport.setBounds(442, 88, 147, 27);
		
		
		Group grpRoute = new Group(grpFlight, SWT.NONE);
		grpRoute.setLocation(10, 128);
		grpRoute.setSize(583, 239);
		grpRoute.setText("\u822A\u7EBF");
		
		Label labStart = new Label(grpRoute, SWT.NONE);
		labStart.setText("\u822A\u7EBF\u5F00\u59CB");
		labStart.setBounds(13, 36, 76, 19);
		
		cmbStart = new Combo(grpRoute, SWT.NONE);
		cmbStart.setBounds(95, 33, 92, 27);
		
		Label label_7 = new Label(grpRoute, SWT.NONE);
		label_7.setText("\u822A\u7EBF\u7ED3\u675F");
		label_7.setBounds(13, 194, 76, 19);
		
		cmbEnd = new Combo(grpRoute, SWT.NONE);
		cmbEnd.setBounds(95, 191, 92, 27);
		
		Label label_8 = new Label(grpRoute, SWT.NONE);
		label_8.setText("\u7ECF\u505C\u4E00");
		label_8.setBounds(13, 88, 76, 19);
		
		Label label_9 = new Label(grpRoute, SWT.NONE);
		label_9.setText("\u7ECF\u505C\u4E8C");
		label_9.setBounds(13, 142, 76, 19);
		
		cmbStop1 = new Combo(grpRoute, SWT.NONE);
		cmbStop1.setBounds(95, 85, 92, 27);
		
		cmbStop2 = new Combo(grpRoute, SWT.NONE);
		cmbStop2.setBounds(95, 139, 92, 27);
		
		Label labStartTakeoff = new Label(grpRoute, SWT.NONE);
		labStartTakeoff.setText("\u8BA1\u8D77\u65F6\u95F4\uFF1A");
		labStartTakeoff.setBounds(222, 36, 75, 19);
		
		txtStartTakeOff = new Text(grpRoute, SWT.BORDER);
		txtStartTakeOff.setBounds(317, 33, 76, 25);
		
		Label label_11 = new Label(grpRoute, SWT.NONE);
		label_11.setText("\u8BA1\u843D\u65F6\u95F4\uFF1A");
		label_11.setBounds(222, 88, 75, 19);
		
		txtStop1LandIn = new Text(grpRoute, SWT.BORDER);
		txtStop1LandIn.setBounds(317, 85, 76, 25);
		
		Label label_12 = new Label(grpRoute, SWT.NONE);
		label_12.setText("\u8BA1\u843D\u65F6\u95F4\uFF1A");
		label_12.setBounds(222, 142, 75, 19);
		
		txtStop2LandIn = new Text(grpRoute, SWT.BORDER);
		txtStop2LandIn.setBounds(318, 139, 75, 25);
		
		Label label_13 = new Label(grpRoute, SWT.NONE);
		label_13.setText("\u8BA1\u843D\u65F6\u95F4\uFF1A");
		label_13.setBounds(222, 194, 75, 19);
		
		txtEndLandIn = new Text(grpRoute, SWT.BORDER);
		txtEndLandIn.setBounds(317, 191, 76, 25);
		
		Label label_14 = new Label(grpRoute, SWT.NONE);
		label_14.setText("\u8BA1\u8D77\u65F6\u95F4\uFF1A");
		label_14.setBounds(407, 88, 75, 19);
		
		txtStop1TakeOff = new Text(grpRoute, SWT.BORDER);
		txtStop1TakeOff.setBounds(488, 85, 76, 25);
		
		Label label_15 = new Label(grpRoute, SWT.NONE);
		label_15.setText("\u8BA1\u8D77\u65F6\u95F4\uFF1A");
		label_15.setBounds(407, 142, 75, 19);
		
		txtStop2TakeOff = new Text(grpRoute, SWT.BORDER);
		txtStop2TakeOff.setBounds(488, 139, 76, 25);
		
		group_2 = new Group(grpFlight, SWT.NONE);
		group_2.setBounds(10, 386, 579, 299);
		
		Label labCraftno = new Label(group_2, SWT.WRAP);
		labCraftno.setBounds(419, 30, 75, 19);
		labCraftno.setText("\u673A\u578B\uFF1A");
		
		cmbCraftType = new Combo(group_2, SWT.NONE);
		cmbCraftType.setBounds(419, 55, 107, 27);
		
		Label labTask = new Label(group_2, SWT.NONE);
		labTask.setBounds(19, 158, 55, 19);
		labTask.setText("\u4EFB\u52A1\uFF1A");
		
		Label labInOut = new Label(group_2, SWT.NONE);
		labInOut.setBounds(19, 228, 45, 19);
		labInOut.setText("\u8FDB\u51FA\uFF1A");
		
		cmbTask = new Combo(group_2, SWT.READ_ONLY);
		cmbTask.setBounds(19, 183, 107, 27);
		
		cmbInOut = new Combo(group_2, SWT.READ_ONLY);
		cmbInOut.setBounds(19, 253, 107, 27);
		
		Label labAttribute = new Label(group_2, SWT.WRAP);
		labAttribute.setBounds(19, 88, 75, 19);
		labAttribute.setText("\u822A\u6BB5\u6027\u8D28\uFF1A");
		
		cmbAttribute = new Combo(group_2, SWT.READ_ONLY);
		cmbAttribute.setBounds(19, 113, 107, 27);
		
		
		
		Label labGate = new Label(group_2, SWT.NONE);
		labGate.setText("\u767B\u673A\u53E3");
		labGate.setBounds(19, 30, 55, 19);
		
		cmbGate = new Combo(group_2, SWT.READ_ONLY);
		cmbGate.setBounds(19, 55, 107, 27);
		
		Label labCounter = new Label(group_2, SWT.NONE);
		labCounter.setText("\u503C\u673A\u67DC\u53F0");
		labCounter.setBounds(158, 30, 68, 19);
		
		cmbCounter = new Combo(group_2, SWT.NONE);
		cmbCounter.setBounds(158, 55, 107, 27);
		
		txtRouteAttrCn = new Text(group_2, SWT.BORDER | SWT.CENTER);
		txtRouteAttrCn.setEnabled(false);
		txtRouteAttrCn.setBounds(158, 113, 186, 27);
		
		txtTaskCn = new Text(group_2, SWT.BORDER | SWT.CENTER);
		txtTaskCn.setEnabled(false);
		txtTaskCn.setBounds(158, 183, 186, 27);
		
		txtOutInCn = new Text(group_2, SWT.BORDER | SWT.CENTER);
		txtOutInCn.setEnabled(false);
		txtOutInCn.setBounds(158, 253, 186, 27);
		
		Button btnTra = new Button(group_2, SWT.CHECK);
		btnTra.setText("\u4E2D\u8F6C");
		btnTra.setBounds(419, 113, 121, 19);
		
		Button btnNight = new Button(group_2, SWT.CHECK);
		btnNight.setText("\u8FC7\u591C");
		btnNight.setBounds(419, 171, 121, 19);
		
		tabFolder = new CTabFolder(composite, SWT.BORDER);
		tabFolder.setBounds(658, 10, 364, 685);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		tbTime = new CTabItem(tabFolder, SWT.NONE);
		tbTime.setText("\u65F6\u95F4");
		
		composite_1 = new Composite(tabFolder, SWT.NONE);
		tbTime.setControl(composite_1);
		
		group = new Group(composite_1, SWT.NONE);
		group.setText("\u822A\u5B63");
		group.setBounds(10, 10, 327, 182);
		
		Label labQuarter = new Label(group, SWT.WRAP);
		labQuarter.setBounds(10, 31, 55, 19);
		labQuarter.setText("\u822A\u5B63\uFF1A");
		
		cmbQuarter = new Combo(group, SWT.READ_ONLY);
		cmbQuarter.setBounds(144, 24, 147, 27);
		
		Label labStartDate = new Label(group, SWT.NONE);
		labStartDate.setBounds(10, 81, 105, 19);
		labStartDate.setText("\u6709\u6548\u5F00\u59CB\u65F6\u95F4\uFF1A");
		
		Label labEndDate = new Label(group, SWT.NONE);
		labEndDate.setBounds(10, 131, 107, 19);
		labEndDate.setText("\u6709\u6548\u7ED3\u675F\u65F6\u95F4\uFF1A");
		
		dtStart = new DateTime(group, SWT.DROP_DOWN);
		dtStart.setBounds(144, 75, 147, 28);
		
		dtEnd = new DateTime(group, SWT.BORDER | SWT.DROP_DOWN);
		dtEnd.setBounds(144, 127, 147, 28);
		
		group_1 = new Group(composite_1, SWT.NONE);
		group_1.setText("\u8D77\u964D\u65F6\u95F4");
		group_1.setBounds(10, 198, 327, 144);
		
		Label LabPlnTakeoff = new Label(group_1, SWT.NONE);
		LabPlnTakeoff.setBounds(10, 25, 107, 19);
		LabPlnTakeoff.setText("\u8BA1\u5212\u8D77\u98DE\u65F6\u95F4\uFF1A");
		
		txtPlnTakeOff = new Text(group_1, SWT.BORDER);
		txtPlnTakeOff.setBounds(133, 21, 184, 25);
		
		Label labPlnLandIn = new Label(group_1, SWT.NONE);
		labPlnLandIn.setBounds(10, 65, 107, 19);
		labPlnLandIn.setText("\u8BA1\u5212\u843D\u5730\u65F6\u95F4\uFF1A");
		
		txtPlnLandIn = new Text(group_1, SWT.BORDER);
		txtPlnLandIn.setBounds(133, 63, 184, 25);
		
		Label labFlightTime = new Label(group_1, SWT.NONE);
		labFlightTime.setBounds(10, 105, 60, 19);
		labFlightTime.setText("\u98DE\u884C\u65F6\u95F4\uFF1A");
		
		txtFlightTime = new Text(group_1, SWT.BORDER);
		txtFlightTime.setBounds(133, 105, 184, 25);
		
		Group grpWeek = new Group(composite_1, SWT.NONE);
		grpWeek.setBounds(10, 361, 327, 250);
		grpWeek.setText("\u6267\u884C\u9891\u7387");
		
		cbMonday = new Button(grpWeek, SWT.CHECK);
		cbMonday.setBounds(28, 20, 69, 19);
		cbMonday.setText("\u661F\u671F\u4E00");
		
		cbTuesday = new Button(grpWeek, SWT.CHECK);
		cbTuesday.setBounds(28, 53, 69, 19);
		cbTuesday.setText("\u661F\u671F\u4E8C");
		
		cbWednesday = new Button(grpWeek, SWT.CHECK);
		cbWednesday.setBounds(28, 86, 69, 19);
		cbWednesday.setText("\u661F\u671F\u4E09");
		
		cbThursday = new Button(grpWeek, SWT.CHECK);
		cbThursday.setBounds(28, 119, 69, 19);
		cbThursday.setText("\u661F\u671F\u56DB");
		
		cbFriday = new Button(grpWeek, SWT.CHECK);
		cbFriday.setBounds(28, 152, 69, 19);
		cbFriday.setText("\u661F\u671F\u4E94");
		
		cbSaturday = new Button(grpWeek, SWT.CHECK);
		cbSaturday.setBounds(28, 185, 69, 19);
		cbSaturday.setText("\u661F\u671F\u516D");
		
		cbSunday = new Button(grpWeek, SWT.CHECK);
		cbSunday.setBounds(28, 218, 69, 19);
		cbSunday.setText("\u661F\u671F\u5929");
		
		CTabItem tbParam = new CTabItem(tabFolder, SWT.NONE);
		tbParam.setText("\u822A\u7EBF");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbParam.setControl(composite_2);
		
		Group group_3 = new Group(composite_2, SWT.NONE);
		group_3.setText("\u822A\u7EBF\u53C2\u6570");
		group_3.setBounds(21, 31, 327, 133);
		
		Label lbType = new Label(group_3, SWT.WRAP);
		lbType.setText("\u822A\u5B63\uFF1A");
		lbType.setBounds(10, 31, 55, 19);
		
		cmbRouteType = new Combo(group_3, SWT.READ_ONLY);
		
		cmbRouteType.setBounds(144, 24, 147, 27);
		
		Label labSeat = new Label(group_3, SWT.NONE);
		labSeat.setText("\u5EA7\u4F4D\u6570");
		labSeat.setBounds(10, 81, 105, 19);
		
		txtSeat = new Text(group_3, SWT.BORDER);
		txtSeat.setBounds(144, 81, 147, 25);
		
		
		initCmbItems();
		AirportModifyListener apmodifyListener = new AirportModifyListener
				(cmbDepAirport, cmbArrAirport, cmbAttribute, cmbInOut,airportService);
		cmbArrAirport.addModifyListener(apmodifyListener);
		cmbDepAirport.addModifyListener(apmodifyListener);
		// TODO Auto-generated method stub
		return null;
	}
}
