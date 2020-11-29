

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ReadersWritersSemaphore {

	public static void main(String[] args) {
        
		Database d = new Database();

		Writer w1 = new Writer(d,1);
		Writer w2 = new Writer(d,10);
		Writer w3 = new Writer(d,100);
		Writer w4 = new Writer(d,1000);
		Writer w5 = new Writer(d,10000);
		Reader r1 = new Reader(d);
		Reader r2 = new Reader(d);
		Reader r3 = new Reader(d);
		Reader r4 = new Reader(d);
		Reader r5 = new Reader(d);
		Reader r6 = new Reader(d);
		Reader r7 = new Reader(d);
		Reader r8 = new Reader(d);
		Reader r9 = new Reader(d);
		Reader r10 = new Reader(d);

		w1.start();
		w2.start();
		w3.start();
		r1.start();
		r2.start();
		r3.start();
		r4.start();
		r5.start();
		w4.start();
		w5.start();
		r6.start();
		r7.start();
		r8.start();
		r9.start();
		r10.start();
	}
}

 
 class Reader extends Thread {
		Database d;
		public Reader(Database d) {
			this.d = d;
		}

		public void run() {
			for (int i = 0; i<5; i++) {
				d.request_read();
				System.out.println(d.read());
				d.done_read();
			}
		}
	}

	class Writer extends Thread {
		Database d;
		int x;

		public Writer(Database d, int x) {
			this.d = d;
			this.x = x;
		}

		public void run() {
			for (int i = 0; i<5; i++) {
				try {Thread.sleep(new Random().nextInt(200));}
				catch (InterruptedException e) {}
				d.request_write();
				d.write(x);
				d.done_write();
			}
		}
	}

	
	class Database {
		int data = 0;
		int r = 0;
		int w = 0;
		int ww = 0;
		int wr = 0;

		Semaphore s1 = new Semaphore(1);   // 'synchronized'
		Semaphore s2_r = new Semaphore(0); // for readers
		Semaphore s2_w = new Semaphore(0); // for writers

		public void request_read() {
			try {
				s1.acquire();
				while (w == 1 || ww > 0) {
					try {wr++;
						s1.release();
						s2_r.acquire(); // reader waits here
						s1.acquire();
						wr--;
					} catch (Exception e) {}
				}
				r++;
				s1.release();
			}
			catch (Exception e) {}
		}

		public int read() {
			int mydata = data;
			return mydata;
		}

		public void done_read() {
			try {
				s1.acquire();
				r--;				
				if (r==0 && ww > 0) 
					s2_w.release();
			    else
				if (wr > 0) 		 
					{ while(s2_r.hasQueuedThreads()) 
						s2_r.release();   
					} 
				s1.release();
			} catch (Exception e) {
			}

		}

		public void request_write() {
			try {
				s1.acquire();
				while (r > 0 || w == 1) {
					try {				
						ww++;
						s1.release();
						s2_w.acquire(); // writer waits here
						s1.acquire();
						ww--;
					} catch (Exception e) {}
				}
				w++;
				s1.release();
			} catch (Exception e) {
			}
		}

		public void write(int x) {
			try { 
				s1.acquire();
				data = data + x;
				s1.release();
			} catch (Exception e) { }
		}

		public void done_write() {
			try {
				s1.acquire();
				w--;
				if (ww > 0) 
					s2_w.release();
			    else
				if (wr > 0) 		 
					{ while(s2_r.hasQueuedThreads()) 
						s2_r.release();   
					} 
				s1.release();
			} catch (Exception e) {}
		}
	}
 