package co.edu.unal.unacompaamiento.Utils;

public class Apis {

    public static final String URL_001 = "http://192.168.0.14:8080/";

    public static UserService getUserService(){
        return Cliente.getCliente(URL_001).create(UserService.class);
    }

}
