// A2 PART 1 - GENERIC TREE ITERATORS

import java.util.*;

public class GenericIterators {

	public static void main(String[] args) {

		test1();
		System.out.println();
		test2();
		System.out.println();
		test3();
		System.out.println();
		test4();
		System.out.println();
		test5();
	}

// ============  DON'T MODIFY THE TEST METHODS.   ============

	static void test1() {

		AbsTree<Integer> set1 = new Tree<Integer>(100);
		set1.insert(150);
		set1.insert(125);
		set1.insert(50);
		set1.insert(50);
		set1.insert(25);
		set1.insert(126);
		set1.insert(75);
		set1.insert(76);
		set1.insert(150);
		set1.insert(125);
		set1.insert(151);
		set1.insert(200);

		AbsTree<Integer> set2 = new Tree<Integer>(100);
		set2.insert(50);
		set2.insert(50);
		set2.insert(25);
		set2.insert(75);
		set2.insert(75);
		set2.insert(150);
		set2.insert(125);
		set2.insert(200);
		set2.insert(100);

		System.out.print("set1 = ");
		print(set1);
		System.out.print("set2 = ");
		print(set2);

		if (contains(set1, set2))
			System.out.println("set1 contains set2.");
		else
			System.out.println("set1 does not contain set2.");
	}

	static void test2() {

		AbsTree<Integer> bag1 = new DupTree<Integer>(100);
		bag1.insert(150);
		bag1.insert(125);
		bag1.insert(50);
		bag1.insert(50);
		bag1.insert(50);
		bag1.insert(50);
		bag1.insert(50);
		bag1.insert(50);
		bag1.insert(25);
		bag1.insert(25);
		bag1.insert(25);
		bag1.insert(100);
		bag1.insert(75);
		bag1.insert(75);
		bag1.insert(75);
		bag1.insert(76);
		bag1.insert(150);
		bag1.insert(125);
		bag1.insert(200);

		AbsTree<Integer> bag2 = new DupTree<Integer>(100);
		bag2.insert(50);
		bag2.insert(50);
		bag1.insert(50);
		bag1.insert(50);
		bag1.insert(50);
		bag1.insert(50);
		bag2.insert(25);
		bag2.insert(25);
		bag2.insert(75);
		bag2.insert(75);
		bag2.insert(150);
		bag2.insert(125);
		bag2.insert(200);
		bag2.insert(100);

		System.out.print("bag1 = ");
		print(bag1);
		System.out.print("bag2 = ");
		print(bag2);

		if (contains(bag1, bag2))
			System.out.println("bag1 contains bag2.");
		else
			System.out.println("bag1 does not contain bag2.");
	}

	static void test3() {

		AbsTree<Integer> set1 = new Tree<Integer>(100);
		set1.insert(150);
		set1.insert(125);
		set1.insert(50);
		set1.insert(50);
		set1.insert(25);
		set1.insert(126);
		set1.insert(75);
		set1.insert(76);
		set1.insert(150);
		set1.insert(125);
		set1.insert(151);
		set1.insert(201);

		AbsTree<Integer> set2 = new Tree<Integer>(100);
		set2.insert(51);
		set2.insert(25);
		set2.insert(75);
		set2.insert(75);
		set2.insert(150);
		set2.insert(125);
		set2.insert(200);
		set2.insert(100);

		System.out.print("set1 = ");
		print(set1);
		System.out.print("set2 = ");
		print(set2);

		if (contains(set1, set2))
			System.out.println("set1 contains set2.");
		else
			System.out.println("set1 does not contain set2.");
	}

	static void test4() {

		AbsTree<Integer> bag1 = new DupTree<Integer>(100);
		bag1.insert(150);
		bag1.insert(125);
		bag1.insert(50);
		bag1.insert(50);
		bag1.insert(26);
		bag1.insert(25);
		bag1.insert(27);
		bag1.insert(75);
		bag1.insert(75);
		bag1.insert(76);
		bag1.insert(150);
		bag1.insert(125);
		bag1.insert(200);

		AbsTree<Integer> bag2 = new DupTree<Integer>(100);
		bag2.insert(50);
		bag2.insert(50);
		bag2.insert(25);
		bag2.insert(75);
		bag2.insert(75);
		bag2.insert(150);
		bag2.insert(125);
		bag2.insert(200);
		bag2.insert(100);

		System.out.print("bag1 = ");
		print(bag1);
		System.out.print("bag2 = ");
		print(bag2);

		if (contains(bag1, bag2))
			System.out.println("bag1 contains bag2.");
		else
			System.out.println("bag1 does not contain bag2.");
	}

