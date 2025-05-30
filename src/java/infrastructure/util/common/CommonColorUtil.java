/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.util.common;

/**
 *
 * @author ricardo
 */
public class CommonColorUtil {

    private CommonColorUtil() {
    }

    public static String getTextColor(String colorHex) {

        int r =  Integer.parseInt(colorHex.substring(0,2),16);
        int g =  Integer.parseInt(colorHex.substring(2,4),16);
        int b =  Integer.parseInt(colorHex.substring(4,6),16);

	int yiq = ((r*299)+(g*587)+(b*114))/1000;
	return (yiq >= 128) ? "black" : "white";
    }
}
