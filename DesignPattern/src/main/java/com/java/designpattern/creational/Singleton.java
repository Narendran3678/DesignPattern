package com.java.designpattern.creational;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class SingletonEagerLoading
{
	private static SingletonEagerLoading eagerClass = new SingletonEagerLoading();
	private SingletonEagerLoading()
	{
		
	}
	public static SingletonEagerLoading getInstance()
	{
		return eagerClass;
	}
}
class SingletonStaticLoading // We can manage Exception
{
	private static SingletonStaticLoading staticClass = null;
	static
	{
		try
		{
			staticClass = new SingletonStaticLoading();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	private SingletonStaticLoading()
	{
		
	}
	public static SingletonStaticLoading getInstance()
	{
		return staticClass;
	}
}
class SingletonLazyLoading implements Serializable,Cloneable
{
	private static final long serialVersionUID = 1L;
	private static SingletonLazyLoading lazyClass = null;
	private SingletonLazyLoading()
	{
		
	}
	public static synchronized SingletonLazyLoading getInstance()// To make it Thread Safe
	{
		if(lazyClass==null)
		{
			lazyClass = new SingletonLazyLoading();
		}
		return lazyClass;
	}
	// Will preventing Hashcode change during serailization process
	private Object readResolve()
	{
		 return getInstance();
	}
	@Override
	protected Object clone() throws CloneNotSupportedException // Preventing Singleton from Cloning
	{
		throw new CloneNotSupportedException("You cannot create Clone of Singleton object");
	}
	
}
class SingletonHolderPattern
{
	private SingletonHolderPattern()
	{
		
	}
	private static class SingletonHolder
	{
		static SingletonHolderPattern SINGLETON_HOLDER_INSTANCE = new SingletonHolderPattern();
	}
	public static SingletonHolderPattern getInstance()//No Need To add Synchronized
	{
		return SingletonHolder.SINGLETON_HOLDER_INSTANCE;
	}
}
enum SingletonEnum
{
	INSTANCE;
}
public class Singleton 
{
	
	private static void SingletonEager()
	{
		System.out.println("Singleton Eager Loading");
		System.out.println(SingletonEagerLoading.getInstance());
		System.out.println(SingletonEagerLoading.getInstance());
	}
	private static void SingletonStatic()
	{
		System.out.println("Singleton Static Loading");
		System.out.println(SingletonStaticLoading.getInstance());
		System.out.println(SingletonStaticLoading.getInstance());
	}
	private static void SingletonLazy()
	{
		System.out.println("Singleton Lazy Loading");
		System.out.println(SingletonLazyLoading.getInstance());
		System.out.println(SingletonLazyLoading.getInstance());
	}
	private static void SingletonHolder()
	{
		System.out.println("Singleton Holder Loading");
		System.out.println(SingletonHolderPattern.getInstance());
		System.out.println(SingletonHolderPattern.getInstance());
	}
	private static void breakSingleton()
	{
		try 
		{
			System.out.println("Break Singleton Loading");
			Constructor cons = SingletonEagerLoading.class.getDeclaredConstructor();
			cons.setAccessible(true);
			try 
			{
				System.out.println(SingletonEagerLoading.getInstance());
				SingletonEagerLoading  breakClass =  (SingletonEagerLoading) cons.newInstance();
				System.out.println(breakClass);
				
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
			
		} 
		catch (NoSuchMethodException | SecurityException e) 
		{
			e.printStackTrace();
		}
	}
	private static void EnumSingleton()
	{
		System.out.println("Preventing Singleton Breaking by Enum Loading");// Drawback cannot do lazy loading
		System.out.println(SingletonEnum.INSTANCE.hashCode());
		System.out.println(SingletonEnum.INSTANCE.hashCode());
	}
	public static void serializeSingleton() throws CloneNotSupportedException
	{
		System.out.println("Serialize Singleton Loading");
		SingletonLazyLoading lazyLoading = SingletonLazyLoading.getInstance();
	//	SingletonLazyLoading lazyLoading1 = (SingletonLazyLoading) lazyLoading.clone();
		System.out.println("Before Serailization ["+lazyLoading+"]");
		//Serialize
		try
		{
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("serialize.ser"));
			outputStream.writeObject(lazyLoading);
			outputStream.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//Deserialize
		try
		{
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("serialize.ser"));
			lazyLoading = (SingletonLazyLoading)inputStream.readObject();	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("After Serailization ["+lazyLoading+"]");
	}
	public static void main(String args[]) throws CloneNotSupportedException
	{
		SingletonEager();
		System.out.println();
		SingletonStatic();
		System.out.println();
		SingletonLazy();
		System.out.println();
		SingletonHolder();
		System.out.println();
		breakSingleton();
		System.out.println();
		EnumSingleton();
		System.out.println();
		serializeSingleton();
	}
	
}