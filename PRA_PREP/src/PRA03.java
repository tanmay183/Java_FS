/*
3
1001
bfsi
2
101
sagar
25000
102
shalini
26000
1002
cmi
1
204
sagars
30000
1003
bnts
2
301
subhash
12000
302
saniya
25000
sagar
20000
*/


import java.util.*;


class unit{
	private int uid;
	private String unmae;
	private int budget;
	private ArrayList<employee> emp;
	
	public unit(int a, String b, int c,ArrayList<employee> d) {
		this.uid=a;
		this.unmae=b;
		this.budget=c;
		this.emp=d;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUnmae() {
		return unmae;
	}

	public void setUnmae(String unmae) {
		this.unmae = unmae;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public ArrayList<employee> getEmp() {
		return emp;
	}

	public void setEmp(ArrayList<employee> emp) {
		this.emp = emp;
	}
}

class employee{
	private int eid;
	private String ename;
	private int salary;
	
	public employee(int a, String b, int c) {
		this.eid=a;
		this.ename=b;
		this.salary=c;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
}

class solution2{
	
	static void case1(ArrayList<unit> u, String name) {
		int sum=0;
		
		for(unit un : u) {
			ArrayList<employee> emp=un.getEmp();
			
			for(employee em: emp) {
				if(em.getEname().equalsIgnoreCase(name)) {
					try {
						if(em.getSalary()<0) {
							throw new SalaryisNegativeException();
						}
						else {
							sum+=em.getSalary();
						}
					} catch (SalaryisNegativeException e) {
						System.out.println(e.getMessage());
						return;
					}
				}
			}
			
			
		}
		if(sum>0) {
			System.out.println(sum);
		}
		else {
			System.out.println("No employee found with mention name");
		}
	}
	
	
	static void case2(ArrayList<unit> un, int salary) {
		
		ArrayList<String> s= new ArrayList<>();
		
		for(unit u : un) {
			ArrayList<employee> e=u.getEmp();
			
			for(employee emp : e) {
				if(emp.getSalary()>salary) {
					s.add(u.getUnmae());
					break;  // in same unit we got two slary > ip salary that why it print two time 
				}
			}
		}
		
		if(s.size()!=0) {
			for(int i=0; i<s.size(); i++) {
				System.out.println(s.get(i));
			}
		}
		else {
			System.out.println("No matching unit found");
		}
		
	
	}
	
}

class SalaryisNegativeException extends Exception{
	public SalaryisNegativeException() {
		super("Invalid salary : salary cannot be negative");
	}
}
public class PRA03 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n=sc.nextInt();sc.nextLine();
		
		ArrayList<unit> u = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			int a=sc.nextInt();sc.nextLine();
			String b=sc.nextLine();
			int c=sc.nextInt();sc.nextLine();
			
			ArrayList<employee> em= new ArrayList<>();
			
			for(int j=0; j<c; j++) {
				int a1=sc.nextInt();sc.nextLine();
				String b1=sc.nextLine();
				int c1=sc.nextInt();sc.nextLine();
				
				em.add(new employee(a1,b1,c1));
			}
			
			u.add(new unit(a,b,c,em));
		}
		
		solution2 s= new solution2();
		
		String ip1=sc.nextLine();
		int ip2=sc.nextInt();sc.nextLine();
		
		s.case1(u, ip1);
		s.case2(u, ip2);
		
		

	}

}
