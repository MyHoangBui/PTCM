package com.ptcm.controller;

import java.awt.Font;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;

import com.ptcm.common.Config;
import com.ptcm.resource.LanguageResource;
import com.ptcm.view.LoginGui;
import com.ptcm.view.MainGui;
import com.sun.javafx.collections.MappingChange.Map;
import com.sun.javafx.scene.accessibility.Attribute;

public class MainThread {
	public static void main(String[] args) {

		Config config = new Config();
		LanguageResource lang  = new LanguageResource("EN");
		config.setLang(lang);
		config.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");

		setUIFont();
		try {
			LoginGui loginGui = new LoginGui(config);
			SwingUtilities.invokeLater(loginGui);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}


	}
	public static void setUIFont (javax.swing.plaf.FontUIResource f){
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();

			Object value = UIManager.get (key);
			if (value != null && value instanceof javax.swing.plaf.FontUIResource){
				 
				
				System.out.println(((FontUIResource)value).getSize());
				UIManager.put (key, f);
			}
		}
	}
	public static void setUIFont (){
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();

			Object value = UIManager.get (key);
			if (value != null && value instanceof javax.swing.plaf.FontUIResource){
				 
				FontUIResource uir = (FontUIResource)value;
				Font font = new Font(uir.getFontName(), uir.getStyle(), uir.getSize()+2);
				//System.out.println(uir.getSize());
				FontUIResource n = new FontUIResource(font);
				UIManager.put (key, n);
			}
		}
	}
}
