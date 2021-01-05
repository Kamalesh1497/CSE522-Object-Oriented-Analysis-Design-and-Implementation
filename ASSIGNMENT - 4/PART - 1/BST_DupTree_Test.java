import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BST_DupTree_Test {

	        static DupTree dtr;
		static List<Integer> al = new ArrayList<Integer>();
		static Random r = new Random();

	// add annotation
	@BeforeAll
	public static void setup() {
		
		// getting the random value
		int ranNumber = 0;
		ranNumber = r.nextInt(25);
		
		// adding it in the duptree
		dtr = new DupTree(ranNumber);
		al.add(ranNumber); // adding in the list
		
		for(int i = 0 ; i < 24; i++) {
		
			ranNumber = r.nextInt(25);
			al.add(ranNumber);
			dtr.insert(ranNumber);
		
		}
		
		//sorting the list
		Collections.sort(al);
		
		//iterator for duptree
		Iterator<Integer> dupTreeItr = dtr.iterator();
		
		System.out.println("DupTree created in Setup: ");
		
		// iterating the duptree iterator till end
		while (dupTreeItr.hasNext()) {
			
			System.out.print(dupTreeItr.next()+" ");
		
		}
		
		System.out.println(" ");
		System.out.println("\nSorted ArrayList created in Setup: ");
		

		//iterator for list
		Iterator<Integer> listItr = al.iterator();
		
		// iterating the list iterator till end
		while (listItr.hasNext()) {
			
			System.out.print(listItr.next()+" ");
			
		}
		
		System.out.println("\n");
	}
	
	// add annotation
	@BeforeEach
	@AfterEach
	void check_invariant() {
		
		//checking if the tree follows bst property
		assertTrue(ordered(dtr));
		
		System.out.println("----------------------------");
		System.out.println("DupTree invariant maintained");
		System.out.println("----------------------------\n");
		//  to be filled in by you
	}
	

	@Test
	void test_delete() {

		//adding the 10 values in tree for deletion
		for(int i =0;i<10;i++) {
			
			System.out.print("Testing DupTree Delete");
			
			//getting random value
			int ranNumber = r.nextInt(25);
			
			//checking if the tree maintains bst property before insert
			assertTrue(ordered(dtr));
			
			//inserting the random value in tree
			dtr.insert(ranNumber);
			
			//checking if the tree maintains bst property after insert
			assertTrue(ordered(dtr));
			
			System.out.println( "(" + ranNumber + ")" );
			
			//count before the insertion
			int countBeforeInsertion = get_count(dtr, ranNumber);
			
			System.out.println("inserted value " + ranNumber + " into duptree; count = " + countBeforeInsertion + " after insertion.");
			
			//deleting the random value 
			dtr.delete(ranNumber);
			
			//count after the insertion
			int countAfterInsertion = get_count(dtr, ranNumber);
			
			//checking if the count after insertion is 0 or not
			if(countAfterInsertion!=0) {
			
				System.out.println("deleted value " + ranNumber + " from duptree; count = " + countAfterInsertion + " after deletion.");
			
			}else {
				
				System.out.println("deleted value " + ranNumber + " from duptree; it is no longer present in duptree");
			
			}
			
			//checking if the count is equal before and after insertion
			assertEquals(countBeforeInsertion, countAfterInsertion + 1);	
			
			System.out.println();
		
		}
		
		System.out.println("DupTree delete test passed\n");
	}
	
	// add annotation
	@Test
	void test_insert() {
	
		//checking the bst property is maintained or not
		assertTrue(ordered(dtr));
		
		System.out.println("Testing DupTree insert ... ");
		
		System.out.println("Creating ArrayList iterator and Comparing elements pair-wise ...");
		
		//iterator for dup tree
		Iterator<Integer> dupTreeItr = dtr.iterator();
		
		//iterator for list
		Iterator<Integer> listItr = al.iterator();
		
		assertTrue(dupTreeItr.hasNext() && listItr.hasNext());
		
		// iterating the dup tree and list iterator
		while(dupTreeItr.hasNext() && listItr.hasNext()) {
		
			assertTrue(dupTreeItr.hasNext() && listItr.hasNext());
			assertTrue(dupTreeItr.next() == listItr.next());
		
		}
		
		System.out.println("... DupTree insert test passed\n");
	
	}
	
	public int get_count(DupTree tr, int v) {
		
		//returning count 0 if v is not present in tree
		if(tr.find(v) == null)
			{
				return 0;
			}
		
		return tr.find(v).get_count();
	}// to be filled in by you

	// checking bst property 
	public boolean ordered(Tree tr) {
	
		return (tr.left == null ||
				(tr.value > tr.left.max().value && ordered(tr.left))) 
				&&
				(tr.right == null ||
				tr.value < tr.right.min().value && ordered(tr.right));
	
	}
	
}