/*
 * In the name of God
 * JPanelPersianGregorialDateConverter.java
 * © Ghasem Kiani
 * 24/09/2003 09:35:54 PM
 * ghasemkiani@yahoo.com
 */

package com.ghasemkiani.gui.misc;

import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ghasemkiani.util.SimplePersianCalendar;
import com.ghasemkiani.util.DateFields;

public class JPanelPersianGregorialDateConverter extends JPanel
{
	private JPanel jPanelGregorian = new JPanel(new GridBagLayout());
	private JLabel jLabelGregorianYear = new JLabel("Year");
	private JLabel jLabelGregorianMonth = new JLabel("Month");
	private JLabel jLabelGregorianDay = new JLabel("Day");
	private JTextField jTextFieldGregorianYear = new JTextField();
	private JTextField jTextFieldGregorianMonth = new JTextField();
	private JTextField jTextFieldGregorianDay = new JTextField();
	
	private JPanel jPanelPersian = new JPanel(new GridBagLayout());
	private JLabel jLabelPersianYear = new JLabel("Year");
	private JLabel jLabelPersianMonth = new JLabel("Month");
	private JLabel jLabelPersianDay = new JLabel("Day");
	private JTextField jTextFieldPersianYear = new JTextField();
	private JTextField jTextFieldPersianMonth = new JTextField();
	private JTextField jTextFieldPersianDay = new JTextField();
	
	private JLabel jLabelCopyRight = new JLabel("<html>&copy; 2003, Ghasem Kiani &lt;<font color=blue>ghasemkiani@yahoo.com</font>&gt;", JLabel.CENTER);
	
	private JPanel jPanelButtons = new JPanel(new FlowLayout());
	
	public JPanelPersianGregorialDateConverter()
	{
		super();
		setLayout(new GridBagLayout());
		
		add(jPanelGregorian, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jPanelGregorian.setBorder(BorderFactory.createTitledBorder("Gregorian"));
		jPanelGregorian.add(jLabelGregorianYear, new GridBagConstraints(0, 0, 1, 1, 0.333, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jPanelGregorian.add(jLabelGregorianMonth, new GridBagConstraints(1, 0, 1, 1, 0.333, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jPanelGregorian.add(jLabelGregorianDay, new GridBagConstraints(2, 0, 1, 1, 0.333, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jPanelGregorian.add(jTextFieldGregorianYear, new GridBagConstraints(0, 1, 1, 1, 0.333, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jPanelGregorian.add(jTextFieldGregorianMonth, new GridBagConstraints(1, 1, 1, 1, 0.333, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jPanelGregorian.add(jTextFieldGregorianDay, new GridBagConstraints(2, 1, 1, 1, 0.333, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		
		add(jPanelPersian, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jPanelPersian.setBorder(BorderFactory.createTitledBorder("Persian"));
		jPanelPersian.add(jLabelPersianYear, new GridBagConstraints(0, 0, 1, 1, 0.333, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jPanelPersian.add(jLabelPersianMonth, new GridBagConstraints(1, 0, 1, 1, 0.333, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jPanelPersian.add(jLabelPersianDay, new GridBagConstraints(2, 0, 1, 1, 0.333, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jPanelPersian.add(jTextFieldPersianYear, new GridBagConstraints(0, 1, 1, 1, 0.333, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jPanelPersian.add(jTextFieldPersianMonth, new GridBagConstraints(1, 1, 1, 1, 0.333, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jPanelPersian.add(jTextFieldPersianDay, new GridBagConstraints(2, 1, 1, 1, 0.333, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		
		add(jLabelCopyRight, new GridBagConstraints(0, 6, 1, 1, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		
		add(jPanelButtons, new GridBagConstraints(0, 7, 1, 1, 1, 1, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));
		
		jPanelButtons.add(new JButton(new AbstractAction("Gregorian To Persian"){
			public void actionPerformed(ActionEvent ae)
			{
				convertGregorianToPersian();
			}
		}));
		jPanelButtons.add(new JButton(new AbstractAction("Persian To Gregorian"){
			public void actionPerformed(ActionEvent ae)
			{
				convertPersianToGregorian();
			}
		}));
	}
	private void convertGregorianToPersian()
	{
		int year, month, day;
		DateFields t;
		try
		{
			try
			{
				year = Integer.parseInt(jTextFieldGregorianYear.getText());
			}
			catch(NumberFormatException nfe)
			{
				year = 0;
			}
			try
			{
				month = Integer.parseInt(jTextFieldGregorianMonth.getText()) - 1;
			}
			catch(NumberFormatException nfe)
			{
				month = 0;
			}
			try
			{
				day = Integer.parseInt(jTextFieldGregorianDay.getText());
			}
			catch(NumberFormatException nfe)
			{
				day = 0;
			}
			
			SimplePersianCalendar c = new SimplePersianCalendar();
			c.set(c.YEAR, year);
			c.set(c.MONTH, month);
			c.set(c.DAY_OF_MONTH, day);
			t = c.getDateFields();
			jTextFieldPersianYear.setText(Long.toString(t.getYear()));
			jTextFieldPersianMonth.setText(Long.toString(t.getMonth() + 1));
			jTextFieldPersianDay.setText(Long.toString(t.getDay()));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	private void convertPersianToGregorian()
	{
		int year, month, day;
		DateFields t;
		try
		{
			try
			{
				year = Integer.parseInt(jTextFieldPersianYear.getText());
			}
			catch(NumberFormatException nfe)
			{
				year = 0;
			}
			try
			{
				month = Integer.parseInt(jTextFieldPersianMonth.getText()) - 1;
			}
			catch(NumberFormatException nfe)
			{
				month = 0;
			}
			try
			{
				day = Integer.parseInt(jTextFieldPersianDay.getText());
			}
			catch(NumberFormatException nfe)
			{
				day = 0;
			}
			
			SimplePersianCalendar c = new SimplePersianCalendar();
			c.setDateFields(year, month, day);
			jTextFieldGregorianYear.setText(Long.toString(c.get(c.YEAR)));
			jTextFieldGregorianMonth.setText(Long.toString(c.get(c.MONTH) + 1));
			jTextFieldGregorianDay.setText(Long.toString(c.get(c.DAY_OF_MONTH)));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
