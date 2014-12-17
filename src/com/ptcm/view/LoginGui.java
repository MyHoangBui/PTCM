package com.ptcm.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javafx.scene.layout.GridPane;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.ptcm.common.Config;
import com.ptcm.model.User;
import com.ptcm.resource.Language;

public class LoginGui extends JFrame implements Runnable,ActionListener{
	private JTextField jtfUserName,jtfPassword;
	private JCheckBox cbSavePassword;
	private JButton btnLogin;

	private Config config;

	public LoginGui(Config config) {
		// TODO Auto-generated constructor stub
		this.config = config;
		setTitle(config.getLang().getLanguage(Language.MAIN_BTN_SEARCH));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		createGui();
		pack();
	}


	private void createGui() {
		// TODO Auto-generated method stub
		GridLayout grid = new GridLayout(2, 2);
		JPanel p = new JPanel();
		p.setLayout(grid);
		p.add(new JLabel("Username: "));
		p.add(jtfUserName = new JTextField(20));
		p.add(new JLabel("Password: "));
		p.add(jtfPassword = new JTextField(20));

		add(p);
		add(btnLogin = new JButton("Login"),BorderLayout.SOUTH);
		btnLogin.addActionListener(this);
		pack();
	}


	



	public Config getConfig() {
		return config;
	}


	public void setConfig(Config config) {
		this.config = config;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		new LoginGui(this.config).setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnLogin)) {
			String username = jtfUserName.getText();
			String password = jtfPassword.getText();
			if(!username.equals(null) && !password.equals(null)){
				User user = new User(0, username, password);
				String field[] = {"username","password"};
				ArrayList<ArrayList<String>> result = this.config.getDb().searchObject(user,field);
				int row = result.size();
				if(row == 0){
					JOptionPane.showMessageDialog(this, "Login failed!!");
					
				}else if(row == 1){
					User userLogin = new User(Integer.parseInt(result.get(0).get(0)),result.get(0).get(1) , result.get(0).get(2));
					this.config.setUser(userLogin);
					this.dispose();
					SwingUtilities.invokeLater(new MainGui(config));
					
				}
			}else{
				JOptionPane.showConfirmDialog(this, "Username or password invalid!!");
			}
			
				
		}
	}



}

