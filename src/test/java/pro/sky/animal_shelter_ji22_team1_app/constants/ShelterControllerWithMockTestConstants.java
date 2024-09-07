package pro.sky.animal_shelter_ji22_team1_app.constants;

import org.springframework.mock.web.MockMultipartFile;

import java.nio.charset.StandardCharsets;

/**
 * Класс констант для тестирования контроллера ShelterController с мокированием
 */
public class ShelterControllerWithMockTestConstants {

    public static final Long SHELTER1_ID = 1L;

    public static final String SHELTER1_NAME = "Cats and 100 friends";
    public static final String SHELTER1_TYPE = "Cats";
    public static final String SHELTER1_CONTACTS = "+74950010101";
    public static final String SHELTER1_MEDIA_TYPE = "image/jpeg";
    public static final String SHELTER1_RULES = "Shelter Rules1";

    public static final byte[] SHELTER1_LOCATION_SCHEME = {0x0001, 0x0002, 0x0003, 0x004};

    public static final byte[] SHELTER1_LOCATION_SCHEME_FILE_LENGTH = "{Shelter-location-scheme}"
            .getBytes(StandardCharsets.UTF_8);

    public static final MockMultipartFile SHELTER1_LOCATION_SCHEME_FILE = new MockMultipartFile(
            "Shelter-location-scheme-name"
            , "Shelter-location-scheme-filename"
            , "image/jpeg", SHELTER1_LOCATION_SCHEME_FILE_LENGTH);

    public static final Long SHELTER2_ID = 1L;

    public static final String SHELTER2_NAME = "Cats and 100 friends";
    public static final String SHELTER2_TYPE = "Cats";
    public static final String SHELTER2_CONTACTS = "+74950010101";
    public static final String SHELTER2_MEDIA_TYPE = "image/jpeg";
    public static final String SHELTER2_RULES = "Shelter Rules1";

}
