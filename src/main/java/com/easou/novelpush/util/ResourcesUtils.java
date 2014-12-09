package com.easou.novelpush.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Properties;

/**
 * 资源文件工具类
 * 
 * @author Alex j.zhongming@gmail.com
 * 
 */
public class ResourcesUtils extends Object {
	private static final String EXCEPTION_INFO = "Could not find resource : ";

	/**
	 * 静态工厂方法，禁止New 新实例
	 */
	private ResourcesUtils() {

	}

	public static URL getResourceURL(final String resource) throws IOException {
		URL url = null;
		ClassLoader loader = ResourcesUtils.class.getClassLoader();
		if (loader != null) {
			url = loader.getResource(resource);
		}
		if (url == null) {
			url = ClassLoader.getSystemResource(resource);
		}
		if (url == null) {
			throw new IOException(EXCEPTION_INFO.concat(resource));
		}
		return url;
	}

	public static URL getResourceURL(final ClassLoader loader, final String resource) throws IOException {
		URL url = null;
		if (loader != null) {
			url = loader.getResource(resource);
		}
		if (url == null) {
			url = ClassLoader.getSystemResource(resource);
		}
		if (url == null) {
			throw new IOException(EXCEPTION_INFO.concat(resource));
		}
		return url;
	}

	public static InputStream getResourceAsStream(final String resource) throws IOException {
		InputStream in = null;
		ClassLoader loader = ResourcesUtils.class.getClassLoader();
		if (loader != null) {
			in = loader.getResourceAsStream(resource);
		}
		if (in == null) {
			in = ClassLoader.getSystemResourceAsStream(resource);
		}
		if (in == null) {
			throw new IOException(EXCEPTION_INFO.concat(resource));
		}
		return in;
	}

	public static InputStream getResourceAsStream(final ClassLoader loader, final String resource) throws IOException {
		InputStream in = null;
		if (loader != null) {
			in = loader.getResourceAsStream(resource);
		}
		if (in == null) {
			in = ClassLoader.getSystemResourceAsStream(resource);
		}
		if (in == null) {
			throw new IOException(EXCEPTION_INFO.concat(resource));
		}
		return in;
	}

	/** */
	/**
	 * Returns a resource on the classpath as a Properties object
	 * 
	 * @param resource
	 *            The resource to find
	 * @throws IOException
	 *             If the resource cannot be found or read
	 * @return The resource
	 */
	public static Properties getResourceAsProperties(String resource) throws IOException {
		Properties props = new Properties();
		String propfile = resource;
		InputStream in = getResourceAsStream(propfile);
		props.load(in);
		in.close();
		return props;
	}

	/** */
	/**
	 * Returns a resource on the classpath as a Properties object
	 * 
	 * @param loader
	 *            The classloader used to load the resource
	 * @param resource
	 *            The resource to find
	 * @throws IOException
	 *             If the resource cannot be found or read
	 * @return The resource
	 */
	public static Properties getResourceAsProperties(ClassLoader loader, String resource) throws IOException {
		Properties props = new Properties();
		String propfile = resource;
		InputStream in = getResourceAsStream(loader, propfile);
		props.load(in);
		in.close();
		return props;
	}

	/** */
	/**
	 * Returns a resource on the classpath as a Reader object
	 * 
	 * @param resource
	 *            The resource to find
	 * @throws IOException
	 *             If the resource cannot be found or read
	 * @return The resource
	 */
	public static InputStreamReader getResourceAsReader(String resource) throws IOException {
		return new InputStreamReader(getResourceAsStream(resource));
	}

	/** */
	/**
	 * Returns a resource on the classpath as a Reader object
	 * 
	 * @param loader
	 *            The classloader used to load the resource
	 * @param resource
	 *            The resource to find
	 * @throws IOException
	 *             If the resource cannot be found or read
	 * @return The resource
	 */
	public static Reader getResourceAsReader(ClassLoader loader, String resource) throws IOException {
		return new InputStreamReader(getResourceAsStream(loader, resource));
	}

	/** */
	/**
	 * Returns a resource on the classpath as a File object
	 * 
	 * @param resource
	 *            The resource to find
	 * @throws IOException
	 *             If the resource cannot be found or read
	 * @return The resource
	 */
	public static File getResourceAsFile(String resource) throws IOException {
		return new File(getResourceURL(resource).getFile());
	}

	/** */
	/**
	 * Returns a resource on the classpath as a File object
	 * 
	 * @param loader
	 *            The classloader used to load the resource
	 * @param resource
	 *            The resource to find
	 * @throws IOException
	 *             If the resource cannot be found or read
	 * @return The resource
	 */
	public static File getResourceAsFile(ClassLoader loader, String resource) throws IOException {
		return new File(getResourceURL(loader, resource).getFile());
	}
}
