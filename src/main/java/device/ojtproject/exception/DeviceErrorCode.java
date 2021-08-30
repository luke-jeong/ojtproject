package device.ojtproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeviceErrorCode {
    NO_QRCODE("큐알코드 없습니다."),
    SERIAL_NUMBER_IS_NOT_ACCEPTABLE("사용할 수 없는 시리얼넘버 입니다."),
    NO_SERIAL_NUMBER("올바르지 않은 시리얼넘버입니다."),
    DUPLICATED_SN("중복된 시리얼 번호입니다."),
    NO_MEMBER("해당되는 회원이 없습니다."),

    INTERNAL_SERVER_ERROR("서버에 오류가 발생했습니다."),
    INVALID_REQUEST("잘못된 요청입니다.");


    private final String message;
}
