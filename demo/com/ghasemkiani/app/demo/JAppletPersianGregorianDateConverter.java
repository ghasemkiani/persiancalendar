/*
 * In the name of God
 * JAppletPersianGregorianDateConverter.java
 * © Ghasem Kiani
 * 25/09/2003 01:40:11 PM
 * ghasemkiani@yahoo.com
 */

/*
	<applet width="200" height="120" archive="PersianCalendar.jar" code="com.ghasemkiani.app.demo.JAppletPersianGregorianDateConverter">You don't have Java support in your browser.</applet>
*/

package com.ghasemkiani.app.demo;

import javax.swing.JApplet;
import com.ghasemkiani.gui.misc.JPanelPersianGregorianDateConverter;

public class JAppletPersianGregorianDateConverter extends JApplet
{
	public void init()
	{
		getContentPane().add(new JPanelPersianGregorianDateConverter());
	}
}

