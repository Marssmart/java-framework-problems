package org.deer.jackson.problem.one.type.serialization.type;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Custom type resolver that includes type information only for complex types
 */
public class ObjectTypeResolverBuilder extends ObjectMapper.DefaultTypeResolverBuilder {

  private static final Set<Class<?>> PRIMITIVE_WRAPPERS = new HashSet<Class<?>>(
      Arrays.asList(Boolean.class, Character.class, Byte.class,
          Short.class, Integer.class, Long.class, Float.class, Double.class, Void.class,
          String.class));

  public ObjectTypeResolverBuilder() {
    super(ObjectMapper.DefaultTyping.NON_FINAL);
    // copie the behaviour of ObjectMapper#enableDefaultTyping
    init(JsonTypeInfo.Id.CLASS, null);
    inclusion(JsonTypeInfo.As.PROPERTY);
    typeProperty("_type");
  }

  /**
   * This method is used to check if type resolver should be used for type, aka if the type
   * information will be included in final json.
   */
  @Override
  public boolean useForType(JavaType t) {
    return !t.isArrayType() && !t.isPrimitive() && !t.isEnumType() &&
        PRIMITIVE_WRAPPERS.stream().noneMatch(t::isTypeOrSubTypeOf);
  }
}
