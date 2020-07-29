package mainApp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dbutilities.DatabaseConnection;

public class SoldMobiles extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel pane = new JPanel(new GridBagLayout());
	private JPanel pane1 = new JPanel(new BorderLayout());
	
	private JLabel modelnumber, errormessage;
	private JTextField modelNumbert;
	private JButton Load;
	
	private Connection con;
	private PreparedStatement ps;
	
	private JLabel mobilename, mobileprice, mobileqty, mobilemodel, mobileimage, mobileImage;
	private JLabel mobileName, mobilePrice, mobileQty, mobileModel;
	private JButton sell, Print;
	private GridBagConstraints g = new GridBagConstraints();
	
	public SoldMobiles() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Mobile Cart Form");
		setBounds(200, 100, 600, 700);
		
		
		g.insets = new Insets(12, 8, 8, 8);
		g.gridx=0; g.gridy=0;
		modelnumber = new JLabel("Enter the Model Number");
		modelnumber.setPreferredSize(new Dimension(200, 30));
		pane.add(modelnumber, g);
		
		
		g.gridx=1; g.gridy=0;
		modelNumbert = new JTextField();
		modelNumbert.setPreferredSize(new Dimension(150, 30));
		pane.add(modelNumbert, g);
		
		
		
		g.gridx=0; g.gridy=1;
		Load = new JButton("Load Data");
		Load.addActionListener(this);
		pane.add(Load, g);
		add(pane);
	
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(isMobileExists(modelNumbert.getText()) && arg0.getSource()==Load){
			
				try{
					System.out.println("Clicked");
					String sql = "Select * from product where productid='"+modelNumbert.getText()+"'";
					DatabaseConnection d = new DatabaseConnection();
					con = d.getConnection();
					ps = con.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						System.out.println("entered While loop");
						g.gridx=0; g.gridy=4;
						mobilename = new JLabel("Mobile Name");
						pane.add(mobilename, g);
						
						g.gridx=1;g.gridy=4;
						mobileName = new JLabel();
						mobileName.setText(rs.getString("productName"));
						pane.add(mobileName, g);
						
						
						g.gridx=0; g.gridy=5;
						mobileprice = new JLabel("Mobile Price");
						pane.add(mobileprice, g);
						
						g.gridx=1;g.gridy=5;
						mobilePrice = new JLabel();
						mobilePrice.setText(rs.getString("productPrice"));
						pane.add(mobilePrice, g);
						
						
						g.gridx=0; g.gridy=6;
						mobileqty = new JLabel("Processor");
						pane.add(mobileqty, g);
						
						g.gridx=1;g.gridy=6;
						mobileQty = new JLabel();
						mobileQty.setText(rs.getString("batteryDetails"));
						pane.add(mobileQty, g);
						
						g.gridx=0; g.gridy=7;
						mobilemodel = new JLabel("Mobile Model");
						pane.add(mobilemodel, g);
						
						g.gridx=1;g.gridy=7;
						mobileModel = new JLabel();
						mobileModel.setText(rs.getString("productModelNumber"));
						pane.add(mobileModel, g);
						
						g.gridx=0;g.gridy=8;
						mobileModel = new JLabel("Mobile Quantity");
						pane.add(mobileModel, g);
						
						g.gridx=1;g.gridy=8;
						mobileModel = new JLabel();
						mobileModel.setText(rs.getString("productQty"));
						pane.add(mobileModel, g);
						
						g.gridx=0;g.gridy=9;
						mobileModel = new JLabel("Ram");
						pane.add(mobileModel, g);
						
						g.gridx=1;g.gridy=9;
						mobileModel = new JLabel();
						mobileModel.setText(rs.getString("productRam"));
						pane.add(mobileModel, g);
						
						g.gridx=0;g.gridy=10;
						mobileModel = new JLabel("Rom");
						pane.add(mobileModel, g);
						
						g.gridx=1;g.gridy=10;
						mobileModel = new JLabel();
						mobileModel.setText(rs.getString("productRom"));
						pane.add(mobileModel, g);
						
						g.gridx=0;g.gridy=11;
						mobileModel = new JLabel("Battery");
						pane.add(mobileModel, g);
						
						g.gridx=1;g.gridy=11;
						mobileModel = new JLabel();
						mobileModel.setText(rs.getString("productProcessor"));
						pane.add(mobileModel, g);
						
						g.gridx=0;g.gridy=12;
						mobileModel = new JLabel("Color");
						pane.add(mobileModel, g);
						
						g.gridx=1;g.gridy=12;
						mobileModel = new JLabel();
						mobileModel.setText(rs.getString("productColor"));
						pane.add(mobileModel, g);
						
						g.gridx=0;g.gridy=13;
						sell = new JButton("Sell");
						sell.setPreferredSize(new Dimension(150, 40));
						sell.addActionListener(this);
						pane.add(sell, g);
						
						g.gridx=1;g.gridy=13;
						Print = new JButton("Print");
						Print.setPreferredSize(new Dimension(150, 40));
						Print.addActionListener(this);
						pane.add(Print, g);
		
						this.revalidate();
						
						
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
		}else if(arg0.getSource()==sell){
			
		}else{
			JOptionPane.showMessageDialog(null, "Invalid Model Number");
		}
		
		
	}
	
	private boolean isMobileExists(String text) {
		try{
			String sql = "Select * from product where productid='"+text+"'";
			DatabaseConnection d = new DatabaseConnection();
			this.con = d.getConnection();
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public static void main(String[] args) {
		new SoldMobiles().setVisible(true);
	}
		
}
