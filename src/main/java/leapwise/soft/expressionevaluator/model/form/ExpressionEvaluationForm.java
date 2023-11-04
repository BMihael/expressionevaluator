package leapwise.soft.expressionevaluator.model.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpressionEvaluationForm {
    String id;
    String jsonRaw;
}
