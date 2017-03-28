package com.forrestdale;

import com.forrestdale.interfaces.IObservable;
import com.forrestdale.interfaces.ISubscriber;

import java.util.ArrayList;

/**
 * Created by jesse on 3/27/2017.
 */
public class ObserverBase implements IObservable{
    private ArrayList<ISubscriber> subscribers = new ArrayList<>();


    @Override
    public void subscribe(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    protected void notifySubscribers(String property) {
        for (ISubscriber subscriber : subscribers) {
            subscriber.onPropertyChange(property);
        }
    }
}
