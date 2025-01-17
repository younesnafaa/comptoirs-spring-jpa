package comptoirs.entity;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = {"COMMANDE_NUMERO", "PRODUIT_REFERENCE"})
})
public class Ligne {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(nullable = false)
	@Setter(AccessLevel.NONE) // la clé est auto-générée par la BD, On ne veut pas de "setter"
	private Integer id;

	@JoinColumn(nullable = false)
	@ManyToOne(optional = false)
	@NonNull
	private Commande commande;

	@JoinColumn(nullable = false)
	@ManyToOne(optional = false)
	@NonNull
	private Produit produit;

	@Basic(optional = false)
	@Column(nullable = false)
	@NonNull
	private Short quantite;

}
