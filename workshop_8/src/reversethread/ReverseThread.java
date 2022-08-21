/**********************************************
Workshop 8
Course:JAC444, Semester - 4
Last Name: Anand
First Name: Ashwin
ID: 152042206
Section: NDD
This assignment represents my own work in accordance with Seneca Academic Policy.
Ashwin Anand
Date: 27 July 2022
**********************************************/

package reversethread;

public class ReverseThread extends Thread{
	static int numOfThread = 50;  
	String name;
	
	public static int getNumOfThread() {
		return numOfThread;
	}
	public static void setNumOfThread(int numOfThread) {
		ReverseThread.numOfThread = numOfThread;
	}
	
	public void setThreadName(String name) {
		this.name = name;
	}
	
	ReverseThread(){
		name = "";
	}
	
	ReverseThread(String threadName){
		super(threadName);
	}
	
	public String getThreadName() {
		return name;
	}
	public void run() {
		
		if(numOfThread > 0) {
			this.setThreadName("Thread! " + getNumOfThread());
			System.out.println("Hello from " + this.getThreadName());
			numOfThread--;
			
			ReverseThread thread = new ReverseThread();
			thread.start();
		}
		
		
	}
    public static void main(String[] args) {
        ReverseThread thread = new ReverseThread();
        thread.start();      
    }
    
}
