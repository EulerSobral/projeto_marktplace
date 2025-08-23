package com.euler.projetct_marktplace.Util;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    private String nome;
    private String email;
    private String senha;  // armazenaremos a senha criptografada
}

