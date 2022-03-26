package com.zenith.event.proxy;

import com.collarmc.pounce.EventInfo;
import com.collarmc.pounce.Preference;

@EventInfo(preference = Preference.POOL)
public class StartQueueEvent {
    public final Integer position;

    public StartQueueEvent(Integer position) {
        this.position = position;
    }
}