package com.forrestdale.views.controls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class WeatherDayPane extends JPanel {

	private final JButton viewDayButton;

	/**
     * Create the panel.
	 */
	public WeatherDayPane(String date, String temp, String wind, ImageIcon icon) {
		setLayout(null);
		setSize(250, 530);
		setPreferredSize(new Dimension(250, 530));

		JLabel dateLbl = new JLabel(date);
		dateLbl.setFont(new Font("Tahoma", Font.BOLD, 24));
		dateLbl.setHorizontalAlignment(SwingConstants.CENTER);
		dateLbl.setBounds(10, 11, 230, 38);
		add(dateLbl);
		
		JLabel pictureLbl = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(200, 150, Image.SCALE_DEFAULT)));
		pictureLbl.setHorizontalAlignment(SwingConstants.CENTER);
		pictureLbl.setBounds(10, 76, 230, 150);
		add(pictureLbl);
		
		JLabel tempLbl = new JLabel(temp);
		tempLbl.setFont(new Font("Tahoma", Font.BOLD, 36));
		tempLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tempLbl.setBounds(10, 337, 230, 53);
		add(tempLbl);
		
		JLabel windLbl = new JLabel(wind);
		windLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		windLbl.setHorizontalAlignment(SwingConstants.CENTER);
		windLbl.setBounds(10, 242, 230, 65);
		add(windLbl);

		viewDayButton = new JButton("View Day");
		viewDayButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		viewDayButton.setBounds(20, 430, 230, 58);
		add(viewDayButton);
	}

	public void setViewAction(MouseAdapter adapter) {
		viewDayButton.addMouseListener(adapter);
	}
}