	static void test5() {

		AbsTree<Integer> set1 = new Tree<Integer>(1000);
		AbsTree<Integer> set2 = new Tree<Integer>(500);
		set2.insert(1000);

		System.out.print("set1 = ");
		print(set1);
		System.out.print("set2 = ");
		print(set2);

		if (contains(set1, set2))
			System.out.println("set1 contains set2.");
		else
			System.out.println("set1 does not contain set2.");
	}

	
	static void print(AbsTree<Integer> bs) {
		System.out.print("{ ");
		for (int x : bs)
			System.out.print(x + " ");
		System.out.println("}");
	}


// The 'contains' method should work for trees and duptrees.

	static <T extends Comparable<T>> boolean contains(AbsTree<T> tr1, AbsTree<T> tr2) {
		Iterator<T> iter1 = tr1.iterator();
		Iterator<T> iter2 = tr2.iterator();
		while (iter2.hasNext()) {
			T x = iter2.next();
			boolean flag = false;
			while (iter1.hasNext()) {
				T y = iter1.next();
				if (x.compareTo(y) == 0) {
					System.out.println(x + " = " + y);
					flag = true;
					break;
				}
				if (x.compareTo(y) > 0) {
					System.out.println(x + " > " + y);
				} else {
					System.out.println(x + " < " + y);
					return false;
				}
			}
			if (!flag)
				return false;
		}
		return true;
	}

//========= GENERIC ABSTREE, TREE, AND DUPTREE (DON'T MODIFY THESE CLASSES) ======

abstract class AbsTree<T extends Comparable<T>> implements Iterable<T> {

	public AbsTree(T v) {
		value = v;
		left = null;
		right = null;
	}

	public void insert(T v) {
		if (value.compareTo(v) == 0)
			count_duplicates();
		if (value.compareTo(v) > 0)
			if (left == null)
				left = add_node(v);
			else
				left.insert(v);
		else if (value.compareTo(v) < 0)
			if (right == null)
				right = add_node(v);
			else
				right.insert(v);
	}

	public Iterator<T> iterator() {
		return create_iterator();
	}

	protected abstract AbsTree<T> add_node(T n);

	protected abstract void count_duplicates();

	protected abstract int get_count();

	protected abstract Iterator<T> create_iterator();

	protected T value;
	protected AbsTree<T> left;
	protected AbsTree<T> right;
}

class Tree<T extends Comparable<T>> extends AbsTree<T> {
	public Tree(T n) {
		super(n);
	}

	public Iterator<T> create_iterator() {
		return new TreeIterator<T>(this);
	}

	protected AbsTree<T> add_node(T n) {
		return new Tree<T>(n);
	}

	protected void count_duplicates() {
		;
	}

	protected int get_count() {
		return 1;
	}
}

class DupTree<T extends Comparable<T>> extends AbsTree<T> {
	public DupTree(T n) {
		super(n);
		count = 1;
	};

	public Iterator<T> create_iterator() {
		return new DupTreeIterator<T>(this);
	}

	protected AbsTree<T> add_node(T n) {
		return new DupTree<T>(n);
	}

	protected void count_duplicates() {
		count++;
	}

	protected int get_count() {
		return count;
	}

	protected int count;
}

// ====================  GENERIC TREE ITERATORS  ========================

class AbsTreeIterator<T extends Comparable<T>> implements Iterator<T> {

	public AbsTreeIterator(AbsTree<T> root) {
		stack_tree_nodes(root);
	}

	public boolean hasNext() {
		return !stack.isEmpty() || count > 0;
	}

	public T next() {
		if (count == 0) {
			AbsTree<T> node = stack.pop();
			value = node.value;
			count = node.get_count();
			stack_tree_nodes(node.right);
		}
		count--;
		return value;
	}

	private void stack_tree_nodes(AbsTree<T> node) {
		if (node == null)
			return;
		stack.push(node);
		while (node.left != null) {
			stack.push(node.left);
			node = node.left;
		}
	}

	private Stack<AbsTree<T>> stack = new Stack<AbsTree<T>>();
	private AbsTree<T> node;
	private T value;
	private int count = 0;
}

class TreeIterator<T extends Comparable<T>> extends AbsTreeIterator<T> {
	public TreeIterator(AbsTree<T> t) {
		super(t);
	}
}

class DupTreeIterator<T extends Comparable<T>> extends AbsTreeIterator<T> {
	public DupTreeIterator(AbsTree<T> t) {
		super(t);
	}
}
