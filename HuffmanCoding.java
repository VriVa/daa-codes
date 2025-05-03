// Greedy Algorithm
//Time Complexity : O(n log n), best case O(n)
import java.util.*;
class Node {
    char ch;
    int freq;
    Node left, right;

    Node(char ch, int freq){
        this.ch = ch;
        this.freq= freq;
    }

    Node(int freq, Node left, Node right){
        this.ch = '\0'; // null character for internal nodes
        this.freq= freq;        
        this.left= left;    
        this.right= right;
    }

}
public class HuffmanCoding {
    public static Map<Character, String > huffmanMap= new HashMap<>();

    //build the tree
    public static Node buildHuffmanTree(char []chars, int [] freqs){
        PriorityQueue<Node> pq= new PriorityQueue<>(Comparator.comparingInt(n->n.freq));

        for (int i = 0; i < chars.length; i++) {
            pq.add(new Node(chars[i], freqs[i]));
            
        }

        while(pq.size()>1){
            Node left= pq.poll();
            Node right= pq.poll();

           pq.add(new Node (left.freq + right.freq, left, right));
        }
        return pq.poll();
    }

    //generate huffman codes
    public static void generateCodes(Node root, String code){
        if (root==null){
            return;
        }

        if (root.left== null && root.right==null){
            huffmanMap.put(root.ch, code);
            return;
        }
        generateCodes(root.left, code + "0");
        generateCodes(root.right, code + "1");
    }

    //encode a string
    public static String encode(String str){
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch= str.charAt(i);
            encoded.append(huffmanMap.get(ch));
        }
        return encoded.toString();
    }

    //print huffman codes
    public static void printHuffmanCodes(){
        for (Map.Entry<Character, String> entry : huffmanMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }


    public static void main(String[] args) {
        //input data
        char [] chars = {'a', 'b', 'c', 'd', 'e', 'f'};
        int [] freqs = {5, 9, 12, 13, 16, 45};

        Node root = buildHuffmanTree(chars,freqs);

        generateCodes(root,"");

        System.out.println("Huffman Codes for characters:");
        printHuffmanCodes();

        //encode a string
        String str= "abcdef";
        String encoded= encode(str);

        System.out.println("Original String: " + str);
        System.out.println("Encoded String: " + encoded);
        
    }
    
}
