package org.deer.jackson.problem.one.type.serialization;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.deer.jackson.problem.one.type.serialization.dto.ComplexType;
import org.deer.jackson.problem.one.type.serialization.type.ObjectTypeResolverBuilder;
import org.deer.jackson.problem.one.type.serialization.dto.ArrayType;
import org.deer.jackson.problem.one.type.serialization.dto.RootDto;
import org.junit.Test;

/**
 * Common case when you are using the json for ex. for communication between different runtimes that
 * need the type information for more complex types
 */
public class SerializeTypeJustForComplexTypesTest {


  @Test
  public void testSerialize() throws JsonProcessingException {
    final ArrayType[] array = {
        new ArrayType("one"),
        new ArrayType("two")
    };
    final ComplexType complex = new ComplexType(24, 47L);
    final RootDto rootDto = new RootDto("name",
        array, complex);

    final ObjectMapper objectMapperDefault = new ObjectMapper();
    objectMapperDefault.enable(SerializationFeature.INDENT_OUTPUT);
    objectMapperDefault.enableDefaultTypingAsProperty(DefaultTyping.NON_FINAL, "_type");
    final String defaultJsonString = objectMapperDefault.writeValueAsString(rootDto);
    System.out.println(defaultJsonString);

    //as can bee seen in output, array is not just serialized as array with "typed" values,
    //but as array with first element as type, and second is nested array of actual array values
    //which is ugly at least
    assertTrue(defaultJsonString.contains("[Lorg.deer.jackson.type.serialization.dto.ArrayType;"));

    final ObjectMapper objectMapperNice = new ObjectMapper();
    objectMapperNice.enable(SerializationFeature.INDENT_OUTPUT);
    //here's the magic
    objectMapperNice.setDefaultTyping(new ObjectTypeResolverBuilder());
    final String niceJsonString = objectMapperNice.writeValueAsString(rootDto);
    System.out.println(niceJsonString);

    assertFalse(niceJsonString.contains("[Lorg.deer.jackson.type.serialization.dto.ArrayType;"));
  }
}
