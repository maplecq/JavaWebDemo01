package com.chiansofit.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtils {
	private static ComboPooledDataSource source;

	static {
		source = new ComboPooledDataSource();
	}

	public static ComboPooledDataSource getDataSource() {
		return source;
	}
}
