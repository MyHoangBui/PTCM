package com.ptcm.view;

<<<<<<< HEAD
import java.awt.Dimension;

=======
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
>>>>>>> 6780817cba31be00959f852a705aed9de4fd25fa
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
<<<<<<< HEAD
=======
import javax.swing.JMenuItem;
>>>>>>> 6780817cba31be00959f852a705aed9de4fd25fa
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
<<<<<<< HEAD
	private JTabbedPane mainTabpane,managermentTabpane;
	private JPanel jpnTabManagermentStation,jpnTabManagermentCar,jpnTabManagermentDriver,jpnTabManagermentOwner,jpnTabSchedule,jpnTabNotify;
	private JTextField searchTextbox;
	private JButton btnEditStation,btnAddStation,btnDeleteStation,btnNotify,btnGenerateSchedule;
=======
	private JMenuItem mnitemNew, mnitemOpen, mnitemSave, mnitemopy, mnitemPast, mnitemDelete, mnitemIndex, mnitemFind;
	private JTabbedPane mainTabpane,managermentTabpane;
	private JPanel jpnTabManagermentStation,jpnTabManagermentCar,jpnTabManagermentDriver,jpnTabManagermentOwner,jpnTabSchedule,jpnTabNotify;
	private JTextField searchTextbox;
	private JButton btnEditStation,btnAddStation,btnDeleteStation,btnNotify,btnGenerateSchedule, btnSearch, btnBus;
>>>>>>> 6780817cba31be00959f852a705aed9de4fd25fa
	private JCheckBox cbSelectAll;
	private JTable tblStation;
	private JComboBox<String>cbbBus;
	private TableModel mdStationTable,mdCarTable,mdDriverTable,mdOwnerTable,mdScheduleTable,mdNotifyTable;
	
<<<<<<< HEAD
=======
	
>>>>>>> 6780817cba31be00959f852a705aed9de4fd25fa
	private LanguageResource lang;
	
	public MainGui() {
		// TODO Auto-generated constructor stub
		setTitle("Passenger Terminal Car manager");
<<<<<<< HEAD
		/**
		 * @lang is language to use all system;
		 */
		lang = new LanguageResource("EN");
		
		createMainTabPane();
		setMinimumSize(new Dimension(600,400));
		pack();
=======
		
		lang = new LanguageResource("EN");
		createMainTabPane();
		setMinimumSize(new Dimension(600,400));
		setJMenuBar(menuBar = new JMenuBar());
		menuBar.add(menuFile = new JMenu("File"));
		menuFile.add(mnitemNew = new JMenuItem("New"));
		menuFile.add(mnitemOpen = new JMenuItem("Open"));
		menuFile.add(mnitemSave = new JMenuItem("Save"));
		menuBar.add(menuEdit = new JMenu("Edit"));
		menuEdit.add(mnitemopy = new JMenuItem("Copy"));
		menuEdit.add(mnitemPast = new JMenuItem("Past"));
		menuEdit.add(mnitemDelete = new JMenuItem("Delete"));
		
		menuBar.add(menuHelp = new JMenu("Help"));
		menuHelp.add(mnitemIndex = new JMenuItem("Index"));
		menuHelp.add(mnitemFind = new JMenuItem("Find"));
		
		pack();
		
>>>>>>> 6780817cba31be00959f852a705aed9de4fd25fa
	}


	private void createMainTabPane() {
		JScrollPane jspPane = new JScrollPane(mainTabpane = new JTabbedPane(JTabbedPane.LEFT));
		mainTabpane.setMinimumSize(new Dimension(600, 400));
<<<<<<< HEAD
=======
		
>>>>>>> 6780817cba31be00959f852a705aed9de4fd25fa
		mainTabpane.addTab(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB), managermentTabpane = new JTabbedPane(JTabbedPane.TOP));
		
		managermentTabpane.addTab(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_STATION), jpnTabManagermentStation = new JPanel());
		
<<<<<<< HEAD
=======
		createTabStation();
		
