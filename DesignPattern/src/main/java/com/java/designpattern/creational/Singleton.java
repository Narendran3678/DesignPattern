package com.java.designpattern.creational;

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
class SingletonLazyLoading
{
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
	public static synchronized SingletonHolderPattern getInstance()// To make it Thread Safe
	{
		return SingletonHolder.SINGLETON_HOLDER_INSTANCE;
	}
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
	public static void main(String args[])
	{
		SingletonEager();
		System.out.println();
		SingletonStatic();
		System.out.println();
		SingletonLazy();
		System.out.println();
		SingletonHolder();
	}
}