//naive string matching algorithm
//time complexity O(n*m)

public class NaiveStringMatching {

public static void naiveStringMatching(String text, String pattern, int n, int m) {
    for (int i = 0; i <= n - m; i++) {
        int j;
        for (j = 0; j < m; j++) {
            if (text.charAt(i + j) != pattern.charAt(j)) {
                break;
            }
        }
        if (j == m) {
            System.out.println("Pattern found at index " + i);
            return;
        }
    }
}

    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";
        int n = text.length();
        int m = pattern.length();

        System.out.println("Text: " + text);
        System.out.println("Pattern: " + pattern);
        naiveStringMatching(text, pattern, n, m);
    }
    
}
