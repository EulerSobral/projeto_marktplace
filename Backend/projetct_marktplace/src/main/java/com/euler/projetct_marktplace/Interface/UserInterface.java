package com.euler.projetct_marktplace.Interface;
import java.util.Map;

public interface UserInterface {
    public Boolean register(String nome, String email,String password);
    public  Map<String, String> login(String email, String password);
}
