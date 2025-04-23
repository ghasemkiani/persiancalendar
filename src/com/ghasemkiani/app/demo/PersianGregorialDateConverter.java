/*
 * In the name of God
 * PersianGregorialDateConverter.java
 * © Ghasem Kiani
 * 25/09/2003 01:37:55 AM
 * ghasemkiani@yahoo.com
 */

package com.ghasemkiani.app.demo;

import com.ghasemkiani.gui.misc.JPanelPersianGregorialDateConverter;

public class PersianGregorialDateConverter
{
	public static void main(java.lang.String[] args)
	{
		javax.swing.JFrame f = new javax.swing.JFrame("Persian-Gregorian Date Converter");
		f.setDefaultCloseOperation(f.DISPOSE_ON_CLOSE);
		f.getContentPane().add(new JPanelPersianGregorialDateConverter());
		f.pack();
		f.setVisible(true);
	}
}
