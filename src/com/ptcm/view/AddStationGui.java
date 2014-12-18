package com.ptcm.view;

import javax.swing.JFrame;

import com.ptcm.common.Config;
import com.ptcm.resource.Language;
import com.ptcm.resource.LanguageResource;

public class AddStationGui extends JFrame implements Runnable{
	
	private Config config;
	
	public AddStationGui(Config config) {
		// TODO Auto-generated constructor stub
		this.config = config;
		LanguageResource lang = this.config.getLang();
		setTitle(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_STATION_ADDFRAME_TITLE));
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		setVisible(true);
	}
	
}
