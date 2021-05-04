
package baseballapp;
//class to help format the file to make it look nice 
public class StringUtil {
    public static String pad(String s, int length) {
        if(s.length() < length){
            StringBuilder sb = new StringBuilder(s);
            while(sb.length() < length){
                sb.append(" ");
            } 
            return sb.toString();
        } else {
            return s.substring(0, length);
        }
    }
}
