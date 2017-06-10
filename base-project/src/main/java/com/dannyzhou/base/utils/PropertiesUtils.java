package com.dannyzhou.base.utils;

import com.dannyzhou.base.exception.GetPropertyException;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by danny on 2017/6/10.
 */
public class PropertiesUtils {

    private PropertiesUtils() {
    }

    private static final String CONFIG_FILE = "commonConfig.properties";

    private static PropertiesUtils propertiesUtils = new PropertiesUtils();

    public static PropertiesUtils getInstance() {
        return propertiesUtils;
    }

    public String GetStringByKey(String key) {
        Properties properties = new Properties();
        InputStream in = null;
        try {
            in = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE);
            properties.load(in);
            String value = properties.getProperty(key);
            if (StringUtils.isBlank(value)) {
                throw new GetPropertyException("value is blank");
            }
            return value.trim();

        } catch (IOException e) {
            throw new GetPropertyException(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new GetPropertyException(e);
                }
            }
        }
    }

    public Integer getIntegerByKey(String key) {
        String result = GetStringByKey(key);
        return Integer.parseInt(result);
    }
}
