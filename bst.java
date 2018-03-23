import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Arrays;

// Test binary search trees (bst).
public class bst {

	private static int N = 20;
	private static int[] res;
	private static int[] ops;
	private static int[] keys;
	private static long[] times;
	private static long[] totalTimes;
	private static boolean print;

	public static void sort(int[] keys, int[] res, long[] times) {
		long begin1 = System.currentTimeMillis();
		avltree awesome = new avltree();
		for(int i = 0; i < N; i++){
			awesome.insert(keys[i]);
		}
		awesome.getRoot().inorder(0, res);
		awesome.clear();
		long end1 = System.currentTimeMillis();
		times[0] = end1 - begin1;

		
		long begin2  = System.currentTimeMillis();
		redblacktree exll = new redblacktree();
		for(int i = 0; i < N; i++){
			exll.insert(keys[i]);
		}
		exll.getRoot().inorder(0, res);
		exll.clear();
		long end2 = System.currentTimeMillis();
		times[1] = end2 - begin2;



		long begin3 = System.currentTimeMillis();
		splaytree great  = new splaytree();
		for(int i = 0; i < N; i++){
			great.insert(keys[i]);
		}
		great.getRoot().inorder(0, res);
		great.clear();
		long end3 = System.currentTimeMillis();
		times[2] = end3 - begin3;
	}
	
    // Test program
	public static void test() {
		int i;
		
		long start, finish;
		
		if (N <= 600000) {
			start = System.currentTimeMillis();
			treeNode bst = treeNode.nullnode;
			for(i = 0; i < N; i++) { 
				switch (ops[i]) {
				case 0:   // insert
				case 1: bst = bst.insert( keys[i] ); break;
				case 2: bst = bst.remove( keys[i] ); break;
				case 3: bst.find(keys[i]); break;
				}
			}		
			if (N < 10 && print) {
				System.out.println( "Binary search tree:");
				bst.printTree( ); System.out.println();
			}
			bst = treeNode.nullnode;	
			finish = System.currentTimeMillis();
			times[0] = finish-start;
			if (print) System.out.println("Binary search tree: " + (finish-start) + " milliseconds.");
		} else
			if (print) System.out.println("Binary search tree skipped due to large size.");
		
		start = System.currentTimeMillis();
		avltree avl = new avltree();
		for(i = 0; i < N; i++) { 
			switch (ops[i]) {
			case 0:   // insert
			case 1: avl.insert( keys[i] ); break;
			case 2: avl.remove( keys[i] ); break;
			case 3: avl.find( keys[i] ); break;
			}
		}		
		if (N < 100 && print) { 
			System.out.println( "\nAVL search tree:");
		    avl.getRoot().printTree( ); System.out.println();
		}
		avl.clear();
		finish = System.currentTimeMillis();
		times[1] = finish-start;
        if (print) System.out.println("AVL search tree: " + (finish-start) + " milliseconds.");
		
		start = System.currentTimeMillis();
		redblacktree rbt = new redblacktree();
		for(i = 0; i < N; i++) { 
			switch (ops[i]) {
			case 0:   // insert
			case 1: rbt.insert( keys[i] ); break;
			case 2: rbt.remove( keys[i] ); break;   // to be implemented
			case 3: rbt.find( keys[i] ); break;
			}
			//rbt.getRoot().printTree( ); System.out.println("RB");
		}
		if (N < 100) {
			if (print) System.out.println( "\nRed-Black search tree:");
		    rbt.getRoot().printTree( ); System.out.println();
		}
		finish = System.currentTimeMillis();
		times[2] = finish-start;
		rbt.checkRedBlack(); 
		rbt.clear();
        if (print) System.out.println("Red-Black search tree: " + (finish-start) + " milliseconds.");
        
		start = System.currentTimeMillis();
		splaytree spt = new splaytree();
		for(i = 0; i < N; i++) { 
			for(i = 0; i < N; i++) { 
				switch (ops[i]) {
				case 0:   // insert
				case 1: spt.insert( keys[i] ); break;
				case 2: spt.remove( keys[i] ); break;
				case 3: spt.find( keys[i] ); break;
				}
			}
		}
		if (N < 100 && print) {
			System.out.println( "\nSplay search tree:");
		    spt.getRoot().printTree( ); System.out.println();
		}
		spt.clear();
		finish = System.currentTimeMillis();
		times[3] = finish-start;
        if (print) System.out.println("Splay search tree: " + (finish-start) + " milliseconds.");
        
        start = System.currentTimeMillis();
        Arrays.sort(keys);
		finish = System.currentTimeMillis();
        if (print) System.out.println("Sorting: " + (finish-start) + " milliseconds.");
       
	}

	public static void questions() {

		 
		long[] totalTimes;
		print = true;
		System.out.println("\n\nQestion 0 Test Result:\n");
		question0();
		print = false;
		System.out.println("\n\nQestion 2 Run Time Collecting:\n");
		question1();
	}

	public static void question0() {
		int NUMS; 
		final int GAP  = 37;
		int i, j;

		times = new long[4];
		keys = new int[N];
        	ops = new int[N];

        	NUMS = ((N & 1) == 1)? N+1 : N;
        	Random randomGenerator = new Random();
        
  		if (print) System.out.println( "\nChecking... " );
		j = 0;
		for(i = 0; i < N; i++) {
			j = ( j + GAP ) % NUMS;
			keys[i] = j;
			ops[i] = randomGenerator.nextInt(4);
			if (N < 100) { 
				switch (ops[i]) {
				case 0:   // insert
				case 1: System.out.print(" i" + keys[i] ); break;
				case 2: System.out.print(" r" + keys[i] ); break;
				case 3: System.out.print(" f" + keys[i] ); break;	
				}
			}
		}
		if (N<100 && print) System.out.println("\nNote: i=insert; r=remove; f=find.\n");

		test();
	}
	public static void question1() {
		int examples = 2;
		N = 5000000;
		totalTimes = new long[3];

		for (int i=0;i<examples;i++) {
			question0();
			System.out.printf("\nExample %d: AVL %d ms, Red-Black %d ms, Splay: %d ms\n",i+1,times[1],times[2],times[3]);
			for (int j=0;j<3;j++){
				totalTimes[j]+=times[j+1];
			}
		}
		System.out.printf("\nTotal: AVL %.2f s, Red-Black %.2f s, Splay %.2f s\n\n",totalTimes[0]/1000.0,totalTimes[1]/1000.0,totalTimes[2]/1000.0);
	}
	public static void question2() {
		System.out.println();
		res = new int[N];
		sort( keys, res, times );
	}
	
	public static void main( String [ ] args ) {
		/*
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            System.out.print("Please give number of elements to be inserted : ");
            N = Integer.parseInt(read.readLine());
        } catch(Exception ex){
            ex.printStackTrace();
        }*/   
        questions();
	}

}
