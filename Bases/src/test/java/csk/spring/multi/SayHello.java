package csk.spring.multi;

import org.springframework.stereotype.Service;

@Service
public class SayHello implements ISay {
    @Override
    public String say(String say) {
        return say+" hello !!!";
    }
}
