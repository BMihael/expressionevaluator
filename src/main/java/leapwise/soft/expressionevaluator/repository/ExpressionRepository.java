package leapwise.soft.expressionevaluator.repository;

import leapwise.soft.expressionevaluator.model.Expression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpressionRepository extends JpaRepository<Expression, Long> {
    @Query("SELECT e FROM Expression e WHERE e.name = ?1")
    Expression nameExists(String name);

    @Query("SELECT e FROM Expression e WHERE e.identifier = ?1")
    Expression findExpression(String name);

}
