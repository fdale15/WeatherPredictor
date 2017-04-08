package com.forrestdale.views.controls;

import com.forrestdale.utils.WeatherSkyConditionConverter;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;

public class WeatherTriDailyPane extends JPanel {

	/**
     * Create the panel.
	 */
	public WeatherTriDailyPane(String time, String barometer, String relativeHumidity, String visibility, String wind, String temp, ImageIcon icon) {
		JLabel lblPicture = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(125, 125, Image.SCALE_DEFAULT)));
		lblPicture.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblTemp = new JLabel(temp);
		lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemp.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblWind = new JLabel(wind);
		lblWind.setHorizontalAlignment(SwingConstants.CENTER);
		lblWind.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblVisibility = new JLabel(visibility);
		lblVisibility.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisibility.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblRh = new JLabel(relativeHumidity);
		lblRh.setHorizontalAlignment(SwingConstants.CENTER);
		lblRh.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblBarometer = new JLabel(barometer);
		lblBarometer.setHorizontalAlignment(SwingConstants.CENTER);
		lblBarometer.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblTime = new JLabel(time);
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblPicture, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblTime, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
												.addContainerGap())
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblBarometer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGap(27))
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblRh, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGap(27))
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblVisibility, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
												.addContainerGap())
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblWind, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
												.addContainerGap())
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
												.addComponent(lblTemp, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
												.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblTime, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
								.addGap(50)
								.addComponent(lblPicture)
								.addGap(40)
								.addComponent(lblBarometer, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
								.addGap(30)
								.addComponent(lblRh, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblVisibility, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblWind, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(lblTemp)
								.addGap(12))
		);
		setLayout(groupLayout);

	}

}
