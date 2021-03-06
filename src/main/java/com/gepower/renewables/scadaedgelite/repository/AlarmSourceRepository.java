package com.gepower.renewables.scadaedgelite.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gepower.renewables.scadaedgelite.dao.impl.AlarmDaoImpl;
import com.gepower.renewables.scadaedgelite.opcuaclient.model.Alarm;

@Repository
public class AlarmSourceRepository {

@Autowired
AlarmDaoImpl alarmDaoImpl;

@Autowired
public AlarmSourceRepository(AlarmDaoImpl alarmDaoImpl){
	this.alarmDaoImpl =alarmDaoImpl;
}
	private Map<String, Alarm> alarmDataMap = new HashMap<>();
	//AlarmDaoImpl alarmDaoImpl  =new AlarmDaoImpl();
	List<Alarm> firstList = new ArrayList<Alarm>(); 	
	List<Alarm> alarmList = new ArrayList<Alarm>(); 
	
	
	public Collection<Alarm> getAlarms(String []assetName){
		String asset="";
		//System.out.println("assetName.length:::"+assetName.length);
		if(assetName == null || assetName.equals("") ){
			alarmList = alarmDaoImpl.getAlarmsList();
			System.out.println("Rnter no value");
		}else{
			for(int i=0;i<assetName.length;i++){
				asset = assetName[i];
				System.out.println("asset:::::::::"+asset);
				firstList = alarmDaoImpl.filterAlarmsList(asset);
				alarmList.addAll(firstList);
				System.out.println("alarmList::::"+alarmList.size());
			}
		}



		//List<Alarm> alarmList = alarmDaoImpl.getAlarmsList(assetName);
		String val="";
		int i = 1;
		for(Iterator<Alarm> iter=alarmList.iterator();iter.hasNext();){
			i=i+1;
			val = Integer.toString(i);
			Alarm alarm = iter.next();
			alarmDataMap.put(val,alarm);

		}
		return alarmDataMap.values();
	}

}
