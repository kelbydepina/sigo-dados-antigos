package cv.pn.processmanagement.business.sigoOcorrenciaPessoa.repository;

import cv.pn.processmanagement.business.projection.OcorrenciaResumoProjection;
import cv.pn.processmanagement.business.sigoOcorrenciaPessoa.model.SgoOcorrenciaPessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import javax.transaction.Transactional;
import java.util.List;


public interface SgoOcorrenciaPessoaRepository extends JpaRepository<SgoOcorrenciaPessoa, String> {

  /*  @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO sgo_ocorrencia_pessoa (
            condicao,
            id_pessoa,
            id_ocorrencia,
            oracle_id_pessoa,
            oracle_id_ocorrencia,
            caracteristica,
            id_agente,
            user_create
        )
        VALUES (
            :condicao,
            :idPessoa,
            :idOcorrencia,
            :oracleIdPessoa,
            :oracleIdOcorrencia,
            :caracteristica,
            :idAgente,
            :userCreate
        )
    """, nativeQuery = true)
    void insertOcorrenciaPessoa(
            @Param("condicao") String condicao,
            @Param("idPessoa") Long idPessoa,
            @Param("idOcorrencia") Long idOcorrencia,
            @Param("oracleIdPessoa") Long oracleIdPessoa,
            @Param("oracleIdOcorrencia") Long oracleIdOcorrencia,
            @Param("caracteristica") String caracteristica,
            @Param("idAgente") Long idAgente,
            @Param("userCreate") String userCreate
    );*/
    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO sgo_ocorrencia_pessoa (
            condicao,
            id_pessoa,
            id_ocorrencia,
            oracle_id_pessoa,
            oracle_id_ocorrencia,
            caracteristica,
            id_agente,
            user_create
        )
        VALUES (
            :condicao,
            :idPessoa,
            :idOcorrencia,
            :oracleIdPessoa,
            :oracleIdOcorrencia,
            :caracteristica,
            :idAgente,
            :userCreate
        )
        ON CONFLICT (oracle_id_pessoa, oracle_id_ocorrencia, condicao) DO NOTHING
    """, nativeQuery = true)
    void insertIgnoreDuplicate(
            @Param("condicao") String condicao,
            @Param("idPessoa") Long idPessoa,
            @Param("idOcorrencia") Long idOcorrencia,
            @Param("oracleIdPessoa") Long oracleIdPessoa,
            @Param("oracleIdOcorrencia") Long oracleIdOcorrencia,
            @Param("caracteristica") String caracteristica,
            @Param("idAgente") Long idAgente,
            @Param("userCreate") String userCreate
    );
   // List<SgoOcorrenciaPessoa> findByOracleIdPessoa(Long oracleIdPessoa);
    //1 boolean existsByOracleIdPessoa(Long idPessoa);

  //  boolean existsByOracleIdPessoa(Long oracleIdPessoa);


    /*@Query(value = """
        SELECT
            o.num_ocorrencia           AS numOcorrencia,
            COALESCE(tp.nome, '')     AS natureza,
            COALESCE(o.tp_auto, '')   AS autoPrincipal,
            COALESCE(op.condicao, '') AS condicao,
            COALESCE(tp.nome, '')     AS tipologiaCrime,
            o.data_registo            AS dataRegisto,
            COALESCE(e.nome, '')     AS unidade,
            COALESCE(o.estado, '')   AS fase
        FROM sgo_ocorrencia_pessoa op
        JOIN sgo_ocorrencia o
          ON o.oracle_id = op.oracle_id_ocorrencia

        LEFT JOIN sgo_ocorrencia_tp_ocorrencia otp
          ON otp.id_ocorrencia = o.oracle_id

        LEFT JOIN sgo_tp_ocorrencia tp
          ON tp.oracle_id = otp.id_tp_ocorrencia

        LEFT JOIN esquadra e
          ON e.oracle_id = o.id_esquadra

        WHERE op.oracle_id_pessoa = :oracleIdPessoa
        ORDER BY o.data_registo DESC
    """, nativeQuery = true)
    List<OcorrenciaResumoProjection> listarOcorrenciasDaPessoa(
            @Param("oracleIdPessoa") Long oracleIdPessoa
    );*/

    //esse query resulta
   /* @Query(value = """
    SELECT
        o.num_ocorrencia           AS numOcorrencia,
        COALESCE(tp.nome, '')     AS natureza,
        COALESCE(o.tp_auto, '')   AS autoPrincipal,
        COALESCE(op.condicao, '') AS condicao,
        COALESCE(tp.nome, '')     AS tipologiaCrime,
        o.data_registo            AS dataRegisto,
        COALESCE(e.nome, '')     AS unidade,
        COALESCE(o.estado, '')   AS fase
    FROM sgo_ocorrencia_pessoa op
    JOIN sgo_ocorrencia o
      ON o.oracle_id = op.oracle_id_ocorrencia

