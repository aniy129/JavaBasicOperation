package csk.bases.npe;

import org.junit.Test;

import java.util.Optional;

public class OptionalTest {
    @Test
    public void test() {
        Province province = new Province();
        province.setCity("beijing");
        Address address = new Address();
        address.setProvince(province);
        User user = new User();
        user.setAddress(address);
        System.out.println(user.getAddress().getProvince().getCity());

        //使用这种方式防止nullPointException （npe）问题
        User user1 = new User();
        String result = Optional
                .ofNullable(user1)
                .map(User::getAddress)
                .map(Address::getProvince)
                .map(Province::getCity)
                .orElse("no value in the variate"); //没有值的时候返回给定的默认值值

        System.out.println(result);

    }
}
