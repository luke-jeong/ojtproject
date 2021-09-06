package device.ojtproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeviceErrorCode {
    NO_QRCODE("잘못된 큐알코드 입니다."),
    NO_SERIAL_NUMBER("잘못된 시리얼넘버입니다."),
    DUPLICATED_SN("중복된 시리얼 번호입니다."),
    NO_MEMBER("해당되는 회원이 없습니다."),
    ACTIVE_ERROR("장비동작 오류입니다. 장비 전원을 확인해주세요."),

    INTERNAL_SERVER_ERROR("서버에 오류가 발생했습니다."),
    INVALID_REQUEST("잘못된 요청입니다.");


    private final String message;
}
