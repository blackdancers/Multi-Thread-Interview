package com.tyue.multi.demo02;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerDemo01 {

	public static void main(String[] args) {
		// 创建容量为10的阻塞队列
		final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
		final Lock lock = new ReentrantLock();
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					String inputString = null;
					if (queue.size() > 0) {
						lock.lock();
						try {
							inputString = queue.take();
							String outputString = ConsumerDemo.doSome(inputString);
							System.out.println(Thread.currentThread().getName()+ ":" + outputString);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}finally{
							lock.unlock();
						}
					}
				}
			}).start();

		}
		System.out.println("begin:" + (System.currentTimeMillis() / 1000));
		for (int i = 0; i < 10; i++) {// 这行不能改动
			String input = i + "";// 这行不能改动
			try {
				queue.put(input);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
/**

Thread-6:0:1493867332
Thread-6:1:1493867333
Thread-6:2:1493867334
Thread-6:3:1493867335
Thread-6:4:1493867336
Thread-5:5:1493867337
Thread-5:6:1493867338
Thread-0:7:1493867339
Thread-0:8:1493867340
Thread-0:9:1493867341
这样就实现了按照顺序打印，可是大家可以看到，我们只使用了十个线程里面的3个，就不满足题目说的十个线程都去执行打印操作的要求，所以上面这个代码还是不成立的。
*/