    LEFT JOIN sgo_ocorrencia_tp_ocorrencia otp
      ON otp.id_ocorrencia = o.oracle_id

    LEFT JOIN sgo_tp_ocorrencia tp
      ON tp.oracle_id = otp.id_tp_ocorrencia

    LEFT JOIN esquadra e
      ON e.oracle_id = o.id_esquadra

    WHERE op.oracle_id_pessoa = :oracleIdPessoa
    ORDER BY o.data_registo DESC
""", nativeQuery = true)
    List<OcorrenciaResumoProjection> listarOcorrenciasDaPessoa(
            @Param("oracleIdPessoa") Long oracleIdPessoa
    );*/

//isto stava a funcionar bem
 /*@Query(value = """
SELECT
    o.num_ocorrencia AS numOcorrencia,
    COALESCE(tp.nome, '') AS natureza,

    CASE o.tp_auto
        WHEN '1' THEN 'Auto de Denúncia'
        WHEN '2' THEN 'Auto de Notícia'
        WHEN '3' THEN 'Auto de Auto de Detenção'
        WHEN '4' THEN 'Auto de Denúncia VBG'
        WHEN '5' THEN 'Auto de Identificação'
        ELSE 'Outro'
    END AS autoPrincipal,
    
    
    COALESCE(spp.condicao, 'Desconhecido') AS condicao,

  
    o.data_registo AS dataRegisto,
    COALESCE(e.nome, '') AS unidade,
    COALESCE(o.descricao_etapa, 'AGUARDA ANÁLISE') AS fase

FROM sgo_ocorrencia_pessoa op
JOIN sgo_ocorrencia o
    ON o.oracle_id = op.oracle_id_ocorrencia
    
 LEFT JOIN sgo_peca_pessoa spp
   ON spp.id_pessoa = op.oracle_id_pessoa
                                                                          

LEFT JOIN sgo_ocorrencia_tp_ocorrencia otp
    ON otp.id_ocorrencia = o.oracle_id

LEFT JOIN sgo_tp_ocorrencia tp
    ON tp.oracle_id = otp.id_tp_ocorrencia

LEFT JOIN esquadra e
    ON e.oracle_id = o.id_esquadra

WHERE op.oracle_id_pessoa = :oracleIdPessoa
ORDER BY o.data_registo DESC
""", nativeQuery = true)
 List<OcorrenciaResumoProjection> listarOcorrenciasDaPessoa(
         @Param("oracleIdPessoa") Long oracleIdPessoa
 );*/

    @Query(value = """
SELECT
    o.num_ocorrencia AS numOcorrencia,
    COALESCE(tp.nome, '') AS natureza,

    CASE o.tp_auto
        WHEN '1' THEN 'Auto de Denúncia'
        WHEN '2' THEN 'Auto de Notícia'
        WHEN '3' THEN 'Auto de Detenção'
        WHEN '4' THEN 'Auto de Denúncia VBG'
        WHEN '5' THEN 'Auto de Identificação'
        ELSE 'Outro'
    END AS autoPrincipal,
    
        
  --  CONDIÇÃO INTELIGENTE
    CASE
        WHEN o.tp_auto = '1' AND op.condicao = '1' THEN 'Denunciante'
        WHEN o.tp_auto = '1' AND op.condicao = '2' THEN 'Ofendido'
        WHEN o.tp_auto = '1' AND op.condicao = '3' THEN 'Denunciado'
        WHEN o.tp_auto = '1' AND op.condicao = '4' THEN 'Testemunha'
        
        WHEN o.tp_auto = '2' AND op.condicao = '1' THEN 'Participante'
        WHEN o.tp_auto = '2' AND op.condicao = '2' THEN 'Denunciante'
        WHEN o.tp_auto = '2' AND op.condicao = '3' THEN 'Denunciado'
        WHEN o.tp_auto = '2' AND op.condicao = '4' THEN 'Testemunha'
        WHEN o.tp_auto = '2' AND op.condicao = '8' THEN 'Agente Atuante'

        WHEN o.tp_auto = '3' AND op.condicao IN ('3','5') THEN 'Detido'
        WHEN o.tp_auto = '3' AND op.condicao = '6' THEN 'Detentor'
        WHEN o.tp_auto = '3' AND op.condicao = '4' THEN 'Testemunha'

