package expval.soft.expressionevaluator.model.form;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import expval.soft.expressionevaluator.model.QueryDeserializer;
import lombok.Data;

@Data
@JsonDeserialize(using = QueryDeserializer.class)
public class ExpressionDynamicForm {
  private String other;
}
