
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import java.awt.TextField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;

import java.awt.List;
import java.awt.CardLayout;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.Choice;

import javax.swing.JTextPane;
import javax.swing.DropMode;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class frame extends JFrame {
	private JList jList;
	private JPanel contentPane;
	private JPanel textPanel;
	private String user;
	public JTextField txt_Encrypt;
	public JTextField txt_Decrypt;
	public JButton btnDec; 
	public JButton btnEnc;
	public JTextPane txt_info;

	
	private BigInteger p;
	private BigInteger q;
	private BigInteger N;
	private BigInteger phi;
	private BigInteger e;
	private BigInteger d;
	private int bitlength = 1024;
	private int blocksize = 256;
	private Random r;
	
	
	public frame() {
		setTitle("RSA");
		//this.user=user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 439);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		//contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(8, 8, 8, 8));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		btnDec = new JButton("Decrypt");
		btnDec.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnDec.setBounds(279, 217, 120, 35);
		contentPane.add(btnDec);
		//btnDec.addActionListener(new btnDecButtonListener());
		
		btnEnc = new JButton("Encrypt");
		btnEnc.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnEnc.setBounds(279, 82, 120, 35);
		contentPane.add(btnEnc);
		
		txt_Encrypt = new JTextField();
		txt_Encrypt.setBounds(10, 36, 664, 35);
		contentPane.add(txt_Encrypt);
		txt_Encrypt.setColumns(10);
		
		txt_Decrypt = new JTextField();
		txt_Decrypt.setColumns(10);
		txt_Decrypt.setBounds(10, 171, 664, 35);
		contentPane.add(txt_Decrypt);
		
		txt_info = new JTextPane();
		txt_info.setEditable(false);
		txt_info.setBounds(10, 275, 664, 114);
		contentPane.add(txt_info);
		btnEnc.addActionListener(new btnEncButtonListener());
	
	    textPanel = new JPanel();
	    
		textPanel.setBounds(127, 110, 680, 628);
		setVisible(true);
	}
	
	public String userName() {
		return user; 
	}
	
	
	private String bytesToString(byte[] encrypted) {		 
		String test = ""; 
		for (byte b : encrypted) { 
			test += Byte.toString(b);
		} 
		return test;
	}
	
	public byte[] encrypt(byte[] message) {		 
		return (new BigInteger(message)).modPow(e, N).toByteArray(); 
	}
 
	public byte[] decrypt(byte[] message) { 
		return (new BigInteger(message)).modPow(d, N).toByteArray(); 
	}
	  
	
		public class btnEncButtonListener implements ActionListener{
			  public void actionPerformed(ActionEvent e) {
				  
				  	RSA2 rsa = new RSA2();
					String x = txt_Encrypt.getText();
					byte[] encrypted = rsa.encrypt(x.getBytes());
					byte[] decrypted = rsa.decrypt(encrypted);
						
					txt_info.setText("Text entered: \n" + x + "\nString in Bytes: \n" + bytesToString(x.getBytes())
					+ "\nEncrypted String in Bytes: \n" + bytesToString(encrypted) + "\nDecrypted String in Bytes: \n" + bytesToString(decrypted)
					+ "\nDecrypted String: \n" + new String(decrypted));
				
			  }  
	  }

	  
		public class RSA2 {
			private BigInteger p;
			private BigInteger q;
			private BigInteger N;
			private BigInteger phi;
			private BigInteger e;
			private BigInteger d;
			private int bitlength = 1024;
			private int blocksize = 256;
			private Random r;
			
			public RSA2() {
				r = new Random();
				p = BigInteger.probablePrime(bitlength, r);
				q = BigInteger.probablePrime(bitlength, r);
				N = p.multiply(q);
				phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
				e = BigInteger.probablePrime(bitlength / 2, r);
				while(phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
					e.add(BigInteger.ONE);
				}
				d = e.modInverse(phi);
			}
			
			public RSA2(BigInteger e, BigInteger d, BigInteger N) {
				this.e = e;
				this.d = d;
				this.N = N;
			}
			private String bytesToString(byte[] encrypted) {		 
				String test = ""; 
				for (byte b : encrypted) { 
					test += Byte.toString(b);
				} 
				return test;
			}
			
			public byte[] encrypt(byte[] message) {		 
				return (new BigInteger(message)).modPow(e, N).toByteArray(); 
			}
		 
			public byte[] decrypt(byte[] message) { 
				return (new BigInteger(message)).modPow(d, N).toByteArray(); 
			}
			
}
		public static void main(String[] args) throws IOException{
			RSA f = new RSA();
			f.setVisible(true);
		}
}



