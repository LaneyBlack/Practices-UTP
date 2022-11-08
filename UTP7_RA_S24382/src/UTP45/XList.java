package UTP45;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class XList<E> implements List<E> {
    private ArrayList<E> data; //could have created my own list, but it's just a studies example

    //Creators--------------------------------------
    public XList() {
        data = new ArrayList<>();
    }

    public XList(Collection<E> collection) {
        this.data = new ArrayList<>(collection);
    }

    public XList(E... elements) {
        data = new ArrayList<>();
        Collections.addAll(data, elements);
    }

    public static <T> XList of(T... elements) {
        ArrayList<T> list = new ArrayList<>();
        list.addAll(Arrays.asList(elements));
        return new XList<T>(list);
    }

    public static <T> XList of(Collection<T> collection) {
        return new XList<T>(new ArrayList<>(collection));
    }

    public static XList<String> charsOf(String input) {
        XList<String> xList = new XList<>();
        for (int i = 0; i < input.length(); i++)
            xList.getData().add(String.valueOf(input.charAt(i)));
        return xList;
    }

    public static XList<String> tokensOf(String input, String... separators) {
        XList<String> xList = new XList<>();
        String[] values = null;
        if (separators.length == 0) {
            values = input.split(" ");
            for (String val : values)
                xList.getData().add(val);
        } else
            for (String separator : separators) {
                values = input.split(separator);
                for (String val : values)
                    xList.getData().add(val);
            }
        return xList;
    }

    //Functions--------------------------------------
    public XList<E> union(Collection<? extends E> collection) {
        XList<E> result = new XList<>();
        result.addAll(data);
        result.addAll(collection);
        return result;
    }

    public XList<E> union(E... elements) {
        XList<E> result = new XList<>();
        result.addAll(data);
        result.addAll(Arrays.asList(elements));
        return result;
    }

    public XList<E> diff(Collection<? extends E> collection) {
        XList<E> result = new XList<>();
        for (E element : data) {
            if (!collection.contains(element))
                result.add(element);
        }
        return result;
    }

    public XList<E> unique() {
        XList<E> result = new XList<>();
        for (E element : data) {
            if (!result.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }

    public XList<XList<E>> combine() {
        List<List<E>> input = new ArrayList<>((Collection<? extends List<E>>) this);
        XList<XList<E>> output = new XList<>();
        int numberOfActs = 1; //how many combinations can we create
        int[] borders = new int[input.size()], indexes = new int[input.size()];
        for (int i = 0; i < borders.length; i++) {
            borders[i] = input.get(i).size();
            indexes[i] = 0; //setting start indexes at first
            numberOfActs *= borders[i]; //it's just all the collections lengths multiply
        }
        for (int act = 0; act < numberOfActs; act++) {
            ArrayList<E> toAdd = new ArrayList<>();
            for (int i = 0; i < indexes.length; i++)
                toAdd.add(input.get(i).get(indexes[i]));
            output.add(XList.of(toAdd));
            indexes[0]++;
            for (int i = 1; i < indexes.length; i++)
                if (indexes[i - 1] == borders[i - 1]) {
                    indexes[i - 1] = 0;
                    indexes[i]++;
                }
        }
        return output;
    }

    public <R> XList<R> collect(Function<E, R> function) {
        ArrayList<R> result = new ArrayList<>();
        for (E element : data)
            result.add(function.apply(element));
        return XList.of(result);
    }

    public String join(String... separators) {
        StringBuilder result = new StringBuilder();
        for (int i=0; i<data.size();i++) {
            result.append(data.get(i));
            if (separators.length > 0 && i!= data.size()-1 )
                for (String sep : separators)
                    result.append(sep);
        }
        return result.toString();
    }

    public void forEachWithIndex(BiConsumer<E, Integer> consumer){
        for (int i = 0; i < data.size(); i++) {
            consumer.accept(data.get(i), i);
        }
    }

    //Implementations-------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return data.toString();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return data.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return data.iterator();
    }

    @Override
    public Object[] toArray() {
        return data.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return data.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return data.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return data.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return data.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return data.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return data.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return data.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return data.retainAll(c);
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public boolean equals(Object o) {
        return data.equals(o);
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }

    @Override
    public E get(int index) {
        return data.get(index);
    }

    @Override
    public E set(int index, E element) {
        return data.set(index, element);
    }

    @Override
    public void add(int index, E element) {
        data.add(index, element);
    }

    @Override
    public E remove(int index) {
        return data.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return data.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return data.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return data.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return data.listIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return data.subList(fromIndex, toIndex);
    }

    //Getters/Setters-------------------------------
    public List<E> getData() {
        return data;
    }
}