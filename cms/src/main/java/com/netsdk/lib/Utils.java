package com.netsdk.lib;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.sun.jna.Platform;

public class Utils {
	public Utils() {

	}
	
	// 获取操作平台信息
	public static String getOsPrefix() {
		String arch = System.getProperty("os.arch").toLowerCase();
		final String name = System.getProperty("os.name");
		String osPrefix;
		switch(Platform.getOSType()) {
			case Platform.WINDOWS: {
				if ("i386".equals(arch))
	                arch = "x86";
	            osPrefix = "win32-" + arch;
			}
            break;
			case Platform.LINUX: {
				if ("x86".equals(arch)) {
	                arch = "i386";
	            }
	            else if ("x86_64".equals(arch)) {
	                arch = "amd64";
	            }
	            osPrefix = "linux-" + arch;
			}			       
	        break;
			default: {
	            osPrefix = name.toLowerCase();
	            if ("x86".equals(arch)) {
	                arch = "i386";
	            }
	            if ("x86_64".equals(arch)) {
	                arch = "amd64";
	            }
	            int space = osPrefix.indexOf(" ");
	            if (space != -1) {
	                osPrefix = osPrefix.substring(0, space);
	            }
	            osPrefix += "-" + arch;
			}
	        break;
	       
		}

		return osPrefix;
	}	
	
    public static String getOsName() {
    	String osName = "";
		String osPrefix = getOsPrefix();
		if(osPrefix.toLowerCase().startsWith("win32-x86")
				||osPrefix.toLowerCase().startsWith("win32-amd64") ) {
			osName = "win";
		} else if(osPrefix.toLowerCase().startsWith("linux-i386")
				|| osPrefix.toLowerCase().startsWith("linux-amd64")) {
			osName = "linux";
		}
		
		return osName;
    }
    
    // 获取加载库
	public static String getLoadLibrary(String library) {
		if (isChecking()) {
			return null;
		}
		
		String loadLibrary = "";
		String osPrefix = getOsPrefix();
		if(osPrefix.toLowerCase().startsWith("win32-x86")) {
			loadLibrary = "./libs/win32/";
		} else if(osPrefix.toLowerCase().startsWith("win32-amd64") ) {
			//loadLibrary = "./libs/win64/";
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("properties/netsdkconfig.properties");
			Properties properties = new Properties();
			try {
				properties.load(in);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			loadLibrary = properties.getProperty("path").trim();
			System.out.println("===============:" + loadLibrary);
		} else if(osPrefix.toLowerCase().startsWith("linux-i386")) {
			loadLibrary = "../webapps/linux32/";
//			System.getProperty("user.dir").replace("/bin", newChar)
		}else if(osPrefix.toLowerCase().startsWith("linux-amd64")) {
			loadLibrary = "../webapps/linux64/";
		}
		
		//System.out.println(System.getProperty("user.dir"));
		
		System.out.printf("[Load %s Path : %s]\n", library, loadLibrary + library);
		return loadLibrary + library;
	}
	
	private static boolean checking = false;
	public static void setChecking() {
		checking = true;
	}
	public static void clearChecking() {
		checking = false;
	}
	public static boolean isChecking() {
		return checking;
	}
}
