package com.ptcm.view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import com.ptcm.resource.Language;
import com.ptcm.resource.LanguageResource;

public class MainGui extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menuBar;
	private JMenu menuFile,menuEdit,menuHelp;
	private JTabbedPane mainTabpane,managermentTabpane;
	private JPanel jpnTabManagermentStation,jpnTabManagermentCar,jpnTabManagermentDriver,jpnTabManagermentOwner,jpnTabSchedule,jpnTabNotify;
	private JTextField searchTextbox;
	private JButton btnEditStation,btnAddStation,btnDeleteStation,btnNotify,btnGenerateSchedule;
	private JCheckBox cbSelectAll;
	private JTable tblStation;
	private JComboBox<String>cbbBus;
	private TableModel mdStationTable,mdCarTable,mdDriverTable,mdOwnerTable,mdScheduleTable,mdNotifyTable;
	
	private LanguageResource lang;
	
	public MainGui() {
		// TODO Auto-generated constructor stub
		setTitle("Passenger Terminal Car manager");
		/**
		 * @lang is language to use all system;
		 */
		lang = new LanguageResource("EN");
		
		createMainTabPane();
		setMinimumSize(new Dimension(600,400));
		pack();
	}


	private void createMainTabPane() {
		JScrollPane jspPane = new JScrollPane(mainTabpane = new JTabbedPane(JTabbedPane.LEFT));
		mainTabpane.setMinimumSize(new Dimension(600, 400));
		mainTabpane.addTab(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB), managermentTabpane = new JTabbedPane(JTabbedPane.TOP));
		
		managermentTabpane.addTab(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_STATION), jpnTabManagermentStation = new JPanel());
		
		
		managermentTabpane.addTab(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_CAR), jpnTabManagermentCar = new JPanel());
		managermentTabpane.addTab(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_DRIVER), jpnTabManagermentDriver = new JPanel());
		
		mainTabpane.addTab(lang.getLanguage(Language.MAIN_SCHEDULE_TAB), jpnTabSchedule = new JPanel());
		
		
		
		mainTabpane.addTab(lang.getLanguage(Language.MAIN_NOTIFY_TAB), jpnTabNotify = new JPanel());
		
		
		managermentTabpane.addTab("Station", new JPanel());
		
		
		add(jspPane);
	}
	
	
	public static void main(String[] args) {
		new MainGui().setVisible(true);
	}
	
}
