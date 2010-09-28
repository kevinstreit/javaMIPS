package de.unisb.prog.mips.assembler.segments;

import java.util.Iterator;

public abstract class ListItem<T extends ListItem<T, D>, D extends T> {
	
	private T next;
	private T prev;
	
	protected abstract T me();
	
	protected ListItem() {
		next = prev = me();
	}
	
	public final T remove() {
		prev.next = next;
		next.prev = prev;
		next = prev = me();
		return me();
	}
	
	public final T prepend(T x) {
		prev.next = x;
		x.prev = prev;
		x.next = me();
		prev = x;
		return x;
	}
	
	public final T append(T x) {
		next.prev = x;
		x.prev = me();
		x.next = next;
		next = x;
		return x;
	}
	
	public final void replaceByList(D x) {
		prev.next = x.next;
		x.next.prev = prev;
		next.prev = x.prev;
		x.prev.next = next;
		next = prev = me();
	}
	
	public final T next() {
		return next;
	}
	
	public final T prev() {
		return prev;
	}

	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private T curr = me();

			@Override
			public boolean hasNext() {
				return curr.next != me();
			}

			@Override
			public T next() {
				curr = curr.next;
				return curr;
			}

			@Override
			public void remove() {
				curr.remove();
			}
		};
	}
	
	

}
