package it.bismark;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Tray extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final String DATE_REST = "08/08/2014";
	private static Date today;
	private SystemTray systemTray = SystemTray.getSystemTray();
	private TrayIcon trayIcon;

	private JLabel label;

	public static void main(String[] args) throws IOException {
		Date date = null;
		today = new Date();
		try {
			date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
					.parse(DATE_REST);
		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(0);
		}

		Calendar calToday = Calendar.getInstance();
		calToday.setTime(today);

		Calendar calHolidays = Calendar.getInstance();
		calHolidays.setTime(date);

		Tray trayTest = new Tray();
		trayTest.pack();
		trayTest.setLocationRelativeTo(null);
		trayTest.setVisible(true);
		trayTest.setSize(250, 100);
		boolean showIconLunch = true;
		boolean showIconGoHome=true;

		while (calHolidays.getTimeInMillis() - calToday.getTimeInMillis() > 0) {
			String val = String
					.valueOf((calHolidays.getTimeInMillis() - calToday
							.getTimeInMillis()) / 60000);
			trayTest.label.setText("Remain for holidays: " + val);
			calToday.setTime(new Date());

			if (calToday.get(Calendar.HOUR_OF_DAY) == 13
					&& (calToday.get(Calendar.MINUTE) > 0 && calToday
							.get(Calendar.MINUTE) < 5) && showIconLunch) {
				trayTest.removeTrayIcon();
				trayTest.addTrayIconGoHome();
				showIconLunch = false;
			}
			
			if (calToday.get(Calendar.HOUR_OF_DAY) == 18
					&& (calToday.get(Calendar.MINUTE) > 30 && calToday
							.get(Calendar.MINUTE) < 40) && showIconGoHome) {
				trayTest.removeTrayIcon();
				trayTest.addTrayIconLunch();
				showIconGoHome = false;
			}
		}
		
		trayTest.label.setText("Holidays is happend!!!! Good luck");

	}

	public Tray() throws IOException {
		super("Holidays is coming");
		setResizable(false);
		label = new JLabel("Remain for holidays:");
		label.setBounds(0, 0, 200, 30);
		getContentPane().add(label);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		trayIcon = new TrayIcon(ImageIO.read(new File(this.getClass()
				.getClassLoader().getResource("edu_languages.png").getFile())),
				"Holidays is coming");
		trayIcon.setImageAutoSize(true);
		trayIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(true);
				setState(JFrame.NORMAL);
				removeTrayIcon();
			}
		});
		addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent e) {
				if (e.getNewState() == JFrame.ICONIFIED) {
					setVisible(false);
					addTrayIcon();
				}
			}
		});
	}

	private void removeTrayIcon() {
		systemTray.remove(trayIcon);
	}

	private void addTrayIcon() {
		try {
			systemTray.add(trayIcon);
			trayIcon.displayMessage("Holidays is coming",
					"Window minimised to tray, double click to show",
					TrayIcon.MessageType.INFO);
		} catch (AWTException ex) {
			ex.printStackTrace();
		}
	}

	private void addTrayIconLunch() {
		try {
			systemTray.add(trayIcon);
			trayIcon.displayMessage("Holidays is coming",
					"You need eat. Got to the lunch!!!!",
					TrayIcon.MessageType.INFO);
		} catch (AWTException ex) {
			ex.printStackTrace();
		}
	}

	private void addTrayIconGoHome() {
		try {
			systemTray.add(trayIcon);
			trayIcon.displayMessage("Holidays is coming",
					"Go home. 18.30 Turn off PC!!!!!!!",
					TrayIcon.MessageType.INFO);
		} catch (AWTException ex) {
			ex.printStackTrace();
		}
	}

}
