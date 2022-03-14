package com.ebay.cabasservice.resolver;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class QueryResolver implements GraphQLQueryResolver {
  public String get() {
    return "";
  }
}
