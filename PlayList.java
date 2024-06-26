package prototype;

class Node {
    String song;
    Node next;

    Node(String song) {
        this.song = song;
        this.next = null;
    }
}

public class PlayList {
    public static void playSongs(char K, int N, Node head) {
        Node current = head;

        while (current != null) {
            if (current.song.startsWith(String.valueOf(K))) {
                for (int i = 0; i < N; i++) {
                    System.out.println(current.song);
                }
            } else {
                System.out.println(current.song);
            }

            current = current.next;
        }
    }

    public static void main(String[] args) {
        // Example playlist: "umbrella", "toxic", "firework", "blinding light", "hai ya", "uptown Funk", "hello"
        Node head = new Node("umbrella");
        head.next = new Node("toxic");
        head.next.next = new Node("firework");
        head.next.next.next = new Node("blinding light");
        head.next.next.next.next = new Node("hai ya");
        head.next.next.next.next.next = new Node("uptown Funk");
        head.next.next.next.next.next.next = new Node("hello");

        char K = 't';
        int N = 4;

        playSongs(K, N, head);
    }
}
