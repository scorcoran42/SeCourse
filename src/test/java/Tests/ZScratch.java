package Tests;

import java.util.Properties;

/*
 * scratch pad
 * 
 */
public class ZScratch {

	public static void main(String[] args) {

		System.setProperty("selenium2Basics.driver", "ff");
		System.out.println(System.getProperty("selenium2Basics.driver", "no value"));
		//System.clearProperty("selenium2Basics.driver");
		System.out.println(System.getProperty("selenium2Basics.driver", "no value"));
//		myProperties.list(System.out);
	}

}
