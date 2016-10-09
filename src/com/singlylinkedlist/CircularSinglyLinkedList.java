package com.singlylinkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

// TODO: unfinished
public class CircularSinglyLinkedList<T> implements Iterable<T> {

	private Node<T> head = new Node<T>(null);
	private Node<T> last = head;

	public CircularSinglyLinkedList(T... elements) {
		addAll(elements);
	}

	public void add(T element) {
		Node<T> newNode = new Node<T>(element);
		last.next = newNode;
		last = last.next;
		last.next = head;
	}

	public void addAll(T... elements) {
		for (T element : elements) {
			add(element);
		}
	}

	@Override
	public String toString() {
		Iterator<T> iterator = iterator();
		if (!iterator.hasNext()) {
			return "[]";
		}

		StringBuilder s = new StringBuilder("[").append(iterator.next());
		while (iterator.hasNext()) {
			s.append(" -> ").append(iterator.next());
		}
		return s.append(']').toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			Node<T> current = head;

			@Override
			public boolean hasNext() {
				return current.next != head;
			}

			@Override
			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				Node<T> temp = current;
				current = current.next;
				return temp.next.element;
			}

		};
	}

	private static class Node<T> {

		private Node<T> next;
		private T element;

		Node(T element) {
			this.element = element;
		}

		@Override
		public String toString() {
			return element.toString();
		}
	}
}
