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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dbutilities.DatabaseConnection;

public class Admin extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;

	Connection con;
	PreparedStatement ps;
	private JLabel usernamelabel, passwordlabel, tittle, selectDivision;
	private JTextField usernametxt, passwordtxt;
	private JPanel pane = new JPanel(new GridBagLayout());
	private JButton login, Cancel,b;
	private JComboBox<String> division;
	private Font f = new Font("Ubuntu Mono Regular", Font.TRUETYPE_FONT, 14);
	private Cursor c = new Cursor(Cursor.HAND_CURSOR);
	public Admin(){
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Registration Form");
		setBounds(200, 100, 500, 350);
		GridBagConstraints c2 = new GridBagConstraints();
		
		c2.insets = new Insets(13, 0, 6, 6);
		
		
//		c2.gridx=0;c2.gridy=0;
//		tittle = new JLabel("Registration Form");
//		pane.add(tittle, c2);
		
		c2.gridx=0;c2.gridy=1;
		usernamelabel = new JLabel("Enter Your Username");
		usernamelabel.setForeground(Color.DARK_GRAY);
		usernamelabel.setFont(f);
		pane.add(usernamelabel, c2);
		
		c2.gridx=1;c2.gridy=1;
		usernametxt = new JTextField();
		usernametxt.setForeground(Color.DARK_GRAY);
		usernametxt.setPreferredSize(new Dimension(150, 30));
		usernametxt.setFont(f);
		pane.add(usernametxt, c2);
		
		c2.gridx=0;c2.gridy=2;
		passwordlabel = new JLabel("Enter your Password");
		passwordlabel.setForeground(Color.DARK_GRAY);
		passwordlabel.setFont(f);
		pane.add(passwordlabel, c2);
		
		c2.gridx=1;c2.gridy=2;
		passwordtxt = new JTextField();
		passwordtxt.setForeground(Color.DARK_GRAY);
		passwordtxt.setPreferredSize(new Dimension(150, 30));
		passwordtxt.setFont(f);
		pane.add(passwordtxt, c2);
		
		c2.gridx=0;c2.gridy=3;
		selectDivision = new JLabel("Select Division");
		selectDivision.setForeground(Color.DARK_GRAY);
		selectDivision.setFont(f);
		String[] elements = {"Admin", "Employee", "User", "Staff"};
		pane.add(selectDivision, c2);
		
		c2.gridx=1;c2.gridy=3;
		division = new JComboBox<>(elements);
		division.setForeground(Color.DARK_GRAY);
		division.setPreferredSize(new Dimension(150, 30));
		division.setFont(f);
		pane.add(division, c2);
		
		c2.gridx=0;c2.gridy=4;
		login = new JButton("Login");
		login.setPreferredSize(new Dimension(150, 30));
		login.setCursor(c);
		login.addActionListener(this);
		pane.add(login, c2);

		c2.gridx=1;c2.gridy=4;
		Cancel = new JButton("Cancel");
		Cancel.setPreferredSize(new Dimension(150, 30));
		Cancel.setCursor(c);
		Cancel.addActionListener(this);
		pane.setBackground(Color.LIGHT_GRAY);
		pane.add(Cancel, c2);
		
		
		
		c2.gridx=0;c2.gridy=5;
		JLabel l = new JLabel("Not Yet Registered? click here to register");
		l.setCursor(c);
		l.setBackground(Color.LIGHT_GRAY);
		pane.add(l, c2);
		
		c2.gridx=1;c2.gridy=5;
		b = new JButton("Register");
		b.setPreferredSize(new Dimension(150, 30));
		b.setCursor(c);
		b.addActionListener(this);
		b.setBackground(Color.LIGHT_GRAY);
		pane.add(b, c2);
		
		add(pane);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==login){
			try{
				String sql = "SELECT * FROM User where"
						+ " username='"+usernametxt.getText()+"'"
								+ " and password='"+passwordtxt.getText()+"'"
										+ " and division='"+division.getSelectedItem()+"'";
				DatabaseConnection d = new DatabaseConnection();
				this.con = d.getConnection();
				this.ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					this.setVisible(false);
					new MobileData().setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "Invalid Credintials");
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				try{
					ps.close();
					con.close();
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}else if(e.getSource()==Cancel){
			this.setVisible(false);
		}else if(e.getSource()==b){
			this.setVisible(false);
			new Register().setVisible(true);
		}
	}
	
	public static void main(String[] args) {
		Admin a = new Admin();
		a.setVisible(true);
	}
}
