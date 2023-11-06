package leapwise.soft.expressionevaluator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import leapwise.soft.expressionevaluator.model.form.ExpressionDynamicForm;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class QueryDeserializer extends JsonDeserializer<ExpressionDynamicForm> {

  @Override
  public ExpressionDynamicForm deserialize(JsonParser jp, DeserializationContext ctxt)
      throws IOException {

    JsonNode node = jp.getCodec().readTree(jp);
    Iterator<Map.Entry<String, JsonNode>> nodeIterator = node.fields();
    ExpressionDynamicForm query = new ExpressionDynamicForm();
    Map<String, Object> other = new HashMap<>();

    while (nodeIterator.hasNext()) {
      Map.Entry<String, JsonNode> entry = nodeIterator.next();
      Field field = getField(entry.getKey());

      if (field != null) {
        try {
          field.set(query, entry.getValue().textValue());
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      } else {
        other.put(entry.getKey(), entry.getValue().textValue());
      }
    }

    if (other.size() != 0) {
      // query.setOther(other);
    }

    return query;
  }

  public static Field getField(String entryName) {
    for (Field field : ExpressionDynamicForm.class.getDeclaredFields()) {
      field.setAccessible(true);
      String fieldName;
      if (field.isAnnotationPresent(JsonProperty.class)) {
        fieldName = field.getAnnotation(JsonProperty.class).value();
      } else {
        fieldName = field.getName();
      }
      if (entryName.equalsIgnoreCase(fieldName)) {
        return field;
      }
    }
    return null;
  }
}
