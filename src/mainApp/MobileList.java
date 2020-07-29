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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.crypto.Data;

import dbutilities.DatabaseConnection;

public class MobileList extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	JFrame frame = new JFrame();
	private JPanel c = new JPanel();
	private JLabel mobileName, mobileModel, mobilePrice, mobileImage, Tittle, selectedImage, SelectQty, mobileRam, mobileRom, mobileBattery, mobileProcessor, mobileColor;
	private JTextField tmobilename, tmobileModel, tmobilePrice, tmobileram, tmobilerom, tmobilebattery, tmobileprocessor, tmobilecolor, idField;
	private JFileChooser FC = new JFileChooser();
	private JButton save, Cancel, SelectFile, delete, update, load;
	private JComboBox<String> mobileQty;
	private Font f = new Font("Ubuntu Mono Regular", Font.TRUETYPE_FONT, 18);
	private Font f1 = new Font("Ubuntu Mono Regular", Font.TRUETYPE_FONT, 12);
	private Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
	private File file=null;
	private Connection con;
	private PreparedStatement pst;
	String filename=null;
	ResultSet rs;
	public MobileList(){
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Mobile Cart Form");
		setBounds(200, 100, 1000, 1000);
		
		
		c.setLayout(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();
		c1.insets = new Insets(10, 0, 0, 0);
		
		
		c1.gridx = 0;c1.gridy=0;
		Tittle = new JLabel("Enter The Id");
		Tittle.setFont(f);
		c.add(Tittle, c1);
		
		c1.gridx = 1;c1.gridy=0;
		idField = new JTextField();
		idField.setPreferredSize(new Dimension(150, 30));
		idField.setFont(f);
		c.add(idField, c1);
			
		c1.gridx = 0;c1.gridy=1;
		mobileName = new JLabel("Enter The Mobile Name");
		mobileName.setFont(f1);
		mobileName.setBackground(Color.DARK_GRAY);
		c.add(mobileName, c1);
		
		c1.gridx=1;c1.gridy=1;
		tmobilename = new JTextField();
		tmobilename.setFont(f1);
		tmobilename.setPreferredSize(new Dimension(150, 30));
		c.add(tmobilename, c1);
		
		c1.gridx = 0;c1.gridy=2;
		mobileModel = new JLabel("Enter The Mobile Model:");
		mobileModel.setFont(f1);
		mobileModel.setBackground(Color.DARK_GRAY);
		c.add(mobileModel, c1);
		
		c1.gridx=1;c1.gridy=2;
		tmobileModel = new JTextField();
		tmobileModel.setFont(f1);
		tmobileModel.setPreferredSize(new Dimension(150, 30));
		c.add(tmobileModel, c1);
		
		c1.gridx = 0;c1.gridy=3;
		mobilePrice = new JLabel("Enter The Mobile Price :");
		mobilePrice.setBackground(Color.DARK_GRAY);
		mobilePrice.setFont(f1);
		c.add(mobilePrice, c1);
		
		c1.gridx=1;c1.gridy=3;
		tmobilePrice = new JTextField();
		tmobilePrice.setFont(f1);
		tmobilePrice.setPreferredSize(new Dimension(150, 30));
		c.add(tmobilePrice, c1);
		
		c1.gridx=0;c1.gridy=4;
		SelectQty = new JLabel("Select The Quantity");
		SelectQty.setFont(f1);
		c.add(SelectQty, c1);
		
		c1.gridx=1;c1.gridy=4;
		String[] i ={"1","2","3","4","5"}; 
		mobileQty = new JComboBox<>(i);
		mobileQty.setFont(f1);
		mobileQty.setPreferredSize(new Dimension(150, 30));
		c.add(mobileQty, c1);
		
		c1.gridx =0;c1.gridy=5;
		mobileImage = new JLabel("Select The Mobile Image:");
		mobileImage.setFont(f1);
		c.add(mobileImage, c1);
		
		c1.gridx=1;c1.gridy=5;
		SelectFile = new JButton("Browse");
		SelectFile.setFont(f1);
		SelectFile.setPreferredSize(new Dimension(150, 30));
		c.add(SelectFile, c1);
		SelectFile.addActionListener(this);
		
		c1.gridx=0;c1.gridy=6;
		mobileRam = new JLabel("Mobile RAM");
		mobileRam.setFont(f1);
		mobileRam.setPreferredSize(new Dimension(150, 30));
		c.add(mobileRam, c1);
		
		
		c1.gridx=1;c1.gridy=6;
		tmobileram = new JTextField();
		tmobileram.setFont(f1);
		tmobileram.setPreferredSize(new Dimension(150, 30));
		c.add(tmobileram, c1);
		
		c1.gridx=0;c1.gridy=7;
		mobileRom = new JLabel("Mobile ROM");
		mobileRom.setFont(f1);
		mobileRom.setPreferredSize(new Dimension(150, 30));
		c.add(mobileRom, c1);
		
		c1.gridx=1;c1.gridy=7;
		tmobilerom = new JTextField();
		tmobilerom.setFont(f1);
		tmobilerom.setPreferredSize(new Dimension(150, 30));
		c.add(tmobilerom, c1);
		
		
		c1.gridx=0;c1.gridy=8;
		mobileBattery = new JLabel("Batter Details");
		mobileBattery.setFont(f1);
		mobileBattery.setPreferredSize(new Dimension(150, 30));
		c.add(mobileBattery, c1);
		
		
		c1.gridx=1;c1.gridy=8;
		tmobilebattery = new JTextField();
		tmobilebattery.setFont(f1);
		tmobilebattery.setPreferredSize(new Dimension(150, 30));
		c.add(tmobilebattery, c1);
		
		
		c1.gridx=0;c1.gridy=9;
		mobileProcessor = new JLabel("Mobile Processor");
		mobileProcessor.setFont(f1);
		mobileProcessor.setPreferredSize(new Dimension(150, 30));
		c.add(mobileProcessor, c1);
		
		c1.gridx=1;c1.gridy=9;
		tmobileprocessor = new JTextField();
		tmobileprocessor.setFont(f1);
		tmobileprocessor.setPreferredSize(new Dimension(150, 30));
		c.add(tmobileprocessor, c1);
		
		c1.gridx=0;c1.gridy=10;
		mobileColor = new JLabel("Mobile Color");
		mobileColor.setFont(f1);
		mobileColor.setPreferredSize(new Dimension(150, 30));
		c.add(mobileColor, c1);
		
		c1.gridx=1;c1.gridy=10;
		tmobilecolor = new JTextField();
		tmobilecolor.setFont(f1);
		tmobilecolor.setPreferredSize(new Dimension(150, 30));
		c.add(tmobilecolor, c1);
		
		c1.gridx=4;c1.gridy=10;
		selectedImage = new JLabel();
		selectedImage.setPreferredSize(new Dimension(100, 100));
		c.add(selectedImage, c1);
		
		c1.gridx=0;c1.gridy=11;
		save = new JButton("Save");
		save.setCursor(cursor);
		save.setFont(f1);
		save.addActionListener(this);
		c.add(save, c1);
		
		c1.gridx=1;c1.gridy=11;
		delete = new JButton("Delete");
		delete.setCursor(cursor);
		delete.setFont(f1);
		delete.addActionListener(this);
		c.add(delete, c1);
		
		c1.gridx=2;c1.gridy=11;
		update = new JButton("update");
		update.setCursor(cursor);
		update.setFont(f1);
		update.addActionListener(this);
		c.add(update, c1);
		
		c1.gridx=0;c1.gridy=12;
		load = new JButton("Load");
		load.setFont(f1);
		load.setCursor(cursor);
		load.addActionListener(this);
		c.add(load, c1);
		c.setBackground(Color.LIGHT_GRAY);
        add(c);
        
		c1.gridx=1;c1.gridy=12;
		Cancel = new JButton("Cancel");
		Cancel.setFont(f1);
		Cancel.setCursor(cursor);
		Cancel.addActionListener(this);
		c.add(Cancel, c1);
		c.setBackground(Color.LIGHT_GRAY);
        add(c);
        
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==SelectFile){
			FC.showOpenDialog(null);
			file = FC.getSelectedFile();
			filename = file.getAbsolutePath();
			selectedImage.setIcon(new ImageIcon(file.toString()));
		}else if(ae.getSource()==save){
			try{
				File img = new File(filename);
				FileInputStream fis = new FileInputStream(img);
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				byte[] buf = new byte[2024];
				for(int read; (read=fis.read(buf))!=-1;){
					bos.write(buf, 0, read);
				}
				byte[] photo = bos.toByteArray();
				String sql = "INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?,?,?)";
				DatabaseConnection d = new DatabaseConnection();
				this.con = d.getConnection();
				this.pst = con.prepareStatement(sql);
				pst.setInt(1, Integer.parseInt(idField.getText()));
				pst.setString(2,  tmobilename.getText());
				pst.setString(3, tmobilePrice.getText());
				pst.setString(4, tmobileModel.getText());
				pst.setString(5, mobileQty.getSelectedItem().toString());
				pst.setBytes(6, photo);
				pst.setString(7, tmobileram.getText().toString());
				pst.setString(8, tmobilerom.getText().toString());
				pst.setString(9, tmobilebattery.getText().toString());
				pst.setString(10, tmobileprocessor.getText().toString());
				pst.setString(11, tmobilecolor.getText().toString());
				
				int a = pst.executeUpdate();
				if(a>0){
					JOptionPane.showMessageDialog(null, "Values Insert Successfully");
					this.setVisible(false);
					new MobileData().setVisible(true);
				}else{
					this.setVisible(true);
					JOptionPane.showMessageDialog(null, "Values Not Inserted....Something Went Wrong");
				}
			}catch(Exception e){
				e.printStackTrace();
			}		
		}else if(ae.getSource()==delete){
			String id = idField.getText();
			try{
				
				String sql = "DELETE FROM product WHERE productid='"+id+"'";
				DatabaseConnection d = new DatabaseConnection();
				this.con = d.getConnection();
				this.pst = con.prepareStatement(sql);
				int a = pst.executeUpdate();
				if(a>0){
					JOptionPane.showMessageDialog(null, "Product delete successfully");
					
				}else{
					JOptionPane.showMessageDialog(null, "Product deletion failed....Something Went Wrong");
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					con.close();
					pst.close();
				}catch(Exception e){
					e.printStackTrace();
				}
		}
	}else if(ae.getSource()==load){
		try{
			String id = idField.getText();
			String sql = "SELECT * FROM product where productid='"+id+"'";
			DatabaseConnection d = new DatabaseConnection();
			this.con = d.getConnection();
			this.pst = con.prepareStatement(sql);
			 rs = pst.executeQuery();
			while(rs.next()){
				tmobilename.setText(rs.getString("productName"));
				tmobilePrice.setText(rs.getString("productPrice"));
				tmobileModel.setText(rs.getString("productModelNumber"));
				mobileQty.setSelectedItem(rs.getString("productQty"));
				byte []b = rs.getBytes("productImage");
				ImageIcon i = new ImageIcon(b);
				selectedImage.setIcon(i);
				tmobileram.setText(rs.getString("productRam"));
				tmobilerom.setText(rs.getString("productRom"));
				tmobilebattery.setText(rs.getString("productProcessor"));
				tmobileprocessor.setText(rs.getString("batteryDetails"));
				tmobilecolor.setText(rs.getString("productColor"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
				pst.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}else if(ae.getSource()==update){
		try{
		String id = idField.getText();
		
		
		String sql = "UPDATE product SET productid=?, productName=?, productPrice=?, productModelNumber=?, productQty=?, productImage=?, productRam=?, productRom=?, productProcessor=?, batteryDetails=?, productColor=? where productid='"+id+"'";
		DatabaseConnection d = new DatabaseConnection();
		this.con = d.getConnection();
		this.pst = con.prepareStatement(sql);
		pst.setInt(1, Integer.parseInt(idField.getText()));
		pst.setString(2,  tmobilename.getText());
		pst.setString(3, tmobilePrice.getText());
		pst.setString(4, tmobileModel.getText());
		pst.setString(5, mobileQty.getSelectedItem().toString());
		if(file==null && filename==null){
			String sql1 = "Select productImage from product";
			DatabaseConnection d1 = new DatabaseConnection();
			Connection c = d1.getConnection();
			PreparedStatement p = c.prepareStatement(sql1);
			ResultSet rs1 = p.executeQuery();
			while(rs1.next()){
				byte []b = rs1.getBytes("productImage");
				pst.setBytes(6, b);
			}
		}else{
				File img = new File(filename);
				FileInputStream fis = new FileInputStream(img);
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				byte[] buf = new byte[2024];
				for(int read; (read=fis.read(buf))!=-1;){
					bos.write(buf, 0, read);
				}
				byte[] photo = bos.toByteArray();
				pst.setBytes(6, photo);
		}
		
		pst.setString(7, tmobileram.getText().toString());
		pst.setString(8, tmobilerom.getText().toString());
		pst.setString(9, tmobilebattery.getText().toString());
		pst.setString(10, tmobileprocessor.getText().toString());
		pst.setString(11, tmobilecolor.getText().toString());
		
		int a = pst.executeUpdate();
		
		if(a>0){
			JOptionPane.showMessageDialog(null, "Values Insert Successfully");
			this.setVisible(false);
			new MobileData().setVisible(true);
		}else{
			this.setVisible(true);
			JOptionPane.showMessageDialog(null, "Values Not Inserted....Something Went Wrong");
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try{
			con.close();
			pst.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	}
		
}

	public static void main(String[] args) {
		MobileList m = new MobileList();
		m.setVisible(true);

	}

}
