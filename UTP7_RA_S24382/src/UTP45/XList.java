package UTP45;

import java.util.*;

public class XList<E> implements List<E> {
    private ArrayList<E> dataList;

    //Creators--------------------------------------
    public XList() {
        dataList = new ArrayList<>();
    }

    public XList(Collection<E> collection) {
        this.dataList = new ArrayList(collection);
    }

    public XList(E... elements) {
        dataList = new ArrayList<>();
        Collections.addAll(dataList, elements);
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
            xList.getDataList().add(String.valueOf(input.charAt(i)));
        return xList;
    }

    public static XList<String> tokensOf(String input, String... separators) {
        XList<String> xList = new XList<>();
        String[] values = null;
        if (separators.length == 0) {
            values = input.split(" ");
            for (String val : values)
                xList.getDataList().add(val);
        } else
            for (String separator : separators) {
                values = input.split(separator);
                for (String val : values)
                    xList.getDataList().add(val);
            }
        return xList;
    }

    //Functions--------------------------------------
    public XList<E> union(Collection<? extends E> collection) {
        XList<E> result = new XList<>();
        result.addAll(dataList);
        result.addAll(collection);
        return result;
    }

    public XList<E> union(E... elements) {
        XList<E> result = new XList<>();
        result.addAll(dataList);
        result.addAll(Arrays.asList(elements));
        return result;
    }

    public XList<E> diff(Collection<? extends E> collection) {
        XList<E> result = new XList<>();
        for (E element : dataList) {
            if (!collection.contains(element))
                result.add(element);
        }
        return result;
    }

    public XList<E> unique() {
        XList<E> result = new XList<>();
        for (E element : dataList) {
            if (!result.contains(element)){
                result.add(element);
            }
        }
        return result;
    }

    public XList<XList<E>> combine(){
        XList<XList<E>>
        ArrayList<List> output = new ArrayList<>();
        int numberOfActs = 1; //how many combinations can we create
        int[] lengths = new int[input.size()], indexes = new int[input.size()];
        for (int i = 0; i < lengths.length; i++) {
            lengths[i] = input.get(i).size();
            indexes[i] = 0; //setting start indexes at first
            numberOfActs *= lengths[i]; //it's just all the collections lengths multiply
        }
        for (int act = 0; act < numberOfActs; act++) {
            ArrayList toAdd = new ArrayList();
            for (int i = 0; i < indexes.length; i++) {
                toAdd.add(input.get(i).get(indexes[i]));
                System.out.print(input.get(i).get(indexes[i]));
            }
            output.add(toAdd);
            System.out.println();
            indexes[0]++;
            for (int i = 1; i < indexes.length; i++)
                if (indexes[i - 1] == lengths[i - 1]) {
                    indexes[i - 1] = 0;
                    indexes[i]++;
                }
        }
        return output;
    }

    //Implementations-------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return dataList.toString();
    }

    @Override
    public int size() {
        return dataList.size();
    }

    @Override
    public boolean isEmpty() {
        return dataList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return dataList.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return dataList.iterator();
    }

    @Override
    public Object[] toArray() {
        return dataList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return dataList.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return dataList.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return dataList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return dataList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return dataList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return dataList.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return dataList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return dataList.retainAll(c);
    }

    @Override
    public void clear() {
        dataList.clear();
    }

    @Override
    public boolean equals(Object o) {
        return dataList.equals(o);
    }

    @Override
    public int hashCode() {
        return dataList.hashCode();
    }

    @Override
    public E get(int index) {
        return dataList.get(index);
    }

    @Override
    public E set(int index, E element) {
        return dataList.set(index, element);
    }

    @Override
    public void add(int index, E element) {
        dataList.add(index, element);
    }

    @Override
    public E remove(int index) {
        return dataList.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return dataList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return dataList.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return dataList.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return dataList.listIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return dataList.subList(fromIndex, toIndex);
    }

    //Getters/Setters-------------------------------
    public List<E> getDataList() {
        return dataList;
    }
}