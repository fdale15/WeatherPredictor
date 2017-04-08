package com.forrestdale.views.controls;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;

public class WeatherDayDetailPane extends JPanel {

	private JButton viewDayButton;

	/**
     * Create the panel.
	 */
	public WeatherDayDetailPane(String date, String temp, String lowTemp, String wind, String relativeHumidity, String barometer, String visibility, ImageIcon icon) {
		setLayout(null);
		setSize(250, 530);
		setPreferredSize(new Dimension(350, 530));

		JLabel dateLbl = new JLabel(date);
		dateLbl.setFont(new Font("Tahoma", Font.BOLD, 24));
		dateLbl.setHorizontalAlignment(SwingConstants.CENTER);
		dateLbl.setBounds(62, 11, 230, 38);
		add(dateLbl);
		
		JLabel pictureLbl = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(230, 150, Image.SCALE_DEFAULT)));
		pictureLbl.setHorizontalAlignment(SwingConstants.CENTER);
		pictureLbl.setBounds(62, 60, 230, 150);
		add(pictureLbl);
		
		JLabel tempLbl = new JLabel(temp);
		tempLbl.setFont(new Font("Tahoma", Font.BOLD, 36));
		tempLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tempLbl.setBounds(10, 285, 151, 53);
		add(tempLbl);
		
		JLabel windLbl = new JLabel(wind);
		windLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		windLbl.setHorizontalAlignment(SwingConstants.CENTER);
		windLbl.setBounds(10, 209, 151, 65);
		add(windLbl);
		
		JLabel lowTempLbl = new JLabel(lowTemp);
		lowTempLbl.setHorizontalAlignment(SwingConstants.CENTER);
		lowTempLbl.setFont(new Font("Tahoma", Font.BOLD, 30));
		lowTempLbl.setBounds(10, 349, 151, 53);
		add(lowTempLbl);
		
		JLabel rhLabel = new JLabel(relativeHumidity);
		rhLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rhLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		rhLabel.setBounds(189, 209, 151, 65);
		add(rhLabel);
		
		JLabel barometerLabel = new JLabel(barometer);
		barometerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		barometerLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		barometerLabel.setBounds(189, 285, 151, 65);
		add(barometerLabel);
		
		JLabel visibilityLabel = new JLabel(visibility);
		visibilityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		visibilityLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		visibilityLabel.setBounds(189, 354, 151, 65);
		add(visibilityLabel);

		viewDayButton = new JButton("View Day");
		viewDayButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		viewDayButton.setBounds(62, 430, 230, 58);
		add(viewDayButton);
	}

	public void setViewEvent(MouseAdapter adapter) {
		viewDayButton.addMouseListener(adapter);
	}
}