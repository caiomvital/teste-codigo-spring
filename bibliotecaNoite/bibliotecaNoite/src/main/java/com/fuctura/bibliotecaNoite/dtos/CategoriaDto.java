package com.fuctura.bibliotecaNoite.dtos;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class CategoriaDto {

    private Long id;

    @NotBlank(message = "O campo NOME é obrigatório")
    @Length(min = 3, max = 15, message = "Deve conter entre 3 e 15 caracteres")
    private String nome;

    @NotBlank(message = "O campo DESCRIÇÃO é obrigatório")
    @Length(min = 10, max = 100, message = "Deve conter entre 10 e 100 caracteres")
    private String descricao;


    public CategoriaDto() {
    }

    public CategoriaDto(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}
