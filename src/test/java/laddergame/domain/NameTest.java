package laddergame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("이름")
class NameTest {

    @DisplayName("값이 비어있거나 공백이면 예외가 발생한다.")
    @ParameterizedTest(name = "value = {0}")
    @EmptySource
    void throwExceptionWhenNameIsEmpty(final String value) {
        assertThatThrownBy(() -> new Name(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("값이 알파벳, 숫자가 아니면 예외가 발생한다.")
    @ParameterizedTest(name = "value = {0}")
    @ValueSource(strings = {"한글훈글", "한글", "훈글", "한a", "!!!!", "a!c"})
    void throwExceptionWhenNameIsNotAlphabet(final String value) {
        assertThatThrownBy(() -> new Name(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("값이 null이면 예외가 발생한다.")
    @Test
    void throwExceptionWhenNameIsNull() {
        assertThatThrownBy(() -> new Name(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("값이 길이를 초과하면 예외가 발생한다.")
    @ParameterizedTest(name = "value = {0}")
    @ValueSource(strings = {"rosiee", "hyenaaa", "jayonnn"})
    void throwExceptionWhenNameOverLength(final String value) {
        assertThatThrownBy(() -> new Name(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("생성된다.")
    @ParameterizedTest(name = "value = {0}")
    @ValueSource(strings = {"rosie", "hyena", "jayon"})
    void create(final String value) {
        assertDoesNotThrow(() -> new Name(value));
    }

    @DisplayName("값을 가져온다.")
    @ParameterizedTest(name = "value = {0}")
    @ValueSource(strings = {"rosie", "hyena", "jayon"})
    void getName(final String value) {
        final Name name = new Name(value);

        assertThat(name.getValue()).isEqualTo(value);
    }

    @DisplayName("값을 비교한다.")
    @ParameterizedTest(name = "value = {0}")
    @ValueSource(strings = {"rosie", "hyena", "jayon"})
    void isSame(final String value) {
        final Name name = new Name(value);
        final boolean isNameSame = name.isSame(value);

        assertThat(isNameSame).isTrue();
    }
}