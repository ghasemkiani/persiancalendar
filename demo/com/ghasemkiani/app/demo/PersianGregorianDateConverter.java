/*
 * In the name of God
 * PersianGregorianDateConverter.java
 * © Ghasem Kiani
 * 25/09/2003 01:37:55 AM
 * ghasemkiani@yahoo.com
 */

package com.ghasemkiani.app.demo;

import javax.swing.JFrame;
import com.ghasemkiani.gui.misc.JPanelPersianGregorianDateConverter;

public class PersianGregorianDateConverter extends JFrame
{
	public PersianGregorianDateConverter()
	{
		super("Persian-Gregorian Date Converter");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().add(new JPanelPersianGregorianDateConverter());
		pack();
		setVisible(true);
	}
	public static void main(String[] args)
	{
		new PersianGregorianDateConverter();
	}
}
