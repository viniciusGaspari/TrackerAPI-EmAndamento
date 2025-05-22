package io.github.vinicusgaspari.trackerapi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "chip")
public class Chip {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "linha", length = 12, nullable = false)
    private Integer linha;

    @Column(name = "operadora", nullable = false)
    private String operadora;

    @ManyToOne
    @JoinColumn(name = "conta", referencedColumnName = "id")
    private Conta conta;

    @OneToMany(mappedBy = "chip")
    private List<Rastreador> rastreador;

    @CreatedDate
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

}
