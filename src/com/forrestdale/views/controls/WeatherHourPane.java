package com.forrestdale.views.controls;

import javax.swing.*;
import java.awt.*;

public class WeatherHourPane extends JPanel {

	/**
     * Create the panel.
	 */
	public WeatherHourPane(String time, String wind, String visibility, String temp, ImageIcon icon) {
		
		JLabel lblTime = new JLabel(time);
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblPicture = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(100, 130, Image.SCALE_DEFAULT)));
		lblPicture.setSize(100, 150);
		
		JLabel lblTemp = new JLabel(temp);
		lblTemp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblWind = new JLabel(wind);
		lblWind.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblVisibility = new JLabel(visibility);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(59)
					.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(lblPicture)
						.addComponent(lblTime)
						.addComponent(lblTemp)
						.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
							.addComponent(lblVisibility)
							.addComponent(lblWind)))
					.addContainerGap(45, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTime)
					.addGap(44)
					.addComponent(lblPicture)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
					.addComponent(lblVisibility)
					.addGap(18)
					.addComponent(lblWind)
					.addGap(18)
					.addComponent(lblTemp)
					.addContainerGap())
		);
		setLayout(groupLayout);

	}

}