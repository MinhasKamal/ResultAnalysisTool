/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
***********************************************************/

package com.resultAnalysisTool;

import javax.swing.UIManager;

import com.resultAnalysisTool.mainFrame.MainFrame;

public class Run {
	public static void main(String[] args) {
		/*// Set the NIMBUS look and feel //*/
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create */
		new MainFrame();
	}
}
