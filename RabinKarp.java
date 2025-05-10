// Time Complexit of Rabin-Karp Algorithm: O(n + m), where n is the length of the text and m is the length of the pattern
import java.util.*;
public class RabinKarp {
    static final int d = 256; // number of characters in the input alphabet
    public static void rabinKarp(String pattern, String text, int q){
        int m = pattern.length();
        int n = text.length();
        int p=0; // hash value for pattern
        int t=0; // hash value for text
        int h=1;

        // The value of h would be "pow(d, m-1)%q"
        for (int i = 0; i < m-1; i++) {
            h= (h*d)%q;
        }

//compute initial hash values for pattern and first window of text
for (int i = 0; i < m; i++) {
    p= (d*p + pattern.charAt(i))%q;
    t= (d*t + text.charAt(i))%q;
    
}

//slide the pattern over text one by one
for (int i = 0; i <= n-m; i++) {
    if (p==t){
        boolean match = true;
        for (int j = 0; j < m; j++) {
            if (text.charAt(i+j)!=pattern.charAt(j)){
                match=false;
                break;
            }
            
        }

        if (match){
            System.out.println("Pattern found at index " + i);
        }

    }
    //compute hash value for next window of text
    if (i<n-m){
        t= (d*(t-text.charAt(i)*h) + text.charAt(i+m))%q;
        if (t<0){
            t=t+q;
        }
    }
    
}

    }
    public static void main(String[] args) {
        String text = "AABAACAADAABAABA";
        String pattern = "AABA";
        int q = 101; // A prime number
        System.out.println("Text: " + text);
        System.out.println("Pattern: " + pattern);
        rabinKarp( pattern,text, q);
    }
}