package uutils;

import java.util.UUID;

//Éú³É¼¤»îÂë
public class uuidutils {
public static String getuuid(){
	return UUID.randomUUID().toString().replace("-", "");
}
}
