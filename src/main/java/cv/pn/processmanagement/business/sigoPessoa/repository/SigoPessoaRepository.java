package cv.pn.processmanagement.business.sigoPessoa.repository;

import cv.pn.processmanagement.business.sigoPessoa.model.SigoPessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SigoPessoaRepository extends JpaRepository<SigoPessoa, Long> {


    @Query("""
SELECT DISTINCT p2.oracleId
FROM SigoPessoa p1
JOIN SigoPessoa p2
  ON lower(trim(p1.nome)) = lower(trim(p2.nome))
 AND (
        (p1.dtNascimento IS NOT NULL AND p1.dtNascimento = p2.dtNascimento)
     OR (p1.num IS NOT NULL AND p1.num = p2.num)
     OR (
            p1.pai IS NOT NULL AND p2.pai IS NOT NULL
        AND p1.mae IS NOT NULL AND p2.mae IS NOT NULL
        AND lower(trim(p1.pai)) = lower(trim(p2.pai))
        AND lower(trim(p1.mae)) = lower(trim(p2.mae))
     )
 )
WHERE p1.oracleId = :oracleId
""")
    List<Long> buscarOracleIdsEquivalentes(@Param("oracleId") Long oracleId);
//isto estava funcionando bem
/*
    @Query("""
        SELECT p
        FROM SigoPessoa p
        WHERE lower(trim(p.nome)) LIKE lower(concat('%', :nome, '%'))
        ORDER BY p.nome
    """)
    List<SigoPessoa> buscarPorNome(@Param("nome") String nome);*/




  /*  @Query("""
    SELECT p
    FROM SigoPessoa p
    WHERE (:nome IS NULL OR lower(trim(p.nome)) LIKE lower(concat('%', :nome, '%')))
      AND (:num IS NULL OR trim(p.num) = trim(:num))
      AND (:pai IS NULL OR lower(trim(p.pai)) LIKE lower(concat('%', :pai, '%')))
      AND (:mae IS NULL OR lower(trim(p.mae)) LIKE lower(concat('%', :mae, '%')))
      AND (:dtNasc IS NULL OR DATE(p.dtNascimento) = :dtNasc)
    ORDER BY p.nome
""")
    List<SigoPessoa> buscarPorFiltro(
            @Param("nome") String nome,
            @Param("num") String num,
            @Param("pai") String pai,
            @Param("mae") String mae,
            @Param("dtNasc") Date dtNasc
    );*/

//troquei porque nao acitava not null no bd
/*    @Query("""
SELECT p
FROM SigoPessoa p
WHERE lower(p.nome) LIKE lower(concat('%', COALESCE(:nome, p.nome), '%'))
  AND (COALESCE(:num, p.num) = p.num)
  AND lower(p.pai) LIKE lower(concat('%', COALESCE(:pai, p.pai), '%'))
  AND lower(p.mae) LIKE lower(concat('%', COALESCE(:mae, p.mae), '%'))
  AND (COALESCE(:dtNasc, p.dtNascimento) = p.dtNascimento)
ORDER BY p.nome
""")*/

    // com data
    @Query("""
    SELECT p
    FROM SigoPessoa p
    WHERE (:nome IS NULL OR lower(trim(p.nome)) LIKE lower(concat('%', :nome, '%')))
      AND (:num IS NULL OR trim(p.num) LIKE concat('%', :num, '%'))
      AND (:pai IS NULL OR lower(trim(p.pai)) LIKE lower(concat('%', :pai, '%')))
      AND (:mae IS NULL OR lower(trim(p.mae)) LIKE lower(concat('%', :mae, '%')))
      AND p.dtNascimento = :dtNasc
      
    ORDER BY p.nome
""")
    List<SigoPessoa> buscarComData(
            @Param("nome") String nome,
            @Param("num") String num,
            @Param("pai") String pai,
            @Param("mae") String mae,
            @Param("dtNasc") Date dtNasc
    );

    // 🔹 SEM DATA DE NASCIMENTO
    @Query("""
        SELECT p
        FROM SigoPessoa p
        WHERE (:nome IS NULL OR lower(trim(p.nome)) LIKE lower(concat('%', :nome, '%')))
          AND (:num IS NULL OR trim(p.num) LIKE concat('%', :num, '%'))
          AND (:pai IS NULL OR lower(trim(p.pai)) LIKE lower(concat('%', :pai, '%')))
          AND (:mae IS NULL OR lower(trim(p.mae)) LIKE lower(concat('%', :mae, '%')))
        ORDER BY p.nome
    """)
    List<SigoPessoa> buscarSemData(
            @Param("nome") String nome,
            @Param("num") String num,
            @Param("pai") String pai,
            @Param("mae") String mae
    );



    //AND (:dtNasc IS NULL OR p.dtNascimento = :dtNasc)


}
