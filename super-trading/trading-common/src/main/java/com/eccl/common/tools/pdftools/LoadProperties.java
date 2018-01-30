package com.eccl.common.tools.pdftools;
import java.util.ResourceBundle;

public class LoadProperties 
{
	private ResourceBundle resBundle;
	
	public LoadProperties(String resourceName)
	{
		resBundle = ResourceBundle.getBundle(resourceName);
	}
    public String getValue(String key) 
    {
        return resBundle.getString(key);
    }
}
