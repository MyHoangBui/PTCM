package com.ptcm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import sun.awt.DefaultMouseInfoPeer;

import com.ptcm.common.Config;
import com.ptcm.model.Station;
import com.ptcm.resource.Language;
import com.ptcm.resource.LanguageResource;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

public class MainGui extends JFrame implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JMenuBar menuBar;
	private JMenu menuFile,menuEdit,menuHelp;
	private JMenuItem mnitemNew, mnitemOpen, mnitemSave, mnitemopy, mnitemPast, mnitemDelete, mnitemIndex, mnitemFind;
	private JTabbedPane mainTabpane,managermentTabpane;
	private JPanel jpnTabManagermentStation,jpnTabManagermentCar,jpnTabManagermentDriver,jpnTabManagermentOwner,jpnTabSchedule,jpnTabNotify;
	private JTextField searchTextbox;
	private JButton btnEditStation,btnAddStation,btnDeleteStation,btnNotify,btnGenerateSchedule, btnSearch, btnBus;
	private JCheckBox cbSelectAll;
	private JTable tblStation;
	private JComboBox<String>cbbBus;
	private TableModel mdStationTable,mdCarTable,mdDriverTable,mdOwnerTable,mdScheduleTable,mdNotifyTable;


	private LanguageResource lang;
	private Config config;

	public MainGui(Config config) {

		// TODO Auto-generated constructor stub
		this.config = config;
		setTitle("Passenger Terminal Car manager");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		lang = this.config.getLang();
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

	}


	private void createMainTabPane() {
		JScrollPane jspPane = new JScrollPane(mainTabpane = new JTabbedPane(JTabbedPane.LEFT));
		mainTabpane.setMinimumSize(new Dimension(600, 400));

		mainTabpane.addTab(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB), managermentTabpane = new JTabbedPane(JTabbedPane.TOP));

		managermentTabpane.addTab(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_STATION), jpnTabManagermentStation = new JPanel());

		createTabStation();


		managermentTabpane.addTab(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_CAR), jpnTabManagermentCar = new JPanel());
		managermentTabpane.addTab(lang.getLanguage(Language.MAIN_MANAGERMENT_TAB_DRIVER), jpnTabManagermentDriver = new JPanel());

		mainTabpane.addTab(lang.getLanguage(Language.MAIN_SCHEDULE_TAB), jpnTabSchedule = new JPanel());
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
		JPanel p = new JPanel();
		BoxLayout bl = new BoxLayout(p, BoxLayout.Y_AXIS);
		BorderLayout bdl = new BorderLayout();
		p.setLayout(bl);
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
		ArrayList<ArrayList<String>>data = this.config.getDb().getObject(new Station(0,0,0,""), 1);
		
		DefaultTableModel tblmd = new DefaultTableModel(data.get(0).toArray(),0);
		tblStation.setModel(tblmd);
		b2.add(jscPtable);

		
		//jpnTabManagermentStation.setLayout(new BoxLayout(jpnTabManagermentStation, BoxLayout.Y_AXIS));
		p.add(b1);
		p.add(b2);
		JPanel p1 = new JPanel();
		p1.add(p);
		jpnTabManagermentStation.setLayout(bdl);
		jpnTabManagermentStation.add(b1,BorderLayout.NORTH);
		jpnTabManagermentStation.add(jscPtable);
		
		//jpnTabManagermentStation.add(b);
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



	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			LookAndFeelInfo look[] = UIManager.getInstalledLookAndFeels();
			for (LookAndFeelInfo lookAndFeelInfo : look) {

				System.out.println(lookAndFeelInfo.getClassName());
			}
			UIManager.setLookAndFeel(this.config.getLookAndFeel());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setVisible(true);
	}


	/*public static void main(String[] args) {

	}*/

}