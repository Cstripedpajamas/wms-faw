package cn.stylefeng.guns.config.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by li wen ya on 2021/11/19
 */
@Component
public class ApplicationEventListen {

    @EventListener
    public void listener(MyApplicationEvent event){

        System.out.println(event);
    }

}
