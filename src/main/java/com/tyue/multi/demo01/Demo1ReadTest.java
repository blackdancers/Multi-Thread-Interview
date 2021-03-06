package com.tyue.multi.demo01;

/**
 * @Blog http://blog.csdn.net/acmman/article/details/53116117
 *       现有的程序代码模拟产生了16个日志对象，并且需要运行16秒才能打印完这些日志，请在程序
 *       中增加4个线程去调用parseLog()方法来分头打印这16个日志对象，程序只需要运行4秒即可打印 玩这些日志对象。原始代码如下:
 * @author blackdancer
 *
 */
public class Demo1ReadTest {

	public static void main(String[] args) {
		System.out.println("begin:" + (System.currentTimeMillis() / 1000));
		/*
		 * 模拟处理16行日志，下面的代码产生了16条日志对象， 当前代码需要运行16秒才能打印完这些日志
		 */
		for (int i = 0; i < 16; i++) {
			final String log = "" + (i + 1);// 这行代码不能改动
			{
				Demo1ReadTest.parseLog(log);
			}
		}
	}

	// parseLog方法内的代码不能改动
	private static void parseLog(String log) {
		System.out.println(log + ":" + (System.currentTimeMillis() / 1000));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
