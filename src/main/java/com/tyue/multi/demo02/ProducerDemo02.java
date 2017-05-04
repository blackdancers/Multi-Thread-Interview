package com.tyue.multi.demo02;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

public class ProducerDemo02 {

	public static void main(String[] args) {
		//信号灯
		final Semaphore semaphore = new Semaphore(1);
		//
		final SynchronousQueue<String> queue = new SynchronousQueue<String>();
		
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					String inputString = null;
					try {
						semaphore.acquire();//占用信号灯
						inputString = queue.take();
						String outputString = ConsumerDemo.doSome(inputString);
						System.out.println(Thread.currentThread().getName()+ ":" + outputString);
						semaphore.release();//释放信号灯
					} catch (InterruptedException e) {
						e.printStackTrace();
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
