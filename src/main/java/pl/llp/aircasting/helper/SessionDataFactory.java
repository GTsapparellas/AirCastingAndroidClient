package pl.llp.aircasting.helper;

import com.google.inject.Inject;
import pl.llp.aircasting.activity.ApplicationState;
import pl.llp.aircasting.model.*;
import pl.llp.aircasting.util.Constants;

import java.util.List;

/**
 * Created by radek on 20/10/17.
 */
public class SessionDataFactory {
    @Inject CurrentSessionManager currentSessionManager;
    @Inject ViewingSessionsManager viewingSessionsManager;
    @Inject CurrentSessionSensorManager currentSessionSensorManager;
    @Inject ViewingSessionsSensorManager viewingSessionsSensorManager;
    @Inject SessionState sessionState;

    public Session getSession(long sessionId) {
        Session session;

        if (sessionState.isSessionCurrent(sessionId)) {
            session = currentSessionManager.getCurrentSession();
        } else {
            session = viewingSessionsManager.getSession(sessionId);
        }

        return session;
    }

    public Sensor getSensor(String sensorName, long sessionId) {
        Sensor sensor;

        if (sessionState.isSessionCurrent(sessionId)) {
            sensor = currentSessionSensorManager.getSensorByName(sensorName);
        } else {
            sensor = viewingSessionsSensorManager.getSensorByName(sensorName, sessionId);
        }

        return  sensor;
    }

    public List<Sensor> getSensorsList(long sessionId) {
        List<Sensor> result;

        if (sessionState.isSessionCurrent(sessionId)) {
            result = currentSessionSensorManager.getSensorsList();
        } else {
            result = viewingSessionsSensorManager.getSensorsList(sessionId);
        }

        return result;
    }

    public MeasurementStream getStream(String sensorName, long sessionId) {
        MeasurementStream stream;

        if (sessionState.isSessionCurrent(sessionId)) {
            stream = currentSessionManager.getMeasurementStream(sensorName);
        } else {
            stream = viewingSessionsManager.getMeasurementStream(sensorName, sessionId);
        }

        return stream;
    }

    }
    }

    }
}