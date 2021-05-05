
package baseballapp;
//class to help format the file to make it look nice 
public class StringUtil {
    public static String pad(String s, String sep, int length) {
        if(s.length() < length){
            StringBuilder sb = new StringBuilder(s);
            while(sb.length() < length){
                sb.append(" ");
            } 
            sb.append(sep);//added field separator for report class - KJC 5/5
            return sb.toString();
        } else {
            return s.substring(0, length);
        }
    }
}
