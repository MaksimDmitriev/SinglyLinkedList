package com.singlylinkedlist;

import org.junit.Assert;
import org.junit.Test;

public class SinglyLinkedListTest {

	@Test
	public void testReverseInPairsEvenNumberOfElements() {
		SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<>();
		linkedList.addAll(1, 2, 3, 4, 5, 6);
		linkedList.reversePairs();

		Assert.assertArrayEquals(new Integer[] { 2, 1, 4, 3, 6, 5 }, linkedList.toArray());
	}

	@Test
	public void testReverseInPairsOddNumberOfElements() {
		SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<>();
		linkedList.addAll(1, 2, 3, 4, 5);
		linkedList.reversePairs();

		Assert.assertArrayEquals(new Integer[] { 2, 1, 4, 3, 5 }, linkedList.toArray());
	}

	@Test
	public void testReverseInPairsSingle() {
		SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<>(1);
		linkedList.reversePairs();
		Assert.assertArrayEquals(new Integer[] { 1 }, linkedList.toArray());
	}

	@Test
	public void testReverseInPairsEmpty() {
		SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<>();
		linkedList.reversePairs();
		Assert.assertArrayEquals(new Integer[] {}, linkedList.toArray());
	}

	@Test(expected = NullPointerException.class)
	public void testReverseInPairsNull() {
		SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<>(null);
	}
}
