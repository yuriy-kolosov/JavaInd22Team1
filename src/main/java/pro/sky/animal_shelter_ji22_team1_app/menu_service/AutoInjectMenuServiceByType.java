package pro.sky.animal_shelter_ji22_team1_app.menu_service;

import pro.sky.animal_shelter_ji22_team1_app.entity.Type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoInjectMenuServiceByType {
    Type type();
}
