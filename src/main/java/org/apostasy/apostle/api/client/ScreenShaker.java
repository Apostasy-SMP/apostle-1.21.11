package org.apostasy.apostle.api.client;

/**
 * @author n1tr0nr
 * Ported from Nitrogen
 */
public interface ScreenShaker {
    default boolean isScreenShaking(){
        return getScreenShakeIntensity() > 0;
    }
    int getScreenShakeDuration();
    float getScreenShakeIntensity();
    void setScreenShakeDuration(int duration);
    void setScreenShakeIntensity(float intensity);
    default void addScreenShake(float intensity, int duration){
        setScreenShakeIntensity(intensity);
        setScreenShakeDuration(duration);
    }
}