package com.fuctura.bibliotecaNoite.repositories;

import com.fuctura.bibliotecaNoite.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query(value = "SELECT obj FROM Livro obj WHERE obj.categoria.id =:id_cat")
    List<Livro> findAllLivroByCategoria(@Param(value = "id_cat") Long id_cat);

    List<Livro> findByCategoriaNomeContainingIgnoreCase(String nome);
}
