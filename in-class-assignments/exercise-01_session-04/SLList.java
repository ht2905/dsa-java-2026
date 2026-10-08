public class SLList {
    private static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }
    private IntNode s;
    private int first;

    public SLList(int first) {
        s = new IntNode(first, null);
    }

    public void addFirst(int x) {
        s.next = new IntNode(x, s.next);
    }

    public void display() {
        IntNode p = s.next;
        while (p != null) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        SLList list = new SLList(0);

        list.addFirst(40);
        list.addFirst(30);
        list.addFirst(20);
        list.addFirst(10);
        list.display();
    }
}