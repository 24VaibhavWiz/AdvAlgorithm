package programming_projects;
import java.util.*;
import java.io.*;
@SuppressWarnings("unused")

public class PageRanking {

	public int path[][] = new int[10][10];
	public double pagerank[] = new double[10];
	
	public void calculate(double totalNodes)
	{
		double  IntialPageRank,
				OutgoinLinks = 0,
				DampingFcator = .85,
				TempPageRanking[] = new double[10];
		
		int ExternalNodeNumber,
		    InternalNodeNumber,
		    i = 1,
		    InterartionStep = 1;
		
		IntialPageRank = 1/totalNodes;
		System.out.println("Total Number of Nodes :- "+totalNodes+"\nInitial PageRank of All Nodes :- "+IntialPageRank+"\n");
		
		//0th Iteration or Initialization Phase
		
		for (i = 0; i <= totalNodes; i++) 
			this.pagerank[i] = IntialPageRank;
		
		System.out.println("Initial Page Rank Values, 0th Step\n");
		
		for (i = 0; i <= totalNodes; i++) 
			System.out.println("Page Rank of "+i+" is :- "+this.pagerank[i]+"\n");
		
		while(InterartionStep <=2)
		{
			//Store the Page Ran fro all Nodes in Temp Array
			
			for (i = 0; i <= totalNodes; i++)
			{
				TempPageRanking[i] = this.pagerank[i];
				this.pagerank[i] = 0;
			}
			
			for(InternalNodeNumber = 1; InternalNodeNumber <= totalNodes; InternalNodeNumber ++)
			{
				for(ExternalNodeNumber = 1; ExternalNodeNumber <= totalNodes; ExternalNodeNumber ++)
				{
					if(this.path[ExternalNodeNumber][InternalNodeNumber] == 1)
					{
						i = 1;
						OutgoinLinks = 0;
						
						//Count the Number of Outgoing Links for each ExternalNodeNUmber
						
						while(i<=totalNodes)
						{
							if(this.path[ExternalNodeNumber][i] == 1)
								OutgoinLinks ++;
							i++;
						}
						this.pagerank[InternalNodeNumber]+=TempPageRanking[ExternalNodeNumber]*(1/OutgoinLinks);
					}
				}
			}
			
			System.out.println("\nAfter "+InterartionStep+"th Step\n");
			
			for (i = 0; i <= totalNodes; i++)
				System.out.println("Page Rank of"+i+" is :- "+this.pagerank[i]+"\n");
			
			InterartionStep ++;
		}
		
		//Add the  Damping Factor to Page Rank
		
		for (i = 0; i < TempPageRanking.length; i++) 
			this.pagerank[i] = (1-DampingFcator)+DampingFcator*this.pagerank[i];
		
		//Display Page Rank
		
		System.out.println("\nFinal Page Rank \n");
		
		for (i = 0; i < TempPageRanking.length; i++) 
			System.out.println("Page Rank of "+i+" is :- "+this.pagerank[i]+"\n");
	}
	
	@SuppressWarnings("resource")
	
	public static void main(String[] args) {
		
		int nodes,cost;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the Number of WebPages :- ");
		nodes = sc.nextInt();
		PageRanking pr = new PageRanking();
		
		for (int i = 0; i < nodes; i++) {
			for (int j = 0; j < args.length; j++) {
				pr.path[i][j] = sc.nextInt();
				if(j == i)
					pr.path[i][j] = 0;
			}
			pr.calculate(nodes);
		}
	}
}
