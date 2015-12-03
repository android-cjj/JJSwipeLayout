package com.cjj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.reflect.Field;

public class InflateViewHolder {
	
	private static final String ID = "id";

	public static View inflateConvertView(Context mContext, View convertView, int layoutId, Class<?> cls)
	{
		try
		{
			if (null == convertView) 
			{
				convertView = LayoutInflater.from(mContext).inflate(layoutId,null);
				Object obj = cls.newInstance();
				Field[] mFields = obj.getClass().getDeclaredFields();

				for (Field mField : mFields)
				{
		        	//得到资源ID
					int resourceId = mContext.getResources().getIdentifier(mField.getName(),ID,mContext.getApplicationContext().getPackageName());
					//允许访问私有属性
					mField.setAccessible(true);
					//保存实例化后的资源
					mField.set(obj,convertView.findViewById(resourceId));
				}
				convertView.setTag(obj);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return convertView;
	}

	public static <T>T inflateViewHolder(View itemView, Class<T> cls)
	{
		Context mContext = itemView.getContext();
		Object obj = null;
		try
		{
			obj = cls.newInstance();
			Field[] mFields = obj.getClass().getDeclaredFields();

			for (Field mField : mFields)
			{
				// 得到资源ID
				int resourceId = mContext.getResources().getIdentifier(mField.getName(),ID,mContext.getApplicationContext().getPackageName());
				// 允许访问私有属性
				mField.setAccessible(true);
				// 保存实例化后的资源
				mField.set(obj,itemView.findViewById(resourceId));
			}
		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return (T)obj;
	}
	
}