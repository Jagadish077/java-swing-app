package mainApp;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dbutilities.DatabaseConnection;

public class Register extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 10058054L;
	private JLabel usernamelabel, passwordlabel, selectDivision, tittle, sex;
	private JTextField usernametxt, passwordtxt;
	private JButton Registerbtn, Cancel,b;
	private JPanel gender = new JPanel();
	private JPanel pane = new JPanel(new GridBagLayout());
	private JComboBox<String> division;
	private JRadioButton male,female;
	private ButtonGroup g;
	private Font f = new Font("Ubuntu Mono Regular", Font.TRUETYPE_FONT, 14);
	private Font f1 = new Font("Ubuntu Mono Regular", Font.TRUETYPE_FONT, 22);
	private Cursor c = new Cursor(Cursor.HAND_CURSOR);
	private Connection con;
	private PreparedStatement pst;
	private JRadioButton temp;
	public Register(){
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Registration Form");
		setBounds(300, 200, 500, 350);
		GridBagConstraints c2 = new GridBagConstraints();
		
		c2.insets = new Insets(13, 0, 6, 6);
		
//		c2.gridx=0;c2.gridy=0;
//		tittle = new JLabel("Registration Form");
//		tittle.setForeground(Color.MAGENTA);
//		tittle.setPreferredSize(new Dimension(250, 40));
//		tittle.setFont(f1);
//		pane.add(tittle, c2);
		
		c2.gridx=0;c2.gridy=1;
		usernamelabel = new JLabel("Enter Your Username");
		usernamelabel.setFont(f);
		usernamelabel.setForeground(Color.DARK_GRAY);
		pane.add(usernamelabel, c2);
		
		c2.gridx=1;c2.gridy=1;
		usernametxt = new JTextField();
		usernametxt.setForeground(Color.DARK_GRAY);
		usernametxt.setFont(f);
		usernametxt.setPreferredSize(new Dimension(150, 30));
		pane.add(usernametxt, c2);
		
		c2.gridx=0;c2.gridy=2;
		passwordlabel = new JLabel("Enter your Password");
		passwordlabel.setFont(f);
		passwordlabel.setForeground(Color.DARK_GRAY);
		pane.add(passwordlabel, c2);
		
		c2.gridx=1;c2.gridy=2;
		passwordtxt = new JTextField();
		passwordtxt.setForeground(Color.DARK_GRAY);
		passwordtxt.setFont(f);
		passwordtxt.setPreferredSize(new Dimension(150, 30));
		pane.add(passwordtxt, c2);
		
		c2.gridx=0;c2.gridy=3;
		sex = new JLabel("Select Gender");
		sex.setFont(f);
		sex.setForeground(Color.DARK_GRAY);
		pane.add(sex, c2);
		
		g = new ButtonGroup();
		male = new JRadioButton("Male");
		male.setForeground(Color.DARK_GRAY);
		male.setFont(f);
		male.setBackground(Color.LIGHT_GRAY);
		
		
		female = new JRadioButton("Female", true);
		female.setFont(f);
		female.setBackground(Color.LIGHT_GRAY);
		female.setForeground(Color.DARK_GRAY);
		g.add(male);
		g.add(female);
		c2.gridx=1;c2.gridy=3;
		gender.add(male);
		gender.add(female);
		pane.add(gender, c2);
		
		c2.gridx=0;c2.gridy=4;
		selectDivision = new JLabel("Select Division");
		selectDivision.setFont(f);
		selectDivision.setForeground(Color.DARK_GRAY);
		String[] elements = {"Admin", "Employee", "User", "Staff"};
		pane.add(selectDivision, c2);
		
		c2.gridx=1;c2.gridy=4;
		division = new JComboBox<>(elements);
		division.setPreferredSize(new Dimension(150, 30));
		division.setFont(f);
		division.setForeground(Color.DARK_GRAY);
		pane.add(division, c2);
		
		c2.gridx=0;c2.gridy=5;
		Registerbtn = new JButton("Register");
		Registerbtn.setForeground(Color.DARK_GRAY);
		Registerbtn.setCursor(c);
		Registerbtn.setPreferredSize(new Dimension(150, 30));
		Registerbtn.addActionListener(this);
		pane.add(Registerbtn, c2);

		c2.gridx=1;c2.gridy=5;
		Cancel = new JButton("Cancel");
		Cancel.addActionListener(this);
		Cancel.setForeground(Color.DARK_GRAY);
		Cancel.setCursor(c);
		Cancel.setPreferredSize(new Dimension(150, 30));
		pane.add(Cancel, c2);
		
		c2.gridx=0;c2.gridy=6;
		JLabel l = new JLabel("Already Registered? login");
		l.setCursor(c);
		l.setBackground(Color.LIGHT_GRAY);
		pane.add(l, c2);
		
		c2.gridx=1;c2.gridy=6;
		b = new JButton("Login");
		b.setPreferredSize(new Dimension(150, 30));
		b.setCursor(c);
		b.addActionListener(this);
		b.setBackground(Color.LIGHT_GRAY);
		pane.add(b, c2);
		
		pane.setBackground(Color.LIGHT_GRAY);
		gender.setBackground(Color.LIGHT_GRAY);
		add(pane);
		
	}
	public String getGender(ButtonGroup g){
		for(Enumeration<AbstractButton> allradiobuttons = g.getElements(); allradiobuttons.hasMoreElements();){
			AbstractButton button = allradiobuttons.nextElement();
			if(button.isSelected()){
				return button.getText();
			}
		}
		return null;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Cancel){
			this.setVisible(false);
		}else if(e.getSource()==Registerbtn){
			try{
				String sql = "INSERT INTO User VALUES(?,?,?,?)";
				DatabaseConnection c = new DatabaseConnection();
				con = c.getConnection();
				pst = con.prepareStatement(sql);
				pst.setString(1, usernametxt.getText());
				pst.setString(2, passwordtxt.getText());
				pst.setString(3, division.getSelectedItem().toString());
				String g1 = getGender(g);
				pst.setString(4, g1);
				int a = pst.executeUpdate();
				if(a>0){
					this.setVisible(false);
					new Admin().setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "Somthing Went Wrong");
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				try{
					con.close();
					pst.close();
				}catch(Exception exe){
					exe.printStackTrace();
				}
			}
		}else{
			new Register().setVisible(true);
		}
	}
	
	public static void main(String[] args) {
		Register r = new Register();
		r.setVisible(true);
	}

}
