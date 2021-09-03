package device.ojtproject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiscardStatus {
    DISCARD("폐기"), NORMAL("운영중");

    private final String description;
}
