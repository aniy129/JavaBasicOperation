package csk.spring.Beans.xml;

public interface IBusinessService {
    String getServiceName();

    void invoke();

    void throwException() throws Exception;

    String putStringsTogether(String first, String second);
}
