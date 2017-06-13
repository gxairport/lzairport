package com.lzairport.ais.tablecolor;

import com.lzairport.ais.models.AisRGB;
import com.lzairport.ais.models.aodb.Flight;

public class FlightBkColor implements ITableColor {

	@Override
	public AisRGB getRgbObj(Object object) {
		// TODO Auto-generated method stub
		return ((Flight)object).getState();
	}

}
