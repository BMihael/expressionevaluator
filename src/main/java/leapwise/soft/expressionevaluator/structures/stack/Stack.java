package leapwise.soft.expressionevaluator.structures.stack;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;
import java.util.stream.Collectors;

/** author: Somewhere on google */
public class Stack<T> {

  private static final int DEFAULT_CAPACITY = 1;
  private T[] stackArray;
  private int size;

  public Stack() {
    stackArray = (T[])new Object[DEFAULT_CAPACITY];
    size = 0;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public void push(T data) {
    ensureCapacity();
    stackArray[size++] = data;
  }

  public Object pop() {
    if (isEmpty()) {
      throw new EmptyStackException();
    }
    T data = stackArray[--size];
    stackArray[size] = null;
    return data;
  }

  public Object peek() {
    if (isEmpty()) {
      throw new EmptyStackException();
    }
    return stackArray[size - 1];
  }

  private void ensureCapacity() {
    if (size == stackArray.length) {
      int newCapacity = stackArray.length * 2;
      T[] newArray = (T[])new Object[newCapacity];
      System.arraycopy(stackArray, 0, newArray, 0, size);
      stackArray =  newArray;
    }
  }

  public List<T> getStackArray(){
    return Arrays.stream(stackArray).collect(Collectors.toList());
  }
}

/*
Old implementation:


public class Stack {

    private int maxSize;
    public Object[] stackArray;
    private int top;

    public Stack(int size) {
        maxSize = size;
        stackArray = new Object[maxSize];
        top = -1;
    }

    public void push(Object value) {
        if (isFull()) {
            System.out.println("Stack is full. Cannot push element.");
            return;
        }
        top++;
        stackArray[top] = value;
    }

    public Object pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty. Cannot pop element.");
            return -1;
        }
        int oldTop = top;
        top--;
        stackArray[oldTop] = null;
        return stackArray[oldTop];
    }

    public Object peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty. Cannot peek element.");
            return -1;
        }
        return stackArray[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }

    @Override
    public String toString() {
        return "Stack: " + Arrays.toString(stackArray);
    }
}
*/
