package de.unisb.prog.mips.assembler.segments;

import java.util.Iterator;

public abstract class ListItem<T extends ListItem<T, D>, D extends T> {

	private T next;
	private T prev;

	protected abstract T me();

	protected ListItem() {
		this.next = this.prev = me();
	}

	public final T remove() {
		this.prev.next = this.next;
		this.next.prev = this.prev;
		this.next = this.prev = me();
		return me();
	}

	public final T prepend(T x) {
		this.prev.next = x;
		x.prev = this.prev;
		x.next = me();
		this.prev = x;
		return x;
	}

	public final T append(T x) {
		this.next.prev = x;
		x.prev = me();
		x.next = this.next;
		this.next = x;
		return x;
	}

	public final void replaceByList(D x) {
		this.prev.next = x.next;
		x.next.prev = this.prev;
		this.next.prev = x.prev;
		x.prev.next = this.next;
		this.next = this.prev = me();
	}

	public final T next() {
		return this.next;
	}

	public final T prev() {
		return this.prev;
	}

	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private T curr = me();

			public boolean hasNext() {
				return this.curr.next != me();
			}

			public T next() {
				this.curr = this.curr.next;
				return this.curr;
			}

			public void remove() {
				this.curr.remove();
			}
		};
	}



}
