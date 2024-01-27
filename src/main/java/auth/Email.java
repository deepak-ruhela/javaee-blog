package auth;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

    private static String USER_NAME = "hubspedia"; // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "bothmncfipwistft"; // GMail password
    private static String RECIPIENT = "deepakr@tiinfotech.com";

    public static String sendOTP(String name, String email) {

//	String name = "Deep";
	String otp = getOTP();
	String from = USER_NAME;
	String pass = PASSWORD;
	// String[] to = { RECIPIENT }; // list of recipient email addresses
	String[] to = { email };
	String subject = "Java send mail example";
	String body = "Hello " + name + ",\nYour HubsPedia verification code is: " + otp;

	//sendFromGMail(from, pass, to, subject, body);
	return otp;
    }

    private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
	Properties props = System.getProperties();
	String host = "smtp.gmail.com";
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", host);
	props.put("mail.smtp.user", from);
	props.put("mail.smtp.password", pass);
	props.put("mail.smtp.port", "587");
	props.put("mail.smtp.auth", "false");

	Session session = Session.getDefaultInstance(props);
	MimeMessage message = new MimeMessage(session);

	try {
	    message.setFrom(new InternetAddress(from));
	    InternetAddress[] toAddress = new InternetAddress[to.length];

	    // To get the array of addresses
	    for (int i = 0; i < to.length; i++) {
		toAddress[i] = new InternetAddress(to[i]);
	    }

	    for (int i = 0; i < toAddress.length; i++) {
		message.addRecipient(Message.RecipientType.TO, toAddress[i]);
	    }

	    message.setSubject(subject);
	    message.setText(body);
	    Transport transport = session.getTransport("smtp");
	    transport.connect(host, from, pass);
	    transport.sendMessage(message, message.getAllRecipients());
	    transport.close();
	    System.out.println("Email Sent");
	}

	catch (MessagingException e) {
	    e.printStackTrace();
	}

    }

    public static String getOTP() {

	// System.out.print("You OTP is : ");

	// Using numeric values
	String numbers = "0123456789";

	// Using random method
	Random rndm_method = new Random();

	char[] otp = new char[6];

	for (int i = 0; i < 6; i++) {
	    // Use of charAt() method : to get character value
	    // Use of nextInt() as it is scanning the value as int
	    otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
	}

	String str = new String(otp);

	return str;
    }
}
