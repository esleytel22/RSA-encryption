

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JButton;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JTextPane;
import java.awt.SystemColor;

public class RSAencrypt extends JFrame {
	private JPanel contentPane;
	private JPanel textPanel;
	public JTextField txt_Encrypt;
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
	
	
	public RSAencrypt() {
		setTitle("RSA");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 439);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(8, 8, 8, 8));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnEnc = new JButton("Encrypt");
		btnEnc.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnEnc.setBounds(279, 82, 120, 35);
		contentPane.add(btnEnc);
		
		txt_Encrypt = new JTextField();
		txt_Encrypt.setBounds(10, 36, 664, 35);
		contentPane.add(txt_Encrypt);
		txt_Encrypt.setColumns(10);
		
		txt_info = new JTextPane();
		txt_info.setEditable(false);
		txt_info.setBounds(10, 181, 664, 208);
		contentPane.add(txt_info);
		btnEnc.addActionListener(new btnEncButtonListener());
	
	    textPanel = new JPanel();
	    
		textPanel.setBounds(127, 110, 680, 628);
		setVisible(true);
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
						
					txt_info.setText("Text entered: \n" + x +"\nEncrypted String : \n"+ encrypted + "\nString in Bytes: \n" + bytesToString(x.getBytes())
					+ "\nEncrypted String in Bytes: \n" + bytesToString(encrypted) + "\nDecrypted String in Bytes : \n" + bytesToString(decrypted)
					+ "\nDecrypted from Bytes: \n "+ decrypted + "\nDecrypted String: \n" + new String(decrypted));
				
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
			public String bytesToString(byte[] encrypted) {		 
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
			RSAencrypt f = new RSAencrypt();
			f.setVisible(true);
		}
}



