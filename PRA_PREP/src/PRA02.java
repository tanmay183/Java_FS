/*
3
1001
cus1
2
101
zeeta
12000
102
voltic
2500
1002
cus2
1
201
zeeta
3000
1003
cus3
2
301
zeetalx
1200
302
volticlx
2500
zeeta
3000
 */

import java.util.*;

class customer1{
	private int cid;
	private String name;
	private int noc;
	private ArrayList<cycle> cy;
	
	public customer1(int a,String b,int c,ArrayList<cycle>d) {
		this.cid=a;
		this.name=b;
		this.noc=c;
		this.cy=d;
		
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNoc() {
		return noc;
	}

	public void setNoc(int noc) {
		this.noc = noc;
	}

	public ArrayList<cycle> getCy() {
		return cy;
	}

	public void setCy(ArrayList<cycle> cy) {
		this.cy = cy;
	}
	
}
class cycle{
	private int cyid;
	private String cyname;
	private int price;
	
	public cycle(int a, String b,int c) {
		this.cyid=a;
		this.cyname=b;
		this.price=c;
	}

	public int getCyid() {
		return cyid;
	}

	public void setCyid(int cyid) {
		this.cyid = cyid;
	}

	public String getCyname() {
		return cyname;
	}

	public void setCyname(String cyname) {
		this.cyname = cyname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}
class solution1{
	
	static void case1(ArrayList<customer1> c,String cname) {
		int sum=0;
		for(customer1 cs: c) {
			ArrayList<cycle> cy=cs.getCy();
			
			for(cycle cyc : cy) {
				if(cyc.getCyname().equalsIgnoreCase(cname)) {
					try {
						if(cyc.getPrice()<0) {
							throw new PriceisNegativeException();
						}
						else {
							sum+=cyc.getPrice();
						}
						
					} catch (PriceisNegativeException e) {
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
			System.out.println("No cycle found with mentioned name");
		}
	}
	
	
	static void case2(ArrayList<customer1> c,int price) {
		boolean flag=false;
		for(customer1 cs: c) {
			ArrayList<cycle> cy=cs.getCy();
			
			for(cycle cyc : cy) {
				if(cyc.getPrice()>price) {
					System.out.println(cs.getName());
					flag=true;
				}
			}
			
			
		}
		if(flag==false) {
			System.out.println("No matching customer found");
		}
	}
	
	
	
}

class PriceisNegativeException extends Exception{
	public PriceisNegativeException() {
		super("Invalid Price: Price cannot be negative");
	}
}


public class PRA02 {

	public static void main(String[] args) {
		
         Scanner sc = new Scanner(System.in);
         
         int n=sc.nextInt();sc.nextLine();
         ArrayList<customer1> cu = new ArrayList<>();
         
         for(int i=0; i<n; i++) {
        	 int a=sc.nextInt();sc.nextLine();
        	 String b=sc.nextLine();
        	 int c=sc.nextInt();sc.nextLine();
        	 
        	 ArrayList<cycle> cy = new ArrayList<>();
        	 for(int j=0; j<c; j++) {
        		 int a1=sc.nextInt();sc.nextLine();
        		 String b1=sc.nextLine();
        		 int c1=sc.nextInt();sc.nextLine();
        		 
        		 cy.add(new cycle(a1,b1,c1));
        	 }
        	 
        	 cu.add(new customer1(a,b,c,cy));
         }
         solution1 s = new solution1();
         
         String ip1=sc.nextLine();
         
         
         
         int ip2=sc.nextInt();
         s.case1(cu, ip1);
         s.case2(cu, ip2);
         
	}

}
