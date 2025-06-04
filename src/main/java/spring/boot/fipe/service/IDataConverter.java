package spring.boot.fipe.service;

import java.util.List;

public interface IDataConverter {
  <T> T ApiCall(String json, Class<T> clazz);

  <T> List<T> getList(String json, Class<T> clazz);
}
