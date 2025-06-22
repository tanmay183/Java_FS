

import java.util.*;

class Fairy{
	private String fname;
	private String ttype;
	private double elevel;
	private int noofdays;
	private ArrayList<Integer> dailytasks;
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getTtype() {
		return ttype;
	}
	public void setTtype(String ttype) {
		this.ttype = ttype;
	}
	public double getElevel() {
		return elevel;
	}
	public void setElevel(double elevel) {
		this.elevel = elevel;
	}
	public int getNoofdays() {
		return noofdays;
	}
	public void setNoofdays(int noofdays) {
		this.noofdays = noofdays;
	}
	public ArrayList<Integer> getDailytasks() {
		return dailytasks;
	}
	public void setDailytasks(ArrayList<Integer> dailytasks) {
		this.dailytasks = dailytasks;
	}
	public Fairy(String fname, String ttype, double elevel, int noofdays, ArrayList<Integer> dailytasks) {
		super();
		this.fname = fname;
		this.ttype = ttype;
		this.elevel = elevel;
		this.noofdays = noofdays;
		this.dailytasks = dailytasks;
	}
}

class TalentNotFoundException extends Exception{
	public TalentNotFoundException() {
		super("No Fairies Found");
	}
}

class NoTaskDataException extends Exception{
	public NoTaskDataException() {
		super("No Task Data Available");
	}
}

public class PRA05 {
	
	public static void listByTtype(ArrayList<Fairy> list,String ttype) {
		int flag = 0;
		for(Fairy f:list) {
			if(f.getTtype().equalsIgnoreCase(ttype)) {
				System.out.println(f.getFname());
				flag++;
			}
		}
		if(flag == 0) {
//			System.out.println("No Fairies Found");
			//To run the code without using exception just uncomment sysout line and comment try-catch block/
			try {
				throw new TalentNotFoundException();
			}catch(TalentNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void hardestWorkingFairy(ArrayList<Fairy> list) {
		int flag = 0;
		int hardestWork = 0;
		String hFairy = "";
		for(Fairy f:list) {
			int total = 0;
			for(int i:f.getDailytasks()) {
				total += i;
				flag++;
			}
			if(total > hardestWork) {
				hardestWork = total;
				hFairy = f.getFname();
			}
		}
		if(flag == 0) {
//			System.out.println("No Task Data Available");
			//To run the code without using exception just uncomment sysout line and comment try-catch block/
			try {
				throw new NoTaskDataException();
			}catch(NoTaskDataException e) {
				System.out.println(e.getMessage());
			}
		}else {			
			System.out.println(hFairy);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		sc.nextLine();
		
		ArrayList<Fairy> list = new ArrayList<>();
		
		for(int i=0;i<n;i++) {
			String fname = sc.nextLine();
			String ttype = sc.nextLine();
			double elevel = sc.nextDouble();
			sc.nextLine();
			int noofdays = sc.nextInt();
			sc.nextLine();
			
			ArrayList<Integer> taskCompleted = new ArrayList<>();
			
			for(int j=0;j<noofdays;j++) {
				int task = sc.nextInt();
				sc.nextLine();
				
				taskCompleted.add(task);
			}
			
			list.add(new Fairy(fname,ttype,elevel,noofdays,taskCompleted));
		}
		String checkTtype = sc.nextLine();
		listByTtype(list,checkTtype);
		hardestWorkingFairy(list);
	}
}
//Inputs

//2
//lara
//a
//49
//3
//10
//11
//12
//tinkerbell
//b
//80
//2
//5
//6
//a