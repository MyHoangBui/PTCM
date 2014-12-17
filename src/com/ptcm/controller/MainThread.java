package com.ptcm.controller;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.ptcm.common.Config;
import com.ptcm.resource.LanguageResource;
import com.ptcm.view.LoginGui;
import com.ptcm.view.MainGui;

public class MainThread {
	public static void main(String[] args) {
		
		Config config = new Config();
		LanguageResource lang  = new LanguageResource("EN");
		config.setLang(lang);
		
		LoginGui gui = new LoginGui(config);
		
		Runnable mainGui = new MainGui(config);
		
		try {
			//SwingUtilities.invokeAndWait(mainGui);
			LoginGui loginGui = new LoginGui(config);
			SwingUtilities.invokeLater(loginGui);
							
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
}
