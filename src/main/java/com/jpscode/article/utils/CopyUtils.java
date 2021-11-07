package com.jpscode.article.utils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtilsBean;

import com.github.slugify.Slugify;

public class CopyUtils extends BeanUtilsBean {

	private static Slugify slg = new Slugify();

	@Override
	public void copyProperty(Object dest, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {
		if (value == null) {
			return;
		}
		if (name.equals("title")) {
			super.copyProperty(dest, "slug", (Object)slg.slugify(value.toString()));
		}
		super.copyProperty(dest, name, value);
	}

}