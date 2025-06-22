/*
2
1
Alice
30
Female
2
101
channel
150.0
Floral
103
Gucci
180.0
Woody
2
Bob
35
Male
2
102
Dior
200.0
Citrus
104
Channel
220.0
Oriental
channel
 */



import java.util.*;

class customer{
	private int cid;
	private String name;
	private int age;
	private String gender;
	private ArrayList<perfume> p;
	
	public customer(int cid,String name,int age,String gender,ArrayList<perfume>p) {
		this.cid=cid;
		this.name=name;
		this.age=age;
		this.gender=gender;
		this.p=p;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public ArrayList<perfume> getP() {
		return p;
	}

	public void setP(ArrayList<perfume> p) {
		this.p = p;
	}
}


class perfume{
	private int pid;
	private String brand;
	private double price;
	private String fregrance;
	
	public perfume(int a,String b,double c,String d) {
		this.pid=a;
		this.brand=b;
		this.price=c;
		this.fregrance=d;
		
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getFregrance() {
		return fregrance;
	}

	public void setFregrance(String fregrance) {
		this.fregrance = fregrance;
	}
	
}

class solution{
	
	static void case1(ArrayList<customer> cu, String brand) {
		boolean flag = false;
		for(customer c:cu) {
			ArrayList<perfume> p= c.getP();
			
			for(perfume pr: p) {
				if(pr.getBrand().equalsIgnoreCase(brand)) {
					System.out.println(c.getName());
					flag=true;
				}
			}
			
		}
		if(flag==false) {
			System.out.println("No Matching Customer found");
		}
	}
	
	static void case2(ArrayList<customer> cu) {
		double sum=0.0;
		for(customer c:cu) {
			ArrayList<perfume> p= c.getP();
			
			for(perfume pr: p) {
				sum += pr.getPrice();
			}
			
			
		}
		
		System.out.println("Total Expenditure on perfume:$"+sum);
	}
}

public class PRA01 {

	public static void main(String[] args) {
		
         Scanner sc = new Scanner(System.in);
         int n=sc.nextInt();sc.nextLine();
         
         ArrayList<customer> cus=new ArrayList<>();
         
         for(int i=0; i<n; i++) {
        	 int a=sc.nextInt();sc.nextLine();
        	 String b=sc.nextLine();
        	 int c =sc.nextInt();sc.nextLine();
        	 String d=sc.nextLine();
        	 
        	 int nop= sc.nextInt();sc.nextLine();
        	 
        	 ArrayList<perfume> per= new ArrayList<>();
        	 for(int j=0; j<nop; j++) {
        		 int a1=sc.nextInt();sc.nextLine();
        		 String b1=sc.nextLine();
        		 double c1=sc.nextDouble();sc.nextLine();
        		 String d1=sc.nextLine();
        		 
        		 per.add(new perfume(a1,b1,c1,d1));
        	 }
        	 
        	 cus.add(new customer(a,b,c,d,per));
        	 
        	 
         }
         
         solution s= new solution();
         
         String ip1= sc.nextLine();
         
         s.case1(cus,ip1);
         s.case2(cus);
         
         
	}

}
