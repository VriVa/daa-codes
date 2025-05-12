//time complexity O(n+m), where n is the length of the text and m is the length of the pattern

public class KMP {

    public static void KMPsearch(String pattern, String text){
        int m= pattern.length();
        int n= text.length();
        int [] lps= computeLPSarray(pattern);

        int i= 0; // index for text
        int j=0;  // index for pattern

        while (i<n){
            if (pattern.charAt(j)==text.charAt(i)){
                i++;
                j++;
            }
            if (j==m){
                System.out.println("Pattern found at index " + (i-j));
                j= lps[j-1];
            }
            else if (i<n && pattern.charAt(j)!= text.charAt(i)){
                if (j!=0){
                    j= lps[j-1];
                }
                else{
                    i++;
                }
            }
            }
        }

     static int [] computeLPSarray(String pattern) {
            int m = pattern.length();
            int [] lps = new int[m];
            int length=0;
            int i=1;

            while (i<m){
                if (pattern.charAt(i)==pattern.charAt(length)){
                    length++;
                    lps[i]=length;
                    i++;
                }
                else{
                    if (length!=0){
                        length= lps[length-1];
                    }
                    else{
                        lps[i]=0;
                        i++;
                    }
                }
            }

            return lps;

    }
      



    public static void main(String[] args) {
      String text = "AABAACAADAABAABA";
       String pattern = "AABA";
       KMPsearch(pattern, text);  
    }
    
}
