import java.math.BigInteger;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

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
	
	private static String bytesToString(byte[] encrypted) {		 
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
	
	public static void main(String[] args) throws IOException{
		RSAencrypt f = new RSAencrypt();
		RSA2 rsa = new RSA2();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		f.setVisible(true);
		
		
		String x = f.btnEnc.getText();
		byte[] encrypted = rsa.encrypt(x.getBytes());
		byte[] decrypted = rsa.decrypt(encrypted);
		
		String test;
		
		System.out.println("Enter plain text:");
		test = in.readLine();
		System.out.println("Encrypting: " + test);
		System.out.println("String in Bytes: " + bytesToString(test.getBytes()));
		//byte[] encrypted = rsa.encrypt(test.getBytes());
		System.out.println("Encrypted String in Bytes: " + bytesToString(encrypted));	
	//	byte[] decrypted = rsa.decrypt(encrypted);
		System.out.println("Decrypted String in Bytes: " + bytesToString(decrypted));
		System.out.println("Decrypted String: " + new String(decrypted));
	}
	public class btnEncButtonListener implements ActionListener{
		  public void actionPerformed(ActionEvent e) {
			  RSAencrypt f = new RSAencrypt();
			  RSA2 rsa = new RSA2();
				String x = f.txt_Encrypt.getText();
				byte[] encrypted = rsa.encrypt(x.getBytes());
				byte[] decrypted = rsa.decrypt(encrypted);
				
				//f.btnEnc.addActionListener(new btnEncButtonListener());
				
				f.txt_info.setText("Text entered: \n" + x + "\nString in Bytes: \n" + bytesToString(x.getBytes())
				+ "\nEncrypted String in Bytes: \n" + bytesToString(encrypted) + "\nDecrypted String in Bytes: \n" + bytesToString(decrypted)
				+ "\nDecrypted String: \n" + new String(decrypted));
			  
				//String x = txt_Encrypt.getText();
			  
			 // txt_info.setText(x);
		  }  
}
}


