package io.arquitetura.hotelservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name="reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("id_cliente")
    @Column(name = "id_cliente", nullable = false)
    private String idCliente;

    @JsonProperty("id_quarto")
    @Column(name = "id_quarto", nullable = false)
    private Long idQuarto;

    @JsonProperty("valor_total")
    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
    @JsonProperty("data_inicio")
    @Column(name = "data_inicio", nullable = false)
    private Timestamp dataInicio;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
    @JsonProperty("data_fim")
    @Column(name = "data_fim", nullable = false)
    private Timestamp dataFim;

    @JsonProperty("pagamento_realizado")
    @Column(name = "pagamento_realizado", nullable = false)
    private boolean pagamentoRealizado;

    @JsonProperty("pagamento_estornado")
    @Column(name = "pagamento_estornado", nullable = false)
    private boolean pagamentoEstornado;

    private String status;

    private String transacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdQuarto() {
        return idQuarto;
    }

    public void setIdQuarto(Long idQuarto) {
        this.idQuarto = idQuarto;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Timestamp getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Timestamp dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Timestamp getDataFim() {
        return dataFim;
    }

    public void setDataFim(Timestamp dataFim) {
        this.dataFim = dataFim;
    }

    public boolean isPagamentoRealizado() {
        return pagamentoRealizado;
    }

    public void setPagamentoRealizado(boolean pagamentoRealizado) {
        this.pagamentoRealizado = pagamentoRealizado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isPagamentoEstornado() {
        return pagamentoEstornado;
    }

    public void setPagamentoEstornado(boolean pagamentoEstornado) {
        this.pagamentoEstornado = pagamentoEstornado;
    }

    public String getTransacao() {
        return transacao;
    }

    public void setTransacao(String transacao) {
        this.transacao = transacao;
    }
}

