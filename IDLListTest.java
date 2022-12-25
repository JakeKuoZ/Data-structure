/**
 * @author Kuo Zhang
 */
class IDLListTest {

    public static void main(String[] args) {
        // create an empty indexed double linked list, data type is Integer
        IDLList<Integer> dList = new IDLList<>();
//all testing case


        try {
            dList.add(0, 5);
            dList.add(0, 1);
            dList.add(1, 15);
            dList.add(2, 10);
            System.out.println("Element added to the list: ");
            System.out.println(dList);
            dList.add(8);
            System.out.println("Add 8");
            System.out.println(dList);
            dList.append(7);
            System.out.println("Append 7");
            System.out.println(dList);
            System.out.println("Get element index as 1");
            System.out.println(dList.get(1));
            System.out.println("Get head element");
            System.out.println(dList.getHead());
            System.out.println("Get tail element");
            System.out.println(dList.getLast());
            System.out.println("Get list size");
            System.out.println(dList.size());
            System.out.println("Remove element at 1");
            System.out.println("Following with size");
            dList.removeAt(1);
            System.out.println(dList);
            System.out.println(dList.size());
            System.out.println("Remove head element");
            dList.remove();
            System.out.println(dList);
            System.out.println("Remove tail element");
            dList.removeLast();
            System.out.println(dList);
            System.out.println("Remove provided element in this case 15");
            System.out.println(("Follow by size."));
            dList.remove(15);
            System.out.println(dList);
            System.out.println(dList.size());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}