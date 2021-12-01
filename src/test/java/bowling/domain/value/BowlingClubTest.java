package bowling.domain.value;

import bowling.domain.factory.FrameFactory;
import bowling.domain.frame.Frame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BowlingClubTest {
    private BowlingClub bowlingClub;

    @BeforeEach
    void setup() {
        FrameFactory frameFactory = new FrameFactory();
        List<Frame> frames = frameFactory.create();

        bowlingClub = BowlingClub.from(frames);
    }

    @Test
    @DisplayName("기본 프레임에서 스트라이크가 발생되면 다음 프레임 넘어가는 부분 검증")
    void knockedDown() {
        bowlingClub.knockedDown(Pins.from(10));

        assertThat(bowlingClub.getCurrentFrameNumber()).isEqualTo(FrameNumber.from(2));
    }

    @Test
    @DisplayName("기본 프레임에서 투구수 2회 발생되면 다음 프레임 넘어가는 부분 검증")
    void knockedDown2() {
        bowlingClub.knockedDown(Pins.from(4));
        bowlingClub.knockedDown(Pins.from(3));

        assertThat(bowlingClub.getCurrentFrameNumber()).isEqualTo(FrameNumber.from(2));
    }

    @Test
    @DisplayName("기본 프레임에서 스트라이크가 발생되지 않고 1회 투구 인 경우, 기존 프레임 유지 검증")
    void knockedDown3() {
        bowlingClub.knockedDown(Pins.from(4));

        assertThat(bowlingClub.getCurrentFrameNumber()).isEqualTo(FrameNumber.from(1));
    }

    @Test
    @DisplayName("마지막 프레임에서 미스가 발생되는 경우 정상 종료 확인")
    void isGameOver() {
        testNormalFrame();

        assertThat(bowlingClub.isGameOver()).isFalse();

        bowlingClub.knockedDown(Pins.from(4));
        bowlingClub.knockedDown(Pins.from(5));

        assertThat(bowlingClub.isGameOver()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임에서 투구수 3회 발생되는 경우 정상 종료 확인")
    void isGameOver2() {
        testNormalFrame();

        assertThat(bowlingClub.isGameOver()).isFalse();

        bowlingClub.knockedDown(Pins.from(4));
        bowlingClub.knockedDown(Pins.from(6));
        bowlingClub.knockedDown(Pins.from(4));

        assertThat(bowlingClub.isGameOver()).isTrue();
    }

    private void testNormalFrame() {
        for (int i = 1; i < 10; i++) {
            bowlingClub.knockedDown(Pins.from(10));
        }
    }

    @Test
    @DisplayName("기본 프레임에서 투구의 합이 10핀이 넘어가는 경우 예외 발생")
    void knockedDown_exception() {
        bowlingClub.knockedDown(Pins.from(4));
        assertThatIllegalArgumentException().isThrownBy(() -> bowlingClub.knockedDown(Pins.from(7)));
    }

    @Test
    @DisplayName("마지막 프레임에서 두번째 투구의 합이 10핀이 넘어가는 경우 예외 발생")
    void knockedDown_exception2() {
        testNormalFrame();

        bowlingClub.knockedDown(Pins.from(4));
        assertThatIllegalArgumentException().isThrownBy(() -> bowlingClub.knockedDown(Pins.from(7)));
    }

    @Test
    @DisplayName("프레임 정보 정상적으로 가지고 오는지 확인")
    void getPins() {
        // given
        Pins first = Pins.from(4);
        Pins second = Pins.from(6);
        Pins third = Pins.from(10);

        first_frame:
        {
            bowlingClub.knockedDown(first);
            bowlingClub.knockedDown(second);
        }

        second_frame:
        {
            bowlingClub.knockedDown(third);
        }

        // when
        assertThat(bowlingClub.getCurrentFrameNumber()).isEqualTo(FrameNumber.from(3));

        // then
        FramePins firstExpected = getFramePins(first, second);
        FramePins firstFramePins = bowlingClub.getPins(1);
        assertThat(firstFramePins).isEqualTo(firstExpected);

        FramePins secondExpected = getFramePins(third);
        FramePins secondFramePins = bowlingClub.getPins(2);
        assertThat(secondFramePins).isEqualTo(secondExpected);
    }

    private FramePins getFramePins(Pins first, Pins second) {
        FramePins framePins = getFramePins(first);
        framePins.addPins(second);
        return framePins;
    }

    private FramePins getFramePins(Pins third) {
        FramePins framePins = FramePins.create();
        framePins.addPins(third);
        return framePins;
    }
}