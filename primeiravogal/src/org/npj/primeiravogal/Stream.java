package org.npj.primeiravogal;

public interface Stream extends java.util.stream.Stream<Character>{
	public char getNext();
	public boolean hasNext();
}
