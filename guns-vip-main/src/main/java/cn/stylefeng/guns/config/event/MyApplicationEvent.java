package cn.stylefeng.guns.config.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by li wen ya on 2021/11/19
 */
public class MyApplicationEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MyApplicationEvent(Object source) {
        super(source);
    }
}
