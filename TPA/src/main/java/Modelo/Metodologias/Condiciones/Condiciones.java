package Modelo.Metodologias.Condiciones;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import Modelo.Empresa.Empresa;

@Entity
@Table(name = "condiciones")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Discriminador", discriminatorType = DiscriminatorType.STRING)
public abstract class Condiciones {

	@Id
	@GeneratedValue
	@Column(name = "id_condiciones")
	private long id_condiciones;

	public abstract boolean cumple(Empresa empresa);
	
	public abstract String mostrarCadena();
		
}
