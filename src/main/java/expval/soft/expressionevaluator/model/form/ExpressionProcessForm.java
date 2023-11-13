package expval.soft.expressionevaluator.model.form;

import expval.soft.expressionevaluator.validation.ValidExpressionForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ValidExpressionForm(name = "name", value = "value")
public class ExpressionProcessForm {
  String name;
  String value;
}
