package uutils;

import java.util.UUID;

//���ɼ�����
public class uuidutils {
public static String getuuid(){
	return UUID.randomUUID().toString().replace("-", "");
}
}
