package com.forrestdale.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * Created by forrest on 2/12/17.
 */
public class ModelBase implements PropertyChangeListener {
    private ArrayList<PropertyChangeListener> mPropertyChangedListeners;

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        for (PropertyChangeListener listener : mPropertyChangedListeners) {
            listener.propertyChange(propertyChangeEvent);
        }
    }

    public void registerPropertyChangedEventListener(PropertyChangeListener listener) {
        mPropertyChangedListeners.add(listener);
    }
}
