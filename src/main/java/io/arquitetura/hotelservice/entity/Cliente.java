package io.arquitetura.hotelservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String nome;

    private String cpf;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
    @JsonProperty("dt_cadastro")
    @Column(name = "dt_cadastro", nullable = false)
    private Timestamp dtCadastro;

    @JsonFormat(pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "Brazil/East")
    @JsonProperty("dt_nascimento")
    @Column(name = "dt_nascimento", nullable = false)
    private Timestamp dtNascimento;

    private String telefone;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Timestamp dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public Timestamp getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Timestamp dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
