package comptoirs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import comptoirs.entity.Commande;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    /**
     * Calcule le montant des articles commandés dans une commande
     * @param numeroCommande le numéro de la commande à traiter
     * @return le montant des articles commandés, en tenant compte de la remise
     */
    @Query("""
SELECT SUM(l.quantite * l.produit.prixUnitaire * (1 - l.commande.remise))
FROM Ligne l
WHERE l.commande.numero = :numeroCommande""")
    BigDecimal montantArticles(Integer numeroCommande);

    @Query("""
            SELECT c.numero AS numeroCommande,
                   c.port AS port,
                   SUM(l.quantite * l.produit.prixUnitaire * (1 - c.remise)) AS montantArticles
            FROM Commande c
            JOIN c.lignes l
            WHERE c.client.code = :codeClient
            GROUP BY c.numero, c.port
            """)
    List<CommandeProjection> findCommandesByClientCode(String codeClient);

}
