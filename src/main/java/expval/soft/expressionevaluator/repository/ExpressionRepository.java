package expval.soft.expressionevaluator.repository;

import expval.soft.expressionevaluator.model.Expression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpressionRepository extends JpaRepository<Expression, Long> {
  @Query("SELECT e FROM Expression e WHERE e.name = ?1")
  Expression findExpressionByName(String name);

  @Query("SELECT e FROM Expression e WHERE e.identifier = ?1")
  Expression findExpressionByIdentifier(String name);
}
