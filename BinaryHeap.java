package lab1;

import java.util.Arrays;
import java.util.Scanner;

/*
 * @(#)TextGraphProject --- BinaryHeap.java 
 */

/**
 * @author 何涛
 * @version 1st on 2017年9月9日
 */
public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {
	private static final int DEFAULT_CAPACITY = 10; // Default initial capacity
	private AnyType[] Elements;
	private int Size;
	private int Capacity;

	@SuppressWarnings("unchecked")
	public BinaryHeap() {
		Elements = (AnyType[]) new Comparable[DEFAULT_CAPACITY];
		Capacity = Elements.length;
		clear();
	}

	@SuppressWarnings("unchecked")
	public BinaryHeap(int capacity) {
		if (capacity < 0)
			throw new IllegalArgumentException("Illegal argument capacity: " + capacity);
		else {
			Elements = (AnyType[]) new Comparable[DEFAULT_CAPACITY];
		}
		Capacity = Elements.length;
		clear();
	}

	public void clear() {
		Size = 0;
	}

	public boolean isEmpty() {
		return Size == 0;
	}

	public AnyType findMin() {
		if (Size == 0)
			throw new IllegalStateException();
		return Elements[1];
	}

	public void insert(AnyType elem) {
		if (Size >= Capacity - 1)
			enLargeArray(Size * 2 + 1);

		int hole = ++Size;
		for (Elements[0] = elem; elem.compareTo(Elements[hole / 2]) < 0; hole /= 2)
			Elements[hole] = Elements[hole / 2];
		Elements[hole] = elem;
	}

	private void enLargeArray(int newSize) {
		if (newSize < Size)
			throw new IllegalArgumentException("Illeagl argument newSize: " + newSize);
		Capacity = newSize;
		Elements = Arrays.copyOf(Elements, newSize);
	}

	public AnyType deleteMin() {
		if (isEmpty())
			throw new IllegalStateException();

		AnyType Min = findMin();
		Elements[1] = Elements[Size--];
		percolateDown(1);

		return Min;
	}

	private void percolateDown(int hole) {
		int child;
		AnyType tmp = Elements[hole];

		for (; hole * 2 <= Size; hole = child) {
			child = hole * 2;
			if (child != Size && Elements[child].compareTo(Elements[child + 1]) > 0)
				child++;
			if (tmp.compareTo(Elements[child]) > 0)
				Elements[hole] = Elements[child];
			else
				break;
		}
		Elements[hole] = tmp;
	}

}
