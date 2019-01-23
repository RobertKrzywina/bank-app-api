package pl.robert.project.app.address;

interface AddressValidationStrings {

    int CITY_MIN_LENGTH = 3;
    int CITY_MAX_LENGTH = 50;
    int ZIP_CODE_LENGTH = 6;
    int STREET_MIN_LENGTH = 3;
    int STREET_MAX_LENGTH = 50;
    String ZIP_CODE_REGEX = "[0-9]{2}\\-[0-9]{3}";
    String HOUSE_NUMBER_REGEX = "^[0-9]*$";

    ////////////////////////////////////////////////////////////////////////////////////////////

    String C_PROVINCE_NULL = "province.null";
    String C_CITY_NULL = "city.null";
    String C_ZIP_CODE_NULL = "zip_code.null";
    String C_STREET_NULL = "street.null";
    String C_HOUSE_NUMBER_NULL = "house_number.null";

    String C_CITY_LENGTH = "city.length";
    String C_STREET_LENGTH = "street.length";

    String C_ZIP_CODE_INVALID = "zip_code.invalid";
    String C_HOUSE_NUMBER_INVALID = "house_number.invalid";

    ////////////////////////////////////////////////////////////////////////////////////////////

    String M_PROVINCE_NULL = "Enter your province";
    String M_CITY_NULL = "Enter your city";
    String M_ZIP_CODE_NULL = "Enter your zip code";
    String M_STREET_NULL = "Enter your street";
    String M_HOUSE_NUMBER_NULL = "Enter your house number";

    String M_CITY_LENGTH = "City name should have from 3 to 50 characters";
    String M_STREET_LENGTH = "street.length";

    String M_ZIP_CODE_INVALID = "Zip code should have XX-XXX format";
    String M_HOUSE_NUMBER_INVALID = "House number should have only digits from 1 to 1000";
}
