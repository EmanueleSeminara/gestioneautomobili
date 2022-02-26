package it.gestioneautomobili.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "proprietario")
public class Proprietario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String congome;
	@Column(name = "codicefiscale")
	private String codiceFiscale;
	@Column(name = "datadinascita")
	private Date dataDiNascita;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proprietario")
	private Set<Automobile> automobili = new HashSet<>();

	public Proprietario() {
		super();
	}

	public Proprietario(String nome, String congome, String codiceFiscale, Date dataDiNascita,
			Set<Automobile> automobili) {
		super();
		this.nome = nome;
		this.congome = congome;
		this.codiceFiscale = codiceFiscale;
		this.dataDiNascita = dataDiNascita;
		this.automobili = automobili;
	}

	public Proprietario(String nome, String congome, String codiceFiscale, Date dataDiNascita) {
		super();
		this.nome = nome;
		this.congome = congome;
		this.codiceFiscale = codiceFiscale;
		this.dataDiNascita = dataDiNascita;
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

	public String getCongome() {
		return congome;
	}

	public void setCongome(String congome) {
		this.congome = congome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public Set<Automobile> getAutomobili() {
		return automobili;
	}

	public void setAutomobili(Set<Automobile> automobili) {
		this.automobili = automobili;
	}

	@Override
	public int hashCode() {
		return Objects.hash(automobili, codiceFiscale, congome, dataDiNascita, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proprietario other = (Proprietario) obj;
		return Objects.equals(automobili, other.automobili) && Objects.equals(codiceFiscale, other.codiceFiscale)
				&& Objects.equals(congome, other.congome) && Objects.equals(dataDiNascita, other.dataDiNascita)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "Proprietario [id=" + id + ", nome=" + nome + ", congome=" + congome + ", codiceFiscale=" + codiceFiscale
				+ ", dataDiNascita=" + dataDiNascita + ", automobili=" + automobili + "]";
	}

}