>>>>>>> 6780817cba31be00959f852a705aed9de4fd25fa
		
		managermentTabpane.addTab(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_CAR), jpnTabManagermentCar = new JPanel());
		managermentTabpane.addTab(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_DRIVER), jpnTabManagermentDriver = new JPanel());
		
		mainTabpane.addTab(lang.getLanguage(Language.MAIN_SCHEDULE_TAB), jpnTabSchedule = new JPanel());
<<<<<<< HEAD
		
		
		
		mainTabpane.addTab(lang.getLanguage(Language.MAIN_NOTIFY_TAB), jpnTabNotify = new JPanel());
		
		
		managermentTabpane.addTab("Station", new JPanel());
		
		
		add(jspPane);
	}
	
	
=======
		createSchedule();
		
		mainTabpane.addTab(lang.getLanguage(Language.MAIN_NOTIFY_TAB), jpnTabNotify = new JPanel());
		Box b = Box.createVerticalBox();
		JPanel b1 = new JPanel();
		JPanel b2 = new JPanel();
		
		JPanel jpbtnotify = new JPanel();
		jpbtnotify.add(btnBus = new JButton(lang.getLanguage(Language.MAIN_NOTIFY_TAB_BT_BUS)));
		jpbtnotify.add(cbbBus = new JComboBox<String>());
		b1.add(jpbtnotify);
		JScrollPane jscPtable = new JScrollPane(tblStation = new JTable());
		b2.add(jscPtable);
		
		b.add(b1);
		b.add(b2);
		jpnTabNotify.add(b);
		managermentTabpane.addTab("Station", new JPanel());
		
		add(jspPane);
	}


	private void createTabStation() {
		Box b = Box.createVerticalBox();
		JPanel b1 = new JPanel();
		JPanel b2 = new JPanel();
		JPanel jpnbution = new JPanel();
		jpnbution.add(cbSelectAll = new JCheckBox(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_STATION_CB_SELECTALL)));
		jpnbution.add(btnEditStation = new JButton(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_STATION_BTN_EDIT)));
		jpnbution.add(btnAddStation = new JButton(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_STATION_BTN_ADDSTATION)));
		jpnbution.add(btnAddStation = new JButton(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_STATION_BTN_DELETE)));
		jpnbution.add(searchTextbox = new JTextField(15));
		jpnbution.add(btnSearch = new JButton(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_STATION_BTN_SEARCH)));
		b1.add(jpnbution);
		
		JScrollPane jscPtable = new JScrollPane(tblStation = new JTable());
		
		b2.add(jscPtable);
		
		b.add(b1);
		b.add(b2);
		jpnTabManagermentStation.add(b);
	}
	
	private void createSchedule()
	{
	Box b = Box.createVerticalBox();
	JPanel b1 = new JPanel();
	JPanel b2 = new JPanel();
	
	JPanel jpBut = new JPanel();
	jpBut.add(btnNotify = new JButton(lang.getLanguage(Language.MAIN_SCHEDULE_TAB_BTN_NOTIFY)));
	jpBut.add(btnGenerateSchedule = new JButton(lang.getLanguage(Language.MAIN_SCHEDULE_TAB_BTN_GENERRATESSCHEDULE)));
	jpBut.add(cbbBus = new JComboBox<String>());
	jpBut.add(searchTextbox = new JTextField(15));
	jpBut.add(btnSearch = new JButton(lang.getLanguage(Language.MAIN_SCHEDULE_TAB_BTN_SEARCH)));
	b1.add(jpBut);
	
	JScrollPane jscPtable = new JScrollPane(tblStation = new JTable());
	b2.add(jscPtable);
	
	b.add(b1);
	b.add(b2);
	jpnTabSchedule.add(b);
	}
>>>>>>> 6780817cba31be00959f852a705aed9de4fd25fa
	public static void main(String[] args) {
		new MainGui().setVisible(true);
	}
	
<<<<<<< HEAD
}
=======
}
>>>>>>> 6780817cba31be00959f852a705aed9de4fd25fa
