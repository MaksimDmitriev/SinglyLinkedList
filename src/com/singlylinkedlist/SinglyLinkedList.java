package com.singlylinkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements Iterable<T> {

	private Node<T> head = new Node<T>(null);
	private Node<T> last = head;
	private int size;
	private int modCount;

	public SinglyLinkedList() {
	}

	public SinglyLinkedList(T... elements) {
		if (elements == null) {
			throw new NullPointerException();
		}
		addAll(elements);
	}

	public void add(T element) {
		Node<T> newNode = new Node<T>(element);
		last.next = newNode;
		last = last.next;
		size++;
		modCount++;
	}

	public void addAll(T... elements) {
		for (T element : elements) {
			add(element);
		}
	}

	public T[] toArray() {
		Object[] array = new Object[size];
		Iterator<T> iterator = iterator();
		int i = 0;
		while (iterator.hasNext()) {
			array[i++] = iterator.next();
		}
		return (T[]) array;
	}

	public void reverse() {
		if (size < 2) {
			return;
		}
		Node<T> prev = head;
		last = head.next;
		Node<T> cur = prev.next;
		prev = null;
		Node<T> next;
		while (cur != null) {
			next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		head.next = prev;
	}

	public void reversePairs() {
		if (size < 2) {
			return;
		}
		// http://codereview.stackexchange.com/a/109011/23821
		Node<T> prev = this.head, cur, next;
		while ((cur = prev.next) != null && (next = cur.next) != null) {
			prev.next = next;
			cur.next = next.next;
			next.next = cur;
			prev = cur;
			last = (prev.next == null) ? prev : prev.next;
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

			int expectedModCount = modCount;
			Node<T> current = head;

			@Override
			public boolean hasNext() {
				return current.next != null;
			}

			@Override
			public T next() {
				checkForComodification();
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				Node<T> temp = current;
				current = current.next;
				return temp.next.element;
			}

			void checkForComodification() {
				if (modCount != expectedModCount)
					throw new ConcurrentModificationException();
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
