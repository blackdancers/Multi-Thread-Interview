package com.tyue.multi.demo01;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Blog http://blog.csdn.net/acmman/article/details/53116117
 * 
 *       官方给的是使用阻塞队列来完成，其实同样，这个问题也可以这么解决
 * @author blackdancer
 *
 */
public class Demo3ReadTest {

	public static void main(String[] args) {
		// 创建容量为4的线程池
		//ExecutorService executor = Executors.newFixedThreadPool(4);
		// 创建容量为4的阻塞队列
		final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(4);
		for (int i = 0; i < 4; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					// 该线程等待打印，只要queue有数据，就打印
					String logString = null;
					while (true) {//死循环，代码量大
						try {
							if (queue.size() > 0) {
								logString = queue.take();
								Demo3ReadTest.parseLog(logString);
							}
						} catch (InterruptedException e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
				}
			}).start();
		}

		System.out.println("begin:" + (System.currentTimeMillis() / 1000));
		/*
		 * 模拟处理16行日志，下面的代码产生了16条日志对象， 当前代码需要运行16秒才能打印完这些日志
		 */
		for (int i = 0; i < 16; i++) {
			final String log = "" + (i + 1);// 这行代码不能改动
			{
				try {
					queue.put(log);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