        WHEN o.tp_auto = '4' AND op.condicao = '1' THEN 'Denunciante/Vitima'
        WHEN o.tp_auto = '4' AND op.condicao = '3' THEN 'Denunciado/Autor'

        WHEN o.tp_auto = '5' AND op.condicao = '2' THEN 'Identificado'
        WHEN o.tp_auto = '5' AND op.condicao = '7' THEN 'Agente Identificador'

        ELSE 'Desconhecido'
    END AS condicao,
   
    o.data_registo AS dataRegisto,
    COALESCE(e.nome, '') AS unidade,
    COALESCE(o.descricao_etapa, 'AGUARDA ANÁLISE') AS fase
    
    
FROM sgo_ocorrencia_pessoa op


 JOIN sgo_ocorrencia o
       ON o.oracle_id = op.oracle_id_ocorrencia
                                                     
LEFT JOIN sgo_ocorrencia_tp_ocorrencia otp
    ON otp.id_ocorrencia = o.oracle_id
LEFT JOIN sgo_tp_ocorrencia tp
    ON tp.oracle_id = otp.id_tp_ocorrencia
LEFT JOIN esquadra e
    ON e.oracle_id = o.id_esquadra
    
    

WHERE op.oracle_id_pessoa = :oracleId
ORDER BY o.data_registo DESC
""", nativeQuery = true)
    List<OcorrenciaResumoProjection> buscarAntecedentesPorPessoa(@Param("oracleId") Long oracleId);

    @Query("""
    SELECT COUNT(op) > 0
    FROM SgoOcorrenciaPessoa op
    JOIN SgoOcorrencia o
       ON o.oracleId = op.oracleIdOcorrencia
    WHERE op.oracleIdPessoa = :oracleIdPessoa
""")
    boolean existsAntecedenteReal(@Param("oracleIdPessoa") Long oracleIdPessoa);
   /* List<OcorrenciaResumoProjection> buscarOcorrenciasPorOracleIds(
            @Param("oracleId") Long oracleId
    );*/

    //funcional
    /*LEFT JOIN sgo_peca_pessoa spp
    ON spp.id_pessoa = op.oracle_id_pessoa*/

    // funcional
    /* WHERE op.oracle_id_pessoa = :oracleId*/

    //ISTO E FUNCIONBAL
   /* FROM sgo_ocorrencia_pessoa op
    JOIN sgo_ocorrencia o
    ON o.oracle_id = op.oracle_id_ocorrencia*/
   //funcional
    /*COALESCE(spp.condicao, 'Desconhecido') AS condicao,*/
   //para grupar e contar
   /* GROUP BY
    o.num_ocorrencia, tp.nome, o.tp_auto, spp.condicao,
    o.data_registo, e.nome, o.descricao_etapa*/

/*   IN (:oracleIds)*/

   /* CASE o.estado
    WHEN 'A' THEN 'Enviado ao MP'
    WHEN 'P' THEN 'Enviado ao EICC '
    WHEN 'C' THEN 'Aguarda Análise'
    WHEN 'C' THEN 'Arquivado'
    WHEN 'C' THEN 'Pendente Análise'
    WHEN 'C' THEN 'Retornando ao Registo'
    ELSE 'Em análise'
    END AS fase*/

    /*--COALESCE(i.nome, 'Desconhecido') AS condicao,*/

/*    LEFT JOIN intervinientes i
    ON CAST(op.condicao AS VARCHAR) = i.id*/

   /* LEFT JOIN intervinientes i
    ON i.id = CAST(op.condicao AS VARCHAR)*/
/*
    LEFT JOIN sgo_peca_intervenente spi
    ON spi.id_peca = spp.id_processo_peca*/

    /*COALESCE(spi.condicao, 'Desconhecido') AS condicao,*/

   /* -- CONDIÇÃO REAL DA PESSOA
    CASE op.condicao
    WHEN '1' THEN 'O autado'
    WHEN '2' THEN 'Denunciante'
    WHEN '3' THEN 'Denunciado'
    WHEN '4' THEN 'Denunciante'
    WHEN '5' THEN 'Denunciado'
    WHEN '6' THEN 'Detentor'
    WHEN '7' THEN 'O Autuante'
    WHEN '8' THEN 'Detentor'
    WHEN '9' THEN 'Detido'
    WHEN '10' THEN 'Testemunha'
    ELSE 'Desconhecido'
    END AS condicao,*/




}
