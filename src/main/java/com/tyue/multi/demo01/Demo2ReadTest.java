package com.tyue.multi.demo01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Blog http://blog.csdn.net/acmman/article/details/53116117
 * 
 *       我创建了容量为4的线程池，然后每次打印的时候创建一个新的线程对象放进去，因为
 *       容量为4，每次只有四个线程同时运行，所以每一秒钟完成四个，下四个线程进去继续运行，如此就实现了4秒打印16个日志的效果。
 * @author blackdancer
 *
 */
public class Demo2ReadTest {

	public static void main(String[] args) {
		// 创建容量为4的线程池
		ExecutorService executor = Executors.newFixedThreadPool(4);

		System.out.println("begin:" + (System.currentTimeMillis() / 1000));
		/*
		 * 模拟处理16行日志，下面的代码产生了16条日志对象， 当前代码需要运行16秒才能打印完这些日志
		 */
		for (int i = 0; i < 16; i++) {
			final String log = "" + (i + 1);// 这行代码不能改动
			{
				executor.execute(new Runnable() {
					@Override
					public void run() {
						Demo2ReadTest.parseLog(log);
					}
				});
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
