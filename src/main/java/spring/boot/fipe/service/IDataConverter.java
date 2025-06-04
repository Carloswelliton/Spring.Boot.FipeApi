package spring.boot.fipe.service;

public interface IDataConverter {
  <T> T ApiCall(String json, Class<T> classe);
}